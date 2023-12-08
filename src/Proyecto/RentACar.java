package Proyecto;

import java.io.ObjectInputStream.GetField;
import java.lang.reflect.AnnotatedArrayType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.Media;

import com.formdev.flatlaf.FlatLightLaf;

import Alquiler.Alquiler;
import Alquiler.GestorAlquileres;
import Alquiler.GestorReservas;
import Alquiler.Reserva;
import Instalaciones.Sede;
import Instalaciones.Sedes;
import Interfaz.InterfazPrincipal;
import Inventario.Carro;
import Inventario.InventarioCarros;
import PDFGenerator.PDFGenerator;
import Tarifas.Categorias;
import Tarifas.Conductor;
import Tarifas.Seguros;
import Tarifas.cambioSede;
import Usuarios.Administrador_Principal;
import Usuarios.Administrador_Sede;
import Usuarios.Cliente;
import Usuarios.Empleado;
import Usuarios.Usuarios;


public class RentACar 
{
	private InventarioCarros inventario;
	private Sedes sedes;
	private GestorReservas gestorReservas;
	private GestorAlquileres gestorAlquileres;
	private Categorias categorias;  //tiene a las categorias y sus precios
	private Seguros seguros;
	private Usuarios usuarios;
	private cambioSede tarifaCambioSede = new cambioSede();
	
	public RentACar()
	{
		this.inventario = new InventarioCarros();
		this.sedes = new Sedes();
		this.gestorReservas = new GestorReservas();
		this.gestorAlquileres = new GestorAlquileres();
		this.categorias = new Categorias();
		this.usuarios = new Usuarios();
		this.seguros = new Seguros();
		
	}
	
	public int autenticar(String usuario, String contraseña)
	{
		usuarios.verificar(usuario, contraseña);
		/*
		 * la funcion mira el parametro tipo de usuario que queda tras autenticar,
		 * para generar una respuesta.
		 */
		return usuarios.getTipoUsuario();
	}
	
	public void agregarCarro(String placa, String marca, int modelo, String transmision, 
			char categoría, String sede, String rutaImagen)
	{
		inventario.agregarCarro(placa, marca, modelo, transmision, categoría, false, true,
				sede, false, false, "", rutaImagen);
	}
	
	public void eliminarCarro(String placa)
	{
		inventario.eliminarCarro(placa);
	}
	
	public void cargarInformacion()
	{
		inventario.cargarCarrosDesdeCarpeta();
		sedes.cargarSedesMapa();
		gestorReservas.cargarReservasDesdeCSV();
		gestorAlquileres.cargarAlquileresDesdeCarpeta();
		categorias.cargarCategoríasDesdeCSV();
		usuarios.cargarUsuarios();
		seguros.cargarSegurosDesdeCSV();
	}
	
	
	//metodos para el CLIENTE
	public Cliente darCliente(String usuario)
	{
		return usuarios.retornarCliente(usuario);
	}
	
	public Cliente darClienteCedula(String cedula)
	{
		return usuarios.retornarClienteCedula(cedula);
	}
	
	/**
	 * Devuelve en una lista de strings las categorías que existan.
	 * @return
	 */
	
	public String[] darCategorias()
	{
		return categorias.darCategorias();
	}
	
	public String[] darSedes()
	{
		return sedes.darSedes();
	}
	public Carro darCarro(String placa)
	{
		return inventario.buscarCarroPorPlaca(placa);
	}
	
