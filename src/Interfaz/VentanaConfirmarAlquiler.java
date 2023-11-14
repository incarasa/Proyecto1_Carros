package Interfaz;

import java.awt.BorderLayout;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JFrame;

import Alquiler.Reserva;
import Inventario.Carro;
import Proyecto.RentACar;
import Tarifas.Conductor;
import Usuarios.Cliente;

public class VentanaConfirmarAlquiler extends JFrame
{
	private RentACar aplicacion;
	private PanelConfirmarAlquiler panelConfirmarAlquiler;
	private Reserva reserva;
	private Cliente cliente;
	
	private LocalDate fechaEntregaAjustada;
	private String sedeEntregaAjustada;
	
	private List<Conductor> listaConductores;
	
	public VentanaConfirmarAlquiler(RentACar aplicacion, double precio)
	{
		this.aplicacion = aplicacion;
		
		setTitle("Confirmar alquiler");
		setSize(1000,700);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(new BorderLayout());
		
		this.panelConfirmarAlquiler = new PanelConfirmarAlquiler(this, precio, 
				reserva.getPrecio());
		add(panelConfirmarAlquiler, BorderLayout.CENTER);
		
		
	}
	
	public void alquilar(double precioFinal)
	{
		aplicacion.alquilarVehiculo(reserva.getPlacaVehiculo(), reserva.getDocumentoCliente(), reserva.getDiaInicio(), fechaEntregaAjustada, "NORMANDIA", sedeEntregaAjustada, listaConductores);
	}
	
}
