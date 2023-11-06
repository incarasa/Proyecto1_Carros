package Proyecto;

import java.time.LocalDate;
import java.time.Month;

import Inventario.Carro;
import Inventario.InventarioCarros;

public class RentACar 
{
	private InventarioCarros inventario;
	
	public RentACar()
	{
		this.inventario = new InventarioCarros();
	}
	
	public void agregarCarro(String placa, String marca, int modelo, String transmision, String categoría, String sede)
	{
		inventario.agregarCarro(placa, marca, modelo, transmision, categoría, sede);
	}
	
	public void cargarInformacion()
	{
		inventario.cargarCarrosDesdeCarpeta();
	}
	
	public void reservarCarro(String placa, LocalDate diaInicio, LocalDate diaFin)
	{
		inventario.reservarCarro(placa, diaInicio, diaFin);
	}
	public InventarioCarros getInventario()
	{
		return inventario;
	}
	
	public static void main(String[] args) 
	{
		RentACar app = new RentACar();
		InventarioCarros inventario = app.getInventario();
		
		app.cargarInformacion();
		
		//app.agregarCarro("MCU788", "KIA", 2008, "MECANICA", "A", "NORMADIA");
		
		LocalDate fecha1 = LocalDate.of(2023, Month.NOVEMBER, 20);
		LocalDate fecha2 = LocalDate.of(2023, Month.NOVEMBER, 30);

		
		//app.reservarCarro("MCU788", fecha1, fecha2);
		
		Carro carro1 = inventario.buscarCarroPorPlaca("MCU788");
		System.out.println(carro1.esReservable(fecha1, fecha2));

		
		
	}

}

