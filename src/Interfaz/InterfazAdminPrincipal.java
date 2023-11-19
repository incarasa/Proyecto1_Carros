package Interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Instalaciones.Sede;
import Proyecto.RentACar;
import Usuarios.Administrador_Principal;
import Usuarios.Administrador_Sede;
import Usuarios.Empleado;

public class InterfazAdminPrincipal extends JFrame
{
	private RentACar aplicacion;
	private Administrador_Principal administrador_Principal;
	
	//PANELES
	PanelAdminPrincipal panelAdminPrincipal;
	PanelAdminPrincipalBotones panelAdminPrincipalBotones;
	
	public InterfazAdminPrincipal(RentACar aplicacion, Administrador_Principal admin)
	{
		this.aplicacion = aplicacion;
		this.administrador_Principal = admin;
		
		setTitle("Interfaz Administrador Principal");
		setSize(1000,700);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//layout 
		setLayout(new BorderLayout());
		
		//PANELES
		panelAdminPrincipal = new PanelAdminPrincipal(this, administrador_Principal);
		panelAdminPrincipalBotones = new PanelAdminPrincipalBotones(this);
		
		add(panelAdminPrincipal, BorderLayout.CENTER);
		add(panelAdminPrincipalBotones, BorderLayout.SOUTH);
		
	}
	
	public void buscarAdmin(String usuario)
	{
		Administrador_Sede admin =  aplicacion.darAdministrador_Sede(usuario);
		if(admin == null)
		{
			panelAdminPrincipal.setEstado("El administrador no existe, puede crearlo oprimiendo el botón "
					+ "de abajo y llenando los campos adicionales.");
			
			panelAdminPrincipalBotones.activarBtnCrearAdmin(true);
		}
		else
		{
			panelAdminPrincipal.setEstado("El administrador existe, puede eliminarlo oprimiendo el botón de abajo");
			panelAdminPrincipalBotones.activarBtnEliminarAdmin(true);
		}
		
	}
	
	public void buscarSede(String sedeStr)
	{
		Sede sede =  aplicacion.darSede(sedeStr);
		if(sede == null)
		{
			panelAdminPrincipal.setEstado("La sede no existe, puede crearla oprimiendo el botón "
					+ "de abajo y llenando los campos adicionales.");
			
			panelAdminPrincipalBotones.activarBtnCrearSede(true);
		}
		else
		{
			panelAdminPrincipal.setEstado("La sede existe, puede eliminarla oprimiendo el botón de abajo");
			panelAdminPrincipalBotones.activarBtnEliminarSede(true);
		}
		
	}
	
	public void mostrarCrearAdmin(boolean estado)
	{
		panelAdminPrincipal.mostrarCrearAdmin(estado);
		panelAdminPrincipal.mostrarBuscarAdmin(false);
	}

	public void mostrarCrearSede(boolean estado)
	{
		panelAdminPrincipal.mostrarCrearSede(estado);
		panelAdminPrincipal.mostrarBuscarAdmin(false);
	}

	/**
	 * Se crea el nuevo empleado en la sede del admin y con el tipo "empleado".
	 * @param usuario
	 * @param contraseña
	 * @param nombre
	 */
	public void crearAdmin(String usuario, String contraseña, String nombre, String sede)
	{
		aplicacion.crearAdmin(usuario, contraseña, "empleado", nombre, sede);
		
		panelAdminPrincipal.setEstado("");
		panelAdminPrincipal.mostrarBuscarAdmin(true);
		panelAdminPrincipal.mostrarCrearAdmin(false);

		
		JOptionPane.showMessageDialog( this , "El administrador fue creado con éxito" ,"Nuevo administrador" , 
				JOptionPane.INFORMATION_MESSAGE );
	}
	
	public void eliminarAdmin()
	{
		String usuario = panelAdminPrincipal.darUsuario();
		
		aplicacion.eliminarAdminSede(usuario);
		
		panelAdminPrincipal.setEstado("");
		
		JOptionPane.showMessageDialog( this , "El administrador fue eliminado con éxito" ,"Eliminar empleado" , 
				JOptionPane.INFORMATION_MESSAGE );
		
	}
	
	public void crearSede(String nombreSede, String direccion)
	{
		aplicacion.crearSede(nombreSede, direccion);
		
		panelAdminPrincipal.setEstado("");
		panelAdminPrincipal.mostrarBuscarAdmin(true);
		panelAdminPrincipal.mostrarCrearSede(false);

		
		JOptionPane.showMessageDialog( this , "Sede creada con éxito" ,"Nueva sede" , 
				JOptionPane.INFORMATION_MESSAGE );
	}
	
	public void eliminarSede()
	{
		String sede = panelAdminPrincipal.darSede(); //nombre sede
		
		aplicacion.eliminarSede(sede);
		
		panelAdminPrincipal.setEstado("");
		
		JOptionPane.showMessageDialog( this , "La sede fue eliminada con éxito" ,"Eliminar sede" , 
				JOptionPane.INFORMATION_MESSAGE );
		
	}
	public void ventanaInventario()
	{
		VentanaAdminCarros ventanaAdminCarros = new VentanaAdminCarros(aplicacion);
		ventanaAdminCarros.setVisible(true);
	}
	

}
