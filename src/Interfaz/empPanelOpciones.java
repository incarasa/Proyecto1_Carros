package Interfaz;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import Usuarios.Empleado;

public class empPanelOpciones extends JPanel implements ActionListener
{
	private JLabel labSaludo = new JLabel("");
	private JButton btnAlquilarVehiculo = new JButton("Alquilar Vehiculo");
	private JButton btnRecibirVehiculo = new JButton("Recibir Vehiculo");
	private JButton btnCancelar = new JButton("Cancelar");
	
	private InterfazEmpleado ventanaEmpleado;
	
	public empPanelOpciones(InterfazEmpleado ventanaEmpleado, Empleado empleado)
	{
		this.ventanaEmpleado = ventanaEmpleado;
		
		setLayout(new GridLayout(22,1));
		setPreferredSize(new Dimension(500,20));
		setBackground(Color.WHITE);
		labSaludo.setFont(new Font("Sans-serif", Font.BOLD, 20));
		
		//ACTION LISTENERS
		btnAlquilarVehiculo.addActionListener(this);
		btnRecibirVehiculo.addActionListener(this);
		btnCancelar.addActionListener(this);
		
		//Saludo
		
		labSaludo.setText("Hola " + empleado.getNombre());
		
		add(labSaludo);
		add(btnAlquilarVehiculo);
		add(btnRecibirVehiculo);
		add(btnCancelar);
		
		
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btnAlquilarVehiculo)
		{
			ventanaEmpleado.alquilarVehiculo();
		}
		else if(e.getSource()==btnCancelar)
		{
			cerrarVentana();
		}
		
	}
	
	public void cerrarVentana()
	{
		ventanaEmpleado.cerrarVentana();
	}
	
}
