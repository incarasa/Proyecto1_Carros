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

import Usuarios.Administrador_Principal;
import Usuarios.Administrador_Sede;

public class PanelAdminPrincipal extends JPanel implements ActionListener
{

	private InterfazAdminPrincipal interfazAdminPrincipal;
	
	private JLabel labTitulo = new JLabel();
	
	private JLabel labUsuarioAdmin = new JLabel("Usuario admin: ");
	private JTextField txtUsuarioAdmin = new JTextField();
	private JLabel labSede = new JLabel("Sede: ");
	private JTextField txtSede = new JTextField();
	
	private JLabel labEstado = new JLabel();
	
	//boton buscar admin
	private JButton btnBuscar = new JButton("Buscar admin");
	private JButton btnBuscarSede = new JButton("Buscar sede");
	
	//creacion admin
	private JLabel labUsuarioAdminNuevo = new JLabel("Administrador nuevo: ");
	private JTextField txtUsuarioAdminNuevo = new JTextField();
	
	private JLabel labContraseñaAdminNuevo = new JLabel("Contraseña: ");
	private JTextField txtContraseñaAdminNuevo = new JTextField();
	
	private JLabel labNombreAdminNuevo = new JLabel("Nombre administrador: ");
	private JTextField txtNombreAdminNuevo = new JTextField();
	
	private JLabel labSedeAdminNuevo = new JLabel("Sede administrador: ");
	private JTextField txtSedeAdminNuevo = new JTextField();
	
	private JButton btnCrear = new JButton("Crear");
	
	//espacios sede
	private JLabel labNombreSede = new JLabel("Nombre sede (MAYUSCULAS): ");
	private JTextField txtNombreSede = new JTextField();
	private JLabel labDireccionSede = new JLabel("Direccion sede: ");
	private JTextField txtDireccionSede = new JTextField();
	
	private JButton btnCrearSede = new JButton("Crear sede");
	
	
	
	public PanelAdminPrincipal(InterfazAdminPrincipal interfazAdminPrincipal,
			Administrador_Principal administrador_Principal)
	{
		this.interfazAdminPrincipal = interfazAdminPrincipal;
		
		//layout
		setLayout(new GridLayout(25,2));
		
		//Configuración estética
		setBackground(Color.WHITE);
		labTitulo.setFont(new Font("Sans-serif", Font.BOLD, 20));
		labEstado.setFont(new Font("Sans-serif", Font.BOLD, 15));
		labEstado.setForeground(Color.RED);
		
		btnCrear.setBackground(new Color(221, 121, 115));
		btnCrear.setForeground(Color.WHITE);
		btnCrear.setFont(new Font("Sans-serif", Font.BOLD, 18));
		
		//titulo
		labTitulo.setText("Hola gerente " + administrador_Principal.getNombre());
		
		//añadir elementos
		add(labTitulo);
		add(labUsuarioAdmin);
		add(txtUsuarioAdmin);
		add(btnBuscar);
		add(labSede);
		add(txtSede);
		add(btnBuscarSede);
		add(labEstado);
		
		//añadir administrador
		add(labUsuarioAdminNuevo);
		add(txtUsuarioAdminNuevo);
		add(labContraseñaAdminNuevo);
		add(txtContraseñaAdminNuevo);
		add(labNombreAdminNuevo);
		add(txtNombreAdminNuevo);
		add(labSedeAdminNuevo);
		add(txtSedeAdminNuevo);
		add(btnCrear);
		
		mostrarCrearAdmin(false);
		
		//añadir sede
		add(labNombreSede);
		add(txtNombreSede);
		add(labDireccionSede);
		add(txtDireccionSede);
		add(btnCrearSede);
		
		mostrarCrearSede(false);
		
		//action listener
		btnBuscar.addActionListener(this);
		btnCrear.addActionListener(this);
		btnCrearSede.addActionListener(this);
		btnBuscarSede.addActionListener(this);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btnBuscar)
		{
			String adminString = txtUsuarioAdmin.getText();
			interfazAdminPrincipal.buscarAdmin(adminString);
		}
		else if(e.getSource() == btnCrear)
		{
			interfazAdminPrincipal.crearAdmin(txtUsuarioAdminNuevo.getText(), 
					txtContraseñaAdminNuevo.getText(), txtNombreAdminNuevo.getText(), 
					txtSedeAdminNuevo.getText());
			mostrarCrearAdmin(false);
		}
		else if(e.getSource() == btnBuscarSede)
		{
			String sedeStr = txtSede.getText();
			interfazAdminPrincipal.buscarSede(sedeStr);
		}
		else if(e.getSource() == btnCrearSede)
		{
			interfazAdminPrincipal.crearSede(txtNombreSede.getText(), txtDireccionSede.getText());
			mostrarCrearAdmin(false);
		}
	}
	
	public void setEstado(String estado)
	{
		labEstado.setText(estado);
	}
	
	public void mostrarBuscarAdmin(boolean estado)
	{
		labUsuarioAdmin.setVisible(estado);
		txtUsuarioAdmin.setVisible(estado);
		btnBuscar.setVisible(estado);
		labEstado.setVisible(estado);
		
		labSede.setVisible(estado);
		txtSede.setVisible(estado);
		btnBuscarSede.setVisible(estado);
	}
	
	
	public void mostrarCrearAdmin(boolean estado)
	{
		labUsuarioAdminNuevo.setVisible(estado);
		txtUsuarioAdminNuevo.setVisible(estado);
		labContraseñaAdminNuevo.setVisible(estado);
		txtContraseñaAdminNuevo.setVisible(estado);
		labNombreAdminNuevo.setVisible(estado);
		txtNombreAdminNuevo.setVisible(estado);
		labSedeAdminNuevo.setVisible(estado);
		txtSedeAdminNuevo.setVisible(estado);
		btnCrear.setVisible(estado);
	}
	
	public void mostrarCrearSede(boolean estado)
	{
		add(labNombreSede).setVisible(estado);
		add(txtNombreSede).setVisible(estado);
		add(labDireccionSede).setVisible(estado);
		add(txtDireccionSede).setVisible(estado);
		add(btnCrearSede).setVisible(estado);
	}


	public String darUsuario()
	{
		return txtUsuarioAdmin.getText();
	}
	
	public String darSede()
	{
		return txtSede.getText();
	}
}
