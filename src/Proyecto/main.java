package Proyecto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.formdev.flatlaf.FlatLightLaf;

import Alquiler.Alquiler;
import Alquiler.GestorAlquileres;
import Alquiler.GestorReservas;
import Alquiler.Reserva;
import Instalaciones.Sedes;
import Interfaz.InterfazPrincipal;
import Inventario.VehiculoBase;
import Inventario.InventarioCarros;
import Tarifas.Categorias;
import Tarifas.Conductor;
import Usuarios.Usuarios;

public class main 
{
	public static void main(String[] args) 
	{
		RentACar app = new RentACar();
		InventarioCarros inventario = app.getInventario();
		Sedes sedes = app.getSedes();
		GestorReservas gestorReservas = app.getGestorReservas();
		GestorAlquileres gestorAlquileres = app.getGestorAlquileres();
		
		app.cargarInformacion();  //este metodo carga todo, no tocarlo.
		
		//app.agregarCarro("HLT788", "FORD", 2015, "MECANICA", "A", "MODELIA");
		
		LocalDate fecha1 = LocalDate.of(2023, Month.NOVEMBER, 20);
		LocalDate fecha2 = LocalDate.of(2023, Month.NOVEMBER, 30);

		
		//app.reservarCarro("MCU788", fecha1, fecha2);
		
		VehiculoBase carro1 = inventario.buscarCarroPorPlaca("MCU788");
		System.out.println(carro1.esReservable(fecha1, fecha2));
		
		//borrar un carro
		//inventario.eliminarCarro("HLT788");
		
		//pruebas sedes
		//sedes.definirHorarioSede("NORMANDIA", DayOfWeek.SATURDAY, LocalTime.of(9, 0), LocalTime.of(14,0));
		
		//pruebas reservas
		
		LocalTime t1 = LocalTime.of(10, 0);
		
		//gestorReservas.crearReserva(t1, fecha1, fecha2, "1000271186", 30000000, "A", "FJK123");
		
		Map<String, List<Reserva>> mapaMap = gestorReservas.getMap();
		
		List<Reserva> listares = mapaMap.get("1000271186");
		Reserva reserva = listares.get(0);
		System.out.println(reserva.getPlacaVehiculo());
		
		//persistencia funciona bien
		
		//app.reservarCarro("SJM853", fecha1, fecha2);
		
		//PRUEBAS ALQUILERES
		Conductor conductor1 = new Conductor("Carlos Ramirez", "100029343", "Colombia", "10/20/2019");
		Conductor conductor2 = new Conductor("Juanito Perez", "1000234892", "Colombia", "10/20/2023");
		
		List<Conductor> listaConductores = new ArrayList<Conductor>();
		listaConductores.add(conductor1);
		listaConductores.add(conductor2);
		
		
		
		//gestorAlquileres.crearAlquiler("SJM89E", "1000271186", fecha1, fecha2, "NORMADIA" , "NORMANDIA" , listaConductores);
		
		Alquiler alquilerPrueba = gestorAlquileres.darAlquiler("1000271186");
		List<Conductor> listaConductors = alquilerPrueba.getListaConductores();
		for(Conductor conductor : listaConductors)
		{
			System.out.println(conductor.getNombre());
		}
		
		//prueba sobre categorias
		Categorias categorias = app.getCategorias();
		//categorias.añadirCategoría("A", "50000");
		//categorias.añadirCategoría("B", "60000");
		//categorias.añadirCategoría("C", "70000");
		categorias.añadirCategoría("D", "80000");
		Map<String, String> mapaCateg = categorias.darMapaCategorias();
		String precioA = mapaCateg.get("A");
		System.out.println(precioA);
		
		//categorias funcionan correctamente
		
		//prueba usuarios
		Usuarios usuarios = app.getUsuarios();
		//usuarios.crearAdminSede("admin1", "12345", "admin", "Pedro Sanchez", "NORMANDIA");
		//usuarios.crearAdminSede("admin2", "12345", "admin", "Juan Romero", "MODELIA");
		
		usuarios.verificar("principal1", "principal12345");
		
		//GUI
		FlatLightLaf.install();
		InterfazPrincipal ventanaPrincipal = new InterfazPrincipal();
		ventanaPrincipal.setVisible(true);
		
		//app.reservarCarro("NORMANDIA", "NORMANDIA", LocalDate.of(2023, 11, 03), 
				//LocalDate.of(2023, 11, 07), LocalTime.of(12,0), LocalTime.of(12, 0), "C".charAt(0));
		
		
		
	}
}
