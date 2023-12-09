package Interfaz;

import java.awt.BorderLayout;
import java.awt.Window;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Alquiler.Reserva;
import Inventario.Carro;
import Proyecto.RentACar;
import Tarifas.Conductor;
import Usuarios.Cliente;
import Usuarios.Empleado;

public class VentanaConfirmarAlquiler extends JFrame
{
	private RentACar aplicacion;
	private PanelConfirmarAlquiler panelConfirmarAlquiler;
	private Reserva reserva;
	private Cliente cliente;
	private Empleado empleado;
	private String seguroSeleccionado;
	
	private LocalDate fechaEntregaAjustada;
	private String sedeEntregaAjustada;
	
	private List<Conductor> listaConductores;
	
	public VentanaConfirmarAlquiler(RentACar aplicacion, double precio, Empleado empleado, 
			Reserva reserva, List<Conductor> listaConductores, LocalDate fechaEntregaAjustada,
			String sedeEntregaAjustada, String seguroSeleccionado)
	{
		this.aplicacion = aplicacion;
		this.empleado = empleado;
		this.listaConductores = listaConductores;
		this.reserva = reserva;
		this.fechaEntregaAjustada = fechaEntregaAjustada;
		this.sedeEntregaAjustada = sedeEntregaAjustada;
		this.seguroSeleccionado = seguroSeleccionado;
		
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
	
	//funcion para alquilar un vehículo.
	
	public void alquilar(double precioFinal)
	{
		aplicacion.alquilarVehiculo(reserva.getPlacaVehiculo(), reserva.getDocumentoCliente(), 
				reserva.getDiaInicio(), fechaEntregaAjustada, empleado.getNombreSede(),
				sedeEntregaAjustada, 
				listaConductores, seguroSeleccionado);
		
		JOptionPane.showMessageDialog(this, "Alquiler realizado con éxito",
				"Reserva exitosa", JOptionPane.INFORMATION_MESSAGE);
		
		dispose();
	}
	
}
