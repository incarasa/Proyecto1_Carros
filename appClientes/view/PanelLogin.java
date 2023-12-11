package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PanelLogin extends JPanel implements ActionListener
{
	private VentanaPrincipal ventanaPrincipal;

	private JLabel labUsuario;
	private JTextField txtUsuario;
	private JLabel labContraseña;
	private JTextField txtContraseña;
	private JButton btnIniciarSesion;
	private JLabel labEstado;

	private JButton btnRegistrarse;

	public PanelLogin(VentanaPrincipal ventanaPrincipal)
	{
		setVisible(true);
		setLayout(new GridLayout(20,1));
		
		this.ventanaPrincipal = ventanaPrincipal; //ahora el panel conoce la ventana principal
		
		labUsuario = new JLabel("Usuario:");
		txtUsuario = new JTextField();
		labContraseña = new JLabel("Contraseña:");
		txtContraseña = new JPasswordField();
		btnIniciarSesion = new JButton("Iniciar Sesión");
		btnRegistrarse = new JButton("Registrar Nuevo Usuario");
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
		btnRegistrarse.addActionListener(this);
		
		add(labUsuario);
		add(txtUsuario);
		add(labContraseña);
		add(txtContraseña);
		add(relleno);
		add(btnIniciarSesion);
		add(btnRegistrarse);
		add(relleno2);
		add(labEstado);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btnIniciarSesion) 
		{
			iniciarSesion();
			
		}else if (e.getSource() == btnRegistrarse){
			ventanaPrincipal.nuevoUsuario();
		}
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


