package Proyecto;

import java.io.ObjectInputStream.GetField;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Map;

import Alquiler.GestorReservas;
import Alquiler.Reserva;
import Instalaciones.Sedes;
import Inventario.Carro;
import Inventario.InventarioCarros;

public class RentACar 
{
	private InventarioCarros inventario;
	private Sedes sedes;
	private GestorReservas gestorReservas;
	
	public RentACar()
	{
		this.inventario = new InventarioCarros();
		this.sedes = new Sedes();
		this.gestorReservas = new GestorReservas();
	}
	
	public void agregarCarro(String placa, String marca, int modelo, String transmision, String categoría, String sede)
	{
		inventario.agregarCarro(placa, marca, modelo, transmision, categoría, sede);
	}
	
	public void cargarInformacion()
	{
		inventario.cargarCarrosDesdeCarpeta();
		sedes.cargarSedesMapa();
		gestorReservas.cargarReservasDesdeCSV();
	}
	
	public void reservarCarro(String placa, LocalDate diaInicio, LocalDate diaFin)
	{
		inventario.reservarCarro(placa, diaInicio, diaFin);
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
	
	
	public static void main(String[] args) 
	{
		RentACar app = new RentACar();
		InventarioCarros inventario = app.getInventario();
		Sedes sedes = app.getSedes();
		GestorReservas gestorReservas = app.getGestorReservas();
		
		app.cargarInformacion();  //este metodo carga todo, no tocarlo.
		
		//app.agregarCarro("HLT788", "FORD", 2015, "MECANICA", "A", "MODELIA");
		
		LocalDate fecha1 = LocalDate.of(2023, Month.NOVEMBER, 20);
		LocalDate fecha2 = LocalDate.of(2023, Month.NOVEMBER, 30);

		
		//app.reservarCarro("MCU788", fecha1, fecha2);
		
		Carro carro1 = inventario.buscarCarroPorPlaca("MCU788");
		System.out.println(carro1.esReservable(fecha1, fecha2));
		
		//borrar un carro
		//inventario.eliminarCarro("HLT788");
		
		//pruebas sedes
		//sedes.definirHorarioSede("NORMANDIA", DayOfWeek.SATURDAY, LocalTime.of(9, 0), LocalTime.of(14,0));
		
		//pruebas reservas
		
		LocalTime t1 = LocalTime.of(10, 0);
		
		//gestorReservas.crearReserva(t1, fecha1, fecha2, "1000271186", 30000000, "A", "SJM89E");
		
		Map<String, List<Reserva>> mapaMap = gestorReservas.getMap();
		
		List<Reserva> listares = mapaMap.get("1000271186");
		Reserva reserva = listares.get(0);
		System.out.println(reserva.getPlacaVehiculo());
		
		//persistencia funciona bien
		
		
		
	}

}

