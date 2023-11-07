package Alquiler;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva 
{
	private LocalTime horaLlegada;
	private LocalDate diaInicio;
	private LocalDate diaFin;
	
	private String documentoCliente;
	private int Precio;
	private String categoria;
	private String placaVehiculo;
	
	public Reserva(LocalTime horaLlegada, LocalDate diaInicio, LocalDate diaFin, String documentoCliente, int precio,
			String categoria, String placaVehiculo) 
	{
		this.horaLlegada = horaLlegada;
		this.diaInicio = diaInicio;
		this.diaFin = diaFin;
		this.documentoCliente = documentoCliente;
		Precio = precio;
		this.categoria = categoria;
		this.placaVehiculo = placaVehiculo;
	}

	
	
	//GETTERS
	public LocalTime getHoraLlegada() {
		return horaLlegada;
	}

	public LocalDate getDiaInicio() {
		return diaInicio;
	}

	public LocalDate getDiaFin() {
		return diaFin;
	}

	public String getDocumentoCliente() {
		return documentoCliente;
	}

	public int getPrecio() {
		return Precio;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getPlacaVehiculo() {
		return placaVehiculo;
	}

	
}
