package Interfaz;

import java.awt.BorderLayout;
import java.time.LocalDate;

import javax.swing.JFrame;

import Alquiler.Reserva;
import Proyecto.RentACar;
import Usuarios.Cliente;

public class VentanaClienteConReserva extends JFrame
{
	private RentACar aplicacion;
	private PanelOpcionesAlquiler panelOpcionesAlquiler; //crear
	private Cliente cliente;
	private Reserva reserva;
	
	private LocalDate fechaEntregaAjustada;
	private String sedeEntregaAjustada;
	
	
	public VentanaClienteConReserva(RentACar aplicacion,Cliente cliente, Reserva reserva)
	{
		setLayout(new BorderLayout());
		
		setTitle("Alquilar vehiculo");
		setSize(1000,700);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		this.aplicacion = aplicacion;
		
		String[] arraySedes = aplicacion.darSedes();
		
		this.panelOpcionesAlquiler = new PanelOpcionesAlquiler(this, arraySedes);
		add(panelOpcionesAlquiler, BorderLayout.CENTER);
		
	}


	public void continuarASeguros()
	{
		VentanaSeguros ventanaSeguros = new VentanaSeguros(aplicacion , cliente, reserva,
				fechaEntregaAjustada, sedeEntregaAjustada);
		ventanaSeguros.setVisible(true);
		cerrarVentana();
	}
	
	
	public void setFechaEntrega(LocalDate fechaEntrega) 
	{
		this.fechaEntregaAjustada = fechaEntrega;
	}


	public void setSedeEntrega(String sedeEntrega) {
		this.sedeEntregaAjustada = sedeEntrega;
	}
	
	public void cerrarVentana()
	{
		dispose();
	}
	
	
}
