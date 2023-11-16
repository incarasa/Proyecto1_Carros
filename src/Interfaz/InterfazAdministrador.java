package Interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Proyecto.RentACar;
import Usuarios.Administrador_Principal;
import Usuarios.Administrador_Sede;
import Usuarios.Empleado;

public class InterfazAdministrador extends JFrame
{
	private RentACar aplicacion;
	private Administrador_Sede administrador_Sede;
	
	//PANELES
	PanelAdministrador panelAdministrador;
	PanelAdministradorBotones panelAdministradorBotones;
	
	public InterfazAdministrador(RentACar aplicacion, Administrador_Sede admin)
	{
		this.aplicacion = aplicacion;
		this.administrador_Sede = admin;
		
		setTitle("Interfaz Administrador");
		setSize(1000,700);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//layout 
		setLayout(new BorderLayout());
		
		//PANELES
		panelAdministrador = new PanelAdministrador(this, admin);
		panelAdministradorBotones = new PanelAdministradorBotones(this);
		
		add(panelAdministrador, BorderLayout.CENTER);
		add(panelAdministradorBotones, BorderLayout.SOUTH);
		
	}
	
	public void buscarEmpleado(String usuario)
	{
		Empleado empleado =  aplicacion.darEmpleado(usuario);
		if(empleado == null)
		{
			panelAdministrador.setEstado("El empleado no existe, puede crearlo oprimiendo el botón "
					+ "de abajo y llenando los campos adicionales.");
			
			panelAdministradorBotones.activarBtnCrearEmpleado(true);
		}
		else
		{
			panelAdministrador.setEstado("El empleado existe, puede eliminarlo oprimiendo el botón de abajo");
			panelAdministradorBotones.activarBtnEliminarEmpleado(true);
		}
		
	}
	
	public void mostrarCrearEmpleado(boolean estado)
	{
		panelAdministrador.mostrarCrearEmpleado(estado);
		panelAdministrador.mostrarBuscarEmpleado(false);
	}

	/**
	 * Se crea el nuevo empleado en la sede del admin y con el tipo "empleado".
	 * @param usuario
	 * @param contraseña
	 * @param nombre
	 */
	public void crearEmpleado(String usuario, String contraseña, String nombre)
	{
		aplicacion.crearEmpleado(usuario, contraseña, "empleado", nombre, 
				administrador_Sede.getNombreSede());
		
		panelAdministrador.setEstado("");
		panelAdministrador.mostrarBuscarEmpleado(true);
		panelAdministrador.mostrarCrearEmpleado(false);

		
		JOptionPane.showMessageDialog( this , "El empleado fue creado con éxito" ,"Nuevo empleado" , 
				JOptionPane.INFORMATION_MESSAGE );
	}
	
	public void eliminarEmpleado()
	{
		String usuario = panelAdministrador.darUsuario();
		
		aplicacion.eliminarEmpleado(usuario);
		
		panelAdministrador.setEstado("");
		
		JOptionPane.showMessageDialog( this , "El empleado fue eliminado con éxito" ,"Eliminar empleado" , 
				JOptionPane.INFORMATION_MESSAGE );
		
	}
}
