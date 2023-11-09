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
		
		String[] arrayCategorias =  aplicacion.darCategorias();
		//le paso las categorias para el JBox
		panelOpciones = new icPanelOpciones(arrayCategorias);
		add(panelOpciones, BorderLayout.CENTER);
		
		
	}
}
