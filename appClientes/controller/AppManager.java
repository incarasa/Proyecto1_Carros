package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Inventario.VehiculoBase;
import model.*;
import persistencia.Persistencia;
import view.VentanaPrincipal;

public class AppManager
{
	private VentanaPrincipal ventanaPrincipal;
	private ControladorUsuario sistemaUsuarios = new ControladorUsuario();
	private ControladorReservas sistemaReservas = new ControladorReservas();
	private Persistencia sistemaPersistencia;
	private HashMap<String, VehiculoBase> vehiculos;
	private HashMap<String, Sede> sedes;
	private HashMap<String, Reserva> reservas;
	private HashMap<String, Usuario> usuarios;
	private Usuario usuario;

	public void iniciarAplicacion()
	{
		ventanaPrincipal = new VentanaPrincipal(this);
	}
	public void registrarUsuario(String usuario2, String contraseña,
				String tipoUsuario, String nombre, String numeroDocumento,
				String telefono, String correo, String fecha_nacimiento,
				String numeroLicencia, String paisExpedición,
				String fechaVencimientoLicencia, String numeroTarjeta,
				String codigoSeguridad, String fechaVencimientoTarjeta)
		{
			sistemaUsuarios.registrarUsuario(usuario2, contraseña, tipoUsuario,
					nombre, numeroDocumento, telefono, correo, fecha_nacimiento,
					numeroLicencia, paisExpedición, fechaVencimientoLicencia,
					numeroTarjeta, codigoSeguridad, fechaVencimientoTarjeta);
			usuarios.put(usuario.getUsuario(), usuario);
		
		sistemaPersistencia.guardarUsuarios();
	}
	public boolean comprobarUsuario(String login, String password)
	{
		usuario = usuarios.get(login);
		return (usuario.getContraseña().equals(password));

	}
	public void consultarDisponibilidad(String sede)
	{

	}
	public Reserva crearReserva(ArrayList<String> informacionReserva)
	{
		Reserva reserva =null;
		String categoria = informacionReserva.get(0);
		Sede sedeRecogida = sedes.get(informacionReserva.get(1));
		String fechaRecogidaStr = informacionReserva.get(2);
		String horaRecogidaStr = informacionReserva.get(3);
		Sede sedeDevolucion = sedes.get(informacionReserva.get(4));
		String fechaDevolucionStr = informacionReserva.get(5);
		String horaDevolucionStr = informacionReserva.get(6);

		LocalDate fechaRecogida = stringToDate(fechaRecogidaStr);
		LocalTime horaRecogida = stringToTime(horaRecogidaStr);
		LocalDate fechaDevolucion = stringToDate(fechaDevolucionStr);
		LocalTime horaDevolucion = stringToTime(horaDevolucionStr);

		boolean sede1IsAbierta = sedeRecogida
				.estaAbierta(fechaRecogida.getDayOfWeek(), horaRecogida);
		boolean sede2IsAbierta = sedeDevolucion
				.estaAbierta(fechaDevolucion.getDayOfWeek(), horaDevolucion);

		if (sede1IsAbierta && sede2IsAbierta)
		{
			List<VehiculoBase> carrosDisponibles = carrosDisponibles(
					sedeRecogida.getNombre(), fechaRecogida, fechaDevolucion,
					categoria);
			
			reserva = sistemaReservas.crearReserva(usuario, informacionReserva, carrosDisponibles);
			return reserva;
		}
		sistemaPersistencia.guardarReservas();
		return reserva;
	}
	private List<VehiculoBase> carrosDisponibles(String sede,
			LocalDate fechaRecogida, LocalDate fechaDevolucion,
			String categoria)
	{

		List<VehiculoBase> listaDisponibles = carrosDisponiblesEnSede(sede);

		listaDisponibles = DisponiblesEnFechas(listaDisponibles, fechaRecogida,
				fechaDevolucion);


		return listaDisponibles;

	}
	public List<VehiculoBase> DisponiblesEnFechas(List<VehiculoBase> carros,
			LocalDate fechaInicio, LocalDate fechaFin)
	{

		ArrayList<VehiculoBase> carrosDisponibles = new ArrayList<VehiculoBase>();

		for (VehiculoBase carro : carros)
		{
			if (carro.esReservable(fechaInicio, fechaFin))
			{

				carrosDisponibles.add(carro);
			}
		}
		return carrosDisponibles;
	}
	public List<VehiculoBase> carrosDisponiblesEnSede(String sede)
	{
		List<VehiculoBase> carrosDisponibles = new ArrayList<>();

		for (VehiculoBase carro : vehiculos.values())
		{
			if ((!carro.isAlquilado()) && (carro.isDisponible())
					&& ((carro.getSede()).equals(sede)))
			{
				carrosDisponibles.add(carro);
			}
		}

		return carrosDisponibles;

	}
	public void cargarInformacion() throws FileNotFoundException, IOException
	{
		sistemaPersistencia = new Persistencia(this);
		sistemaPersistencia.cargarInformacion();

	}

	public LocalDate stringToDate(String fechaStr)
	{
		LocalDate fecha = null;
		String[] partes = fechaStr.split("/");
		int año = Integer.parseInt(partes[2]);
		int mes = Integer.parseInt(partes[1]);
		int dia = Integer.parseInt(partes[0]);
		fecha = LocalDate.of(año, mes, dia);
		return fecha;

	}
	public LocalTime stringToTime(String tiempoStr)
	{
		String[] partes = tiempoStr.split(":");
		int hora = Integer.parseInt(partes[0]);
		int minuto = Integer.parseInt(partes[1]);

		return LocalTime.of(hora, minuto);
	}
	public String[] getArraySedes()
	{
		Set<String> sedesList = sedes.keySet();
		String[] arraySedes = sedesList.toArray(new String[0]);
		return arraySedes;
	}

	public HashMap<String, VehiculoBase> getVehiculos()
	{
		return vehiculos;
	}
	public HashMap<String, Sede> getSedes()
	{
		return sedes;
	}
	public HashMap<String, Reserva> getReservas()
	{
		return reservas;
	}
	public HashMap<String, Usuario> getUsuarios()
	{
		return usuarios;
	}
	public Usuario getUsuario()
	{
		return usuario;
	}
	public void setVehiculos(Map<String, Inventario.VehiculoBase> map)
	{
		this.vehiculos = (HashMap<String, Inventario.VehiculoBase>) map;
	}
	public void setSedes(HashMap<String, model.Sede> sedes)
	{
		this.sedes = sedes;
	}
	public void setReservas(HashMap<String, Reserva> reservas)
	{
		this.reservas = reservas;
	}
	public void setUsuarios(HashMap<String, Usuario> usuarios)
	{
		this.usuarios = usuarios;
	}
	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}
	public void guardarInformacion()
	{

	}
}
