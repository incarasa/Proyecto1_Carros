package Interfaz;

import java.awt.Color;
import java.awt.Font;
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
	private JLabel labEstado;
	
	public ipPanelCentral(InterfazPrincipal pPrincipal)
	{
		setLayout(new GridLayout(20,1));
		
		ventanaPrincipal = pPrincipal; //ahora el panel conoce la ventana principal
		
		labUsuario = new JLabel("Usuario:");
		txtUsuario = new JTextField();
		labContraseña = new JLabel("Contraseña:");
		txtContraseña = new JTextField();
		btnIniciarSesion = new JButton("Iniciar Sesión");
		labEstado = new JLabel("");
		
		labEstado.setForeground(new Color(215, 0, 64)); //Color Carmine
		labEstado.setFont(new Font("Sans-serif", Font.BOLD, 20));
		
		//white para rellenar
		JLabel relleno = new JLabel("-");
		JLabel relleno2 = new JLabel("-");
		relleno.setFont(new Font("Sans-serif", Font.BOLD, 40));
		relleno2.setFont(new Font("Sans-serif", Font.BOLD, 40));
		
		//action listener
		btnIniciarSesion.addActionListener(this);
		
		add(labUsuario);
		add(txtUsuario);
		add(labContraseña);
		add(txtContraseña);
		add(relleno);
		add(btnIniciarSesion);
		add(relleno2);
		add(labEstado);
		
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
	
	public void setTextEstado(String text)
	{
		labEstado.setText(text);
	}
	
}
