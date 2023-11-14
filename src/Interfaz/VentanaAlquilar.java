package Interfaz;

import java.awt.BorderLayout;
import java.time.LocalDate;

import javax.print.attribute.standard.Sides;
import javax.swing.JFrame;

import Alquiler.Reserva;
import Proyecto.RentACar;
import Usuarios.Cliente;
import Usuarios.Empleado;

public class VentanaAlquilar extends JFrame
{
	/*
	 * El flujo es el siguiente, si no existe el cliente se abre la ventana del cliente.
	 * Esta ventana se cierra apenas se crea el cliente
	 * Si el cliente existe entonces, se pasa a otra ventana (ventanaCliente con reserva)
	 * 
	 */
	
	private RentACar aplicacion;
	private PanelAlquiler panelAlquiler; 
	private PanelAlquilerBotones panelBotones;
	private Empleado empleado;
	private boolean alquilo = false; 
	
	//VENTANAS
	private VentanaClienteConReserva ventanaReserva;
	private ventanaCliente ventanaCliente;
	
	public VentanaAlquilar(RentACar aplicacion, Empleado empleado)
	{
		this.aplicacion = aplicacion;
		this.empleado = empleado;
		
		setLayout(new BorderLayout());
		
		setTitle("Empleado");
		setSize(1000,700);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		panelAlquiler = new PanelAlquiler(this);
		add(panelAlquiler, BorderLayout.CENTER);
		
		panelBotones = new PanelAlquilerBotones(this);
		add(panelBotones, BorderLayout.SOUTH);
		
		
	}
	
	public void consultarReserva(String cedula)
	{
		//primero mirar si el cliente existe
		Cliente cliente = aplicacion.darClienteCedula(cedula);
		
		if(cliente == null)
		{
			panelAlquiler.setEstado("El cliente no existe. Por favor creélo con el botón de abajo.");
			panelBotones.estadoBtnCrearCliente(true);
		}
		
		//ahora mirar la reserva si existe
		else 
		{
			Reserva reserva = aplicacion.consultarReserva(cedula, LocalDate.now());
			
			if(reserva == null)
			{
				panelAlquiler.setEstado("El cliente no ha reservado un vehiculo hoy");
				panelBotones.estadoBtnAlquilarVehiculo(true);
				
			}
			else 
			{
				panelAlquiler.setEstado("El cliente ha reservado un vehiculo para hoy");
				panelBotones.estadoBtnAlquilarVehiculo(true);
				alquilo = true;
				
				//NUEVA VENTANA
				ventanaReserva = 
					new VentanaClienteConReserva(aplicacion, cliente, reserva , empleado);
					ventanaReserva.setVisible(true);
				
			}
		}
		
	}
	
	public void cerrarVentana()
	{
		dispose();
	}
	
	public void ventanaCliente()
	{
		
		ventanaCliente =  new ventanaCliente(aplicacion, empleado);
		ventanaCliente.setVisible(true);
	}
	
	public boolean alquilo()
	{
		return alquilo;
	}
}

