package Interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class VentanaAñadirConductor extends JFrame
{
	private PanelAñadirConductor panelAñadirConductor;
	
	public VentanaAñadirConductor()
	{
		setTitle("Seguros");
		setSize(800,600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(new BorderLayout());
	}
	
	public void añadirConductor()
	{
		
	}
}
