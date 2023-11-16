package Interfaz;

import java.awt.BorderLayout;

import javax.swing.*;

import Proyecto.RentACar;
import Usuarios.Administrador_Sede;
import Usuarios.Cliente;
import Usuarios.Empleado;
import Usuarios.Usuarios;

public class InterfazPrincipal extends JFrame
{
	private RentACar aplicacion;
	private ipPanelCentral panelCentral;
	
	private JFrame interfazCliente;
	private JFrame interfazEmpleado;
	private JFrame interfazAdminSede;
	private JFrame interfazPrincipal;
	
	
	
	public InterfazPrincipal()
	{
		setTitle("Rent A Car");
		setSize(1000,700);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//aplicacion
		aplicacion = new RentACar();
		aplicacion.cargarInformacion(); //IMPORTANTISIMO NO TOCAR
		
		//layout 
		setLayout(new BorderLayout());
		
		this.panelCentral = new ipPanelCentral(this);
		add(panelCentral, BorderLayout.CENTER);
		
	}
	
	public void validarInicioSesion(String usuario, String contraseña)
	{
		//Este artributo sera 
		// 1 - cliente
		// 2 - empleado
		// 3 - admin
		// 4 - principal
		// 0 - usuario no existe
		// 10 - contraseña errada
		
		int tipoUsuario = aplicacion.autenticar(usuario, contraseña);
		if(tipoUsuario == 1)
		{
			Cliente cliente = aplicacion.darCliente(usuario);
			interfazCliente = new InterfazCliente(aplicacion , cliente);
			interfazCliente.setVisible(true);
		}
		else if(tipoUsuario == 2)
		{
			Empleado empleado = aplicacion.darEmpleado(usuario);
			interfazEmpleado = new InterfazEmpleado(aplicacion , empleado);
			interfazEmpleado.setVisible(true);
		}
		else if(tipoUsuario == 3)
		{
			Administrador_Sede administrador_Sede = aplicacion.darAdministrador_Sede(usuario);
			interfazAdminSede = new InterfazAdministrador(aplicacion, administrador_Sede);
			interfazAdminSede.setVisible(true);
		}
		else if(tipoUsuario == 0)
		{
			panelCentral.setTextEstado("El usuario no existe");
		}
		else if(tipoUsuario == 10)
		{
			panelCentral.setTextEstado("Contraseña incorrecta");
		}	
		
	}
	
	
	
	
}
