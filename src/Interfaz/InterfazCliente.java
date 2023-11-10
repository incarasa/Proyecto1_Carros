package Interfaz;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.*;

import Proyecto.RentACar;

public class InterfazCliente extends JFrame
{
	private RentACar aplicacion;
	private icPanelOpciones panelOpciones;
	
	public InterfazCliente(RentACar aplicacion)
	{
		this.aplicacion = aplicacion;
		
		setLayout(new BorderLayout());
		
		setTitle("Cliente");
		setSize(1000,700);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//Acá tengo que crear los arrays para los JBox de el panel.
		String[] arrayCategorias =  aplicacion.darCategorias();
		String[] arraySedes = aplicacion.darSedes();
		
		panelOpciones = new icPanelOpciones(arrayCategorias , arraySedes);
		add(panelOpciones, BorderLayout.EAST);
		
		
	}
}