	public Object[] reservarCarro(String nombreSedeRecogida, String nombreSedeDevolucion,
			LocalDate diaRecogida, LocalDate diaDevolucion,
			LocalTime horaRecogida, LocalTime horaDevolucion , char categoria)
	{
		int retVar = 10; //arranca en estado de que funcionó bien
		double[] precios = null;
		Carro carroSeleccionado = null;
		
		//primero mirar
		
		//TO DO LIST
		//problemas a manejar - que la sede esté cerrada, que no haya carro disponible
		//que la sede sea igual o diferente
		//calcular el precio.
		
		//Errores importantes:
		//	-> 0 la SEDE RECOGIDA no esta abierta en el horario seleccionado.
		//  -> 1 la SEDE DEVOLUCIÓN no esta abierta en el horario seleccionado.
		//  -> 2 no hay carros en la sede con las caracteristicas seleccionadas
		//  -> 10 todo funcionó a la perfección.
		
		//1.Mirar si la sede esta abierta
		
		Sede sedeRecogida = sedes.getSede(nombreSedeRecogida);
		Sede sedeDevolucion = sedes.getSede(nombreSedeDevolucion);
		
		boolean sede1IsAbierta = sedeRecogida.estaAbierta(diaRecogida.getDayOfWeek(), horaRecogida);
		boolean sede2IsAbierta = sedeDevolucion.estaAbierta(diaDevolucion.getDayOfWeek(), horaDevolucion);
		
		if(!sede1IsAbierta)
		{
			//La sede 1 no esta abierta en el horario seleccionado.
			System.out.println("SEDE RECOGIDA NO ESTA ABIERTA");			
			retVar = 0;
		}
		
		if(!sede2IsAbierta)
		{
			//La sede 2 no esta abierta en el horario seleccionado.
			System.out.println("SEDE DEVOLUCION NO ESTA ABIERTA");			
			retVar = 1;
		}
		
		
		if(sede1IsAbierta && sede2IsAbierta)
			
		//2. Mirar si hay un carro disponible en los días seleccionados.
		
		{
			//Que carros hay en la sede disponibles para reservar?
			List<Carro> carrosDisponibles = inventario.carrosDisponibles(nombreSedeRecogida,
					diaRecogida, diaDevolucion , categoria);
			
			//LOOP PARA SABER CUANTOS CARROS HAY
			for(Carro carro: carrosDisponibles)
			{
				System.out.println(carro.getPlaca());
			}
			
			if(carrosDisponibles.size() != 0) //hay carros disponibles
			{
				carroSeleccionado = carrosDisponibles.get(0);
				//se calcula el precio de la reserva
				precios = calcularPrecioReserva(nombreSedeRecogida, nombreSedeDevolucion, 
						diaRecogida, diaDevolucion, categoria);
				
			}
			
			else
			{
				retVar = 2; //error no hay carros disponibles
			}
		}
		
		//aca va la funcion de pago y por ulitmo reservar :)
		
		Object[] returnation = {retVar, precios, carroSeleccionado};
		return returnation;
	}

	public double[] calcularPrecioReserva(String nombreSedeRecogida, String nombreSedeDevolucion,
			LocalDate diaRecogida, LocalDate diaDevolucion , char categoria)
	{
		double precioTotal;
		double precio30percent;
		
		//calcular el bruto por días
		
		//le paso la categoria en forma dse str
		int precioPorDia = categorias.precioCategoría(String.valueOf(categoria)); 
		
		//dias a calcular el precio
		int diasTranscurridos = (int)(ChronoUnit.DAYS.between(diaRecogida , diaDevolucion));
		
		precioTotal = diasTranscurridos * precioPorDia;
		
		if(!(nombreSedeRecogida.equals(nombreSedeDevolucion)) )
		{
			precioTotal = precioTotal + tarifaCambioSede.getTarifa();
		}
		
		precio30percent = precioTotal*0.3;
		
		double[] precios = {precioTotal , precio30percent};
		
		return precios;
		
	}
	
	//crea una reserva y cambia la disponibilidad del vehiculo
	public void reservarDefinitivo(Cliente cliente, double precio30,Carro carroSelecionado, 
			LocalDate fechaRecogida, LocalDate fechaDevolucion, 
			LocalTime horaRecogida, LocalTime horaDevolucion, 
			String sedeRecogida, String sedeDevolucion, char categoria)
	{
		gestorReservas.crearReserva(horaRecogida, fechaRecogida, fechaDevolucion, cliente.getNumeroDocumento(), precio30, String.valueOf(categoria), carroSelecionado.getPlaca());
		inventario.reservarCarro(carroSelecionado.getPlaca(), fechaRecogida, fechaDevolucion);
	}
	
	
	//METODOS PARA EL EMPLEADO
	public Empleado darEmpleado(String usuario)
	{
		return usuarios.retornarEmpleado(usuario);
	}
	
	public Reserva consultarReserva(String cedula, LocalDate dia)
	{
		Reserva reserva = gestorReservas.darReserva(cedula, dia);
		return reserva;
	}
	
	public void crearCliente(String usuario, String contraseña, String tipoUsuario, String nombre, String numeroDocumento, 
			String telefono, String correo, String fecha_nacimiento, String numeroLicencia, 
			String paisExpedición, String fechaVencimientoLicencia, 
			String numeroTarjeta, String codigoSeguridad, String fechaVencimientoTarjeta)
	{
		usuarios.crearCliente(usuario, contraseña, tipoUsuario, nombre, numeroDocumento, telefono, correo, fecha_nacimiento, numeroLicencia, paisExpedición, fechaVencimientoLicencia, numeroTarjeta, codigoSeguridad, fechaVencimientoTarjeta);
	}
	
	public void crearEmpleado(String usuario, String contraseña, String tipoUsuario, String nombre,
			String nombreSede)
	{
		usuarios.crearEmpleado(usuario, contraseña, tipoUsuario, nombre, nombreSede);
	}
	
	public void eliminarEmpleado(String usuario)
	{
		usuarios.eliminarEmpleado(usuario);
	}
	
