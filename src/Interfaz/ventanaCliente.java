package Interfaz;

import java.awt.BorderLayout;


import javax.swing.JFrame;

import Proyecto.RentACar;
import Usuarios.Empleado;

public class ventanaCliente extends JFrame
{
	private RentACar aplicacion;
	private PanelCrearCliente panelCrearCliente; 

	public ventanaCliente(RentACar aplicacion)
	{
		this.aplicacion = aplicacion;
		
		setLayout(new BorderLayout());
		
		setTitle("Empleado");
		setSize(1000,700);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		panelCrearCliente = new PanelCrearCliente(this);
		add(panelCrearCliente, BorderLayout.CENTER);
	}
	
	public void crearCliente(String usuario, String contraseña, String tipoUsuario, String nombre, String numeroDocumento,
			String telefono, String correo, String fecha_nacimiento, String numeroLicencia, String paisExpedición,
			String fechaVencimientoLicencia, String numeroTarjeta, String codigoSeguridad,
			String fechaVencimientoTarjeta)
	{
		aplicacion.crearCliente(usuario, contraseña, tipoUsuario, nombre, numeroDocumento, telefono, correo, fecha_nacimiento, numeroLicencia, paisExpedición, fechaVencimientoLicencia, numeroTarjeta, codigoSeguridad, fechaVencimientoTarjeta);
	}

	public void cerrarVentana()
	{
		dispose();
	}
		
}
