package Interfaz;

import javax.swing.*;

import Proyecto.RentACar;

public class InterfazCliente extends JFrame
{
	private RentACar aplicacion;
	
	public InterfazCliente(RentACar aplicacion)
	{
		this.aplicacion = aplicacion;
		
		setTitle("Cliente");
		setSize(1000,700);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
}
