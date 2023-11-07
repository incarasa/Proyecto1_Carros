package Proyecto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import Instalaciones.Sedes;
import Inventario.Carro;
import Inventario.InventarioCarros;

public class RentACar 
{
	private InventarioCarros inventario;
	private Sedes sedes;
	
	public RentACar()
	{
		this.inventario = new InventarioCarros();
		this.sedes = new Sedes();
	}
	
	public void agregarCarro(String placa, String marca, int modelo, String transmision, String categoría, String sede)
	{
		inventario.agregarCarro(placa, marca, modelo, transmision, categoría, sede);
	}
	
	public void cargarInformacion()
	{
		inventario.cargarCarrosDesdeCarpeta();
		sedes.cargarSedesMapa();
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
	
	
	public static void main(String[] args) 
	{
		RentACar app = new RentACar();
		InventarioCarros inventario = app.getInventario();
		Sedes sedes = app.getSedes();
		
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
		sedes.definirHorarioSede("NORMANDIA", DayOfWeek.SATURDAY, LocalTime.of(9, 0), LocalTime.of(14,0));
		
		
		
	}

}

