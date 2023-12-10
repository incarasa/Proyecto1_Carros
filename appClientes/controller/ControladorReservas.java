package controller;

import java.util.List;

import model.Reserva;
import model.Sede;
import model.Usuario;
import model.Vehiculo;

public class ControladorReservas
{
	private Reserva reserva;
	public Reserva crearReserva(Usuario usuario, List<String> informacionReserva)
	{
		String categoria = informacionReserva.get(0);
		String sedeRecogida = informacionReserva.get(0);
		String fechaRecogida = informacionReserva.get(0);
		String horaRecogida = informacionReserva.get(0);
		String sedeDevolucion = informacionReserva.get(0);
		String fechaEntrega = informacionReserva.get(0);
		String horaEntrega = informacionReserva.get(0);
		
		return reserva;
	}
}
