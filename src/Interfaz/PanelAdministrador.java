package Interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Usuarios.Administrador_Sede;

public class PanelAdministrador extends JPanel implements ActionListener
{
	private InterfazAdministrador interfazAdministrador;
	
	private JLabel labTitulo = new JLabel();
	
	private JLabel labUsuarioEmpleado = new JLabel("Usuario empleado: ");
	private JTextField txtUsuarioEmpleado = new JTextField();
	
	private JLabel labEstado = new JLabel();
	
	//boton buscar empleado
	private JButton btnBuscar = new JButton("Buscar empleado");
	
	//creacion empleado
	private JLabel labUsuarioEmpleadoNuevo = new JLabel("Usuario nuevo: ");
	private JTextField txtUsuarioEmpleadoNuevo = new JTextField();
	
	private JLabel labContraseñaEmpleadoNuevo = new JLabel("Contraseña: ");
	private JTextField txtContraseñaEmpleadoNuevo = new JTextField();
	
	private JLabel labNombreEmpleadoNuevo = new JLabel("Nombre empleado: ");
	private JTextField txtNombreEmpleadoNuevo = new JTextField();
	
	private JButton btnCrear = new JButton("Crear");
	
	
	
	public PanelAdministrador(InterfazAdministrador interfazAdministrador,
			Administrador_Sede administrador_Sede)
	{
		this.interfazAdministrador = interfazAdministrador;
		
		//layout
		setLayout(new GridLayout(20,2));
		
		//Configuración estética
		setBackground(Color.WHITE);
		labTitulo.setFont(new Font("Sans-serif", Font.BOLD, 20));
		labEstado.setFont(new Font("Sans-serif", Font.BOLD, 15));
		labEstado.setForeground(Color.RED);
		
		btnCrear.setBackground(new Color(221, 121, 115));
		btnCrear.setForeground(Color.WHITE);
		btnCrear.setFont(new Font("Sans-serif", Font.BOLD, 18));
		
		//titulo
		labTitulo.setText("Hola " + administrador_Sede.getNombre() + " ("
				+ administrador_Sede.getNombreSede() + ")");
		
		//añadir elementos
		add(labTitulo);
		add(labUsuarioEmpleado);
		add(txtUsuarioEmpleado);
		add(btnBuscar);
		add(labEstado);
		
		//añadir empleado
		add(labUsuarioEmpleadoNuevo);
		add(txtUsuarioEmpleadoNuevo);
		add(labContraseñaEmpleadoNuevo);
		add(txtContraseñaEmpleadoNuevo);
		add(labNombreEmpleadoNuevo);
		add(txtNombreEmpleadoNuevo);
		add(btnCrear);
		
		mostrarCrearEmpleado(false);
		
		
		//action listener
		btnBuscar.addActionListener(this);
		btnCrear.addActionListener(this);
		
		//creacion empleado
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btnBuscar)
		{
			String empleadoString = txtUsuarioEmpleado.getText();
			interfazAdministrador.buscarEmpleado(empleadoString);
		}
		else if(e.getSource() == btnCrear)
		{
			interfazAdministrador.crearEmpleado(txtUsuarioEmpleadoNuevo.getText(), 
					txtContraseñaEmpleadoNuevo.getText(), txtNombreEmpleadoNuevo.getText());
			mostrarCrearEmpleado(false);
		}
		
		
	}
	
	public void setEstado(String estado)
	{
		labEstado.setText(estado);
	}
	
	public void mostrarBuscarEmpleado(boolean estado)
	{
		labUsuarioEmpleado.setVisible(estado);
		txtUsuarioEmpleado.setVisible(estado);
		btnBuscar.setVisible(estado);
		labEstado.setVisible(estado);
	}
	
	
	public void mostrarCrearEmpleado(boolean estado)
	{
		labUsuarioEmpleadoNuevo.setVisible(estado);
		txtUsuarioEmpleadoNuevo.setVisible(estado);
		labContraseñaEmpleadoNuevo.setVisible(estado);
		txtContraseñaEmpleadoNuevo.setVisible(estado);
		labNombreEmpleadoNuevo.setVisible(estado);
		txtNombreEmpleadoNuevo.setVisible(estado);
		btnCrear.setVisible(estado);
	}
	
	public String darUsuario()
	{
		return txtUsuarioEmpleado.getText();
	}
	
}
