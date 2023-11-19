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
	
	private JRadioButton btnLavar = new JRadioButton("Lavar");
	private JRadioButton btnMantenimiento = new JRadioButton("Mantenimiento");
	private JLabel labFechaDisponibleNuevamente = new JLabel("Fecha disponible nuevamente");
	private JTextField txtFechaDisponibleNuevamente = new JTextField("");
	private JButton btnDevolver = new JButton("Devolver");
	
	//elementos recibir
	private JLabel labIngresePlaca = new JLabel("Ingrese la placa del vehículo: ");
	private JTextField txtPlaca = new JTextField("");
	
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
		
		//elementos recibir
		add(labIngresePlaca);
		add(txtPlaca);
		add(btnLavar);
		add(btnMantenimiento);
		add(labFechaDisponibleNuevamente);
		add(txtFechaDisponibleNuevamente);
		add(btnDevolver);
		
		mostrarRecibirVehiculo(false);
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btnAlquilarVehiculo)
		{
			ventanaEmpleado.alquilarVehiculo();
		}
		else if(e.getSource()==btnRecibirVehiculo)
		{
			mostrarRecibirVehiculo(true);
		}
		else if(e.getSource()==btnCancelar)
		{
			cerrarVentana();
		}
		else if(e.getSource()==btnDevolver)
		{
			devolverCarro();
		}
	}
	
	public void cerrarVentana()
	{
		ventanaEmpleado.cerrarVentana();
	}
	
	public void mostrarRecibirVehiculo(boolean estado)
	{
		labIngresePlaca.setVisible(estado);
		txtPlaca.setVisible(estado);
		btnLavar.setVisible(estado);
		btnMantenimiento.setVisible(estado);
		labFechaDisponibleNuevamente.setVisible(estado);
		txtFechaDisponibleNuevamente.setVisible(estado);
		btnDevolver.setVisible(estado);
	}
	
	public void devolverCarro()
	{
		boolean lavar = btnLavar.isSelected();
		boolean mantenimiento = btnMantenimiento.isSelected();
		String fechaDisponibleNuevamente = txtFechaDisponibleNuevamente.getText();
		
		ventanaEmpleado.devolverCarro(fechaDisponibleNuevamente, lavar, mantenimiento, 
				fechaDisponibleNuevamente);
	}
	
}