	public void crearAdmin(String usuario, String contraseña, String tipoUsuario, String nombre,
			String nombreSede)
	{
		usuarios.crearAdminSede(usuario, contraseña, tipoUsuario, nombre, nombreSede);
	}
	
	public void eliminarAdminSede(String usuario)
	{
		usuarios.eliminarAdminSede(usuario);
	}
	
	public void crearSede(String nombreSede, String direccion)
	{
		sedes.crearSede(nombreSede, direccion);
	}
	public void eliminarSede(String nombreSede)
	{
		sedes.eliminarSede(nombreSede);
	}
	
	public double calcularPrecioAlquiler(int dias, String categoriaSeleccionada, 
			int numeroDeConductores, String sedeEntrega,
			String sedeDevolucion, String seguroSeleccionado)
	
	//precio se refiere al costo por categoría.
	//precio seguro tambien es por días.
	{
		
		//se saca el precio de la categoria y el seguro seleccionados.
		int precioCategoria = categorias.precioCategoría(categoriaSeleccionada);
		int precioSeguro = seguros.precioSeguro(seguroSeleccionado);
		
		int precioFinal = (precioCategoria*dias)+(precioSeguro*dias)+
				(numeroDeConductores * Conductor.getPrecioCondAdicional());
		
		if(!(sedeEntrega.equals(sedeEntrega)))
		{
			precioFinal = precioFinal + tarifaCambioSede.getTarifa();
		}
		
		return precioFinal;
	}
	
	public void alquilarVehiculo(String placaAuto, String cedulaCliente, LocalDate fechaRecogida, LocalDate fechaEntrega,
			String sedeRecogida, String sedeEntrega , List<Conductor> listaConductores, 
			String seguroSeleccionado)
	{
		//se crea el alquiler y el carro se hace no disponible en los días de alquiler
		gestorAlquileres.crearAlquiler(placaAuto, cedulaCliente, fechaRecogida, fechaEntrega, sedeRecogida, sedeEntrega, listaConductores);
		inventario.alquilarCarro(placaAuto, cedulaCliente,fechaRecogida, fechaEntrega);
		
		//se empieza a sacar la info para la factura

		Cliente cliente = darClienteCedula(cedulaCliente);
		Carro carro = inventario.buscarCarroPorPlaca(placaAuto);
		char categoriaCarro = carro.getCategoría();
		int precioDiaCategoria = categorias.precioCategoría(categoriaCarro+"");
		int numeroDeDias = (int)(ChronoUnit.DAYS.between(fechaRecogida, fechaEntrega));
		
		int precioSeguro = seguros.precioSeguro(seguroSeleccionado);
		
		int numeroConductores = listaConductores.size();
		int precioCondAdicional = Conductor.getPrecioCondAdicional();
		
		boolean diferenteSede = sedeRecogida.equals(sedeEntrega);
		int tarifaCambioSede1 = tarifaCambioSede.getTarifa();
		
		//se genera la factura y se guarda en el pdf
		
		PDFGenerator.generarFactura(placaAuto, fechaRecogida, cliente.getNombre(), 
				cliente.getNumeroDocumento(), 
				cliente.getTelefono(), cliente.getCorreo(), categoriaCarro, 
				precioDiaCategoria, numeroDeDias, seguroSeleccionado, precioSeguro, 
				numeroConductores, precioCondAdicional, diferenteSede, tarifaCambioSede1);
		
		
	}
	
	public void devolverVehiculo(String placa, boolean lavar, boolean 
			mantenimiento, String fechaDisponibleNuevamente)
	{
		inventario.devolverCarro(placa, lavar, lavar, fechaDisponibleNuevamente);
	}
	
	//METODOS ADMIN SEDE
	public Administrador_Sede darAdministrador_Sede(String usuario)
	{
		return usuarios.retornarAdminSede(usuario);
	}
	
	public Administrador_Principal darAdministrador_Principal(String usuario)
	{
		return usuarios.retornarAdminPrincipal(usuario);
	}
	
	public Map<String, String> darMapaSeguros()
	{
		return seguros.darMapaSeguros();
	}
	
	public Sede darSede(String sede)
	{
		return sedes.getSede(sede);
	}
	
	public InventarioCarros getInventario()
	{
		return inventario;
	}
	
	public Sedes getSedes()
	{
		return sedes;
	}
	
	public GestorReservas getGestorReservas()
	{
		return gestorReservas;
	}
	
	public GestorAlquileres getGestorAlquileres()
	{
		return gestorAlquileres;
	}
	
	public Categorias getCategorias()
	{
		return categorias;
	}
	
	public Usuarios getUsuarios()
	{
		return usuarios;
	}
	

}

