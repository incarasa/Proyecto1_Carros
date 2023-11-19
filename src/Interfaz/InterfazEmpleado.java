package Interfaz;

import java.awt.BorderLayout;

import javax.swing.*;

import Proyecto.RentACar;
import Usuarios.Empleado;

public class InterfazEmpleado extends JFrame
{

	private RentACar aplicacion;
	private empPanelOpciones empPanelOpciones; //panel opciones empleado
	private Empleado empleado;
	
	private VentanaAlquilar ventanaAlquilar;
	
	public InterfazEmpleado(RentACar aplicacion, Empleado empleado)
	{
		this.aplicacion = aplicacion;
		this.empleado = empleado;
		
		setLayout(new BorderLayout());
		
		setTitle("Empleado");
		setSize(1000,700);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		empPanelOpciones = new empPanelOpciones(this , empleado);
		add(empPanelOpciones, BorderLayout.CENTER);
	}
	
	
	public void alquilarVehiculo()
	{
		ventanaAlquilar = new VentanaAlquilar(aplicacion , empleado);
		ventanaAlquilar.setVisible(true);
	}
	
	//metodo para cerrar la ventana
	public void cerrarVentana()
	{
		dispose();
	}
	
	public void devolverCarro(String placa, boolean lavar, boolean mantenimiento,
			String fechaDisponibleNuevamente)
	{
		aplicacion.devolverVehiculo(placa, lavar, mantenimiento, fechaDisponibleNuevamente);
		
	}
}
