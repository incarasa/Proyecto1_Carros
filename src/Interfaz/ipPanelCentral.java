package Interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;

import javax.swing.*;


public class ipPanelCentral extends JPanel implements ActionListener
{
	private InterfazPrincipal ventanaPrincipal;
	
	private JLabel labUsuario;
	private JTextField txtUsuario;
	private JLabel labContraseña;
	private JTextField txtContraseña;
	private JButton btnIniciarSesion;
	
	public ipPanelCentral(InterfazPrincipal pPrincipal)
	{
		setLayout(new GridLayout(6,1));
		
		ventanaPrincipal = pPrincipal; //ahora el panel conoce la ventana principal
		
		labUsuario = new JLabel("Usuario:");
		txtUsuario = new JTextField();
		labContraseña = new JLabel("Contraseña:");
		txtContraseña = new JTextField();
		btnIniciarSesion = new JButton("Iniciar Sesión");
		
		//action listener
		btnIniciarSesion.addActionListener(this);
		
		add(labUsuario);
		add(txtUsuario);
		add(labContraseña);
		add(txtContraseña);
		add(btnIniciarSesion);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btnIniciarSesion) 
		{
			iniciarSesion();
			
		}
		// TODO Auto-generated method stub
	}
	
	public void iniciarSesion()
	{
		String usuario = txtUsuario.getText();
		String contraseña = txtContraseña.getText();
		
		txtUsuario.setText("");
		txtContraseña.setText("");
		
		ventanaPrincipal.validarInicioSesion(usuario, contraseña);
		
	}
}
