package Interfaz;

import java.awt.BorderLayout;

import javax.swing.*;

import Proyecto.RentACar;

public class InterfazPrincipal extends JFrame
{
	private RentACar aplicacion;
	
	private JPanel panelCentral;
	
	
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
		aplicacion.autenticar(usuario, contraseña);
	}
}
