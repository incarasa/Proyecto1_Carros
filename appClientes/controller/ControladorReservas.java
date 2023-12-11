package controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;

import Inventario.VehiculoBase;
import model.Reserva;
import model.Usuario;
import model.Vehiculos.Vehiculo;

public class ControladorReservas
{
	private Reserva reserva;
	private static HashMap<String, Integer> tarifasCategoria;
	
	public Reserva crearReserva(Usuario usuario, List<String> informacionReserva, List<VehiculoBase> carrosDisponibles)
	{
		String categoria = informacionReserva.get(0);
		String sedeRecogida = informacionReserva.get(1);
		LocalDate fechaRecogida = stringToDate(informacionReserva.get(2));
		LocalTime horaRecogida = stringToTime(informacionReserva.get(3));
		String sedeDevolucion = informacionReserva.get(4);
		LocalDate fechaEntrega = stringToDate(informacionReserva.get(5));
		LocalTime horaEntrega = stringToTime(informacionReserva.get(6));
		String placaVehiculo = carrosDisponibles.get(0).getPlaca();
		double precio = calcularPrecioReserva(sedeRecogida,  sedeDevolucion,
			 fechaRecogida, fechaEntrega ,  categoria);
		reserva = new Reserva(horaRecogida, fechaRecogida, fechaEntrega, usuario.getNumeroDocumento(), precio,
				categoria, placaVehiculo);
		return reserva;
		
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
	public double calcularPrecioReserva(String nombreSedeRecogida, String nombreSedeDevolucion,
			LocalDate diaRecogida, LocalDate diaDevolucion , String categoria)
	{
		double precioTotal;

		int precioPorDia = tarifasCategoria.get(categoria); 
		
		int diasTranscurridos = (int)(ChronoUnit.DAYS.between(diaRecogida , diaDevolucion));
		
		precioTotal = diasTranscurridos * precioPorDia;
		
		if(!(nombreSedeRecogida.equals(nombreSedeDevolucion)) )
		{
			precioTotal = precioTotal + 20000;
		}
		
		precioTotal = precioTotal - (precioTotal*0.1);
		
		
		return precioTotal;
	
		
	}
	public static HashMap<String, Integer> getTarifasCategoria()
	{
		return tarifasCategoria;
	}
	public static void setTarifasCategoria(
			HashMap<String, Integer> tarifasCategoria)
	{
		ControladorReservas.tarifasCategoria = tarifasCategoria;
	}
}
