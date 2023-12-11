package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Interfaz.ventanaCliente;

@SuppressWarnings("serial")
public class PanelRegistro extends JPanel implements ActionListener
{

	private ventanaCliente ventanaCliente;

	private JLabel labTitulo = new JLabel("Crear cliente");

	private JLabel labUsuario = new JLabel("Usuario cliente:");
	private JTextField txtUsuario = new JTextField();

	private JLabel labContraseña = new JLabel("Contraseña cliente:");
	private JTextField txtContraseña = new JTextField();

	private JLabel labNombre = new JLabel("Nombre cliente:");
	private JTextField txtNombreCliente = new JTextField();

	private JLabel labNumeroDocumento = new JLabel("Numero Documento:");
	private JTextField txtNumeroDocumento = new JTextField();

	private JLabel labTelefono = new JLabel("Telefono:");
	private JTextField txtTelefono = new JTextField();

	private JLabel labCorreo = new JLabel("Correo:");
	private JTextField txtCorreo = new JTextField();

	private JLabel labFechaNacimiento = new JLabel("Fecha Nacimiento:");
	private JTextField txtFechaNacimiento = new JTextField();

	private JLabel labNumeroLicencia = new JLabel("Numero Licencia:");
	private JTextField txtNumeroLicencia = new JTextField();

	private JLabel labPaisExpedicion = new JLabel("Pais Expedicion:");
	private JTextField txtPaisExpedicion = new JTextField();

	private JLabel labFechaVencimientoLicencia = new JLabel(
			"Fecha Vencimiento Licencia:");
	private JTextField txtFechaVencimientoLicencia = new JTextField();

	private JLabel labNumeroTarjeta = new JLabel("Numero Tarjeta:");
	private JTextField txtNumeroTarjeta = new JTextField();

	private JLabel labCodigoSeguridad = new JLabel("CodigoSeguridad:");
	private JTextField txtCodigoSeguridad = new JTextField();

	private JLabel labFechaVencimientoTarjeta = new JLabel(
			"Fecha vencimiento tarjeta:");
	private JTextField txtFechaVencimientoTarjeta = new JTextField();

	private JLabel labEstado = new JLabel("");
	private JButton btnCrearCliente = new JButton("CREAR CLIENTE");

	private VentanaPrincipal ventanaPrincipal;

	PanelRegistro(VentanaPrincipal ventanaPrincipal)
	{
		this.ventanaPrincipal = ventanaPrincipal;

		setLayout(new GridLayout(22, 1));

		setPreferredSize(new Dimension(500, 20));
		setBackground(Color.WHITE);
		labTitulo.setFont(new Font("Sans-serif", Font.BOLD, 20));

		labEstado.setForeground(new Color(215, 0, 64));
		labEstado.setFont(new Font("Sans-serif", Font.BOLD, 20));

		// actionlisteners
		btnCrearCliente.addActionListener(this);

		// añadir elementos
		add(labTitulo);
		add(new JLabel(""));
		add(labUsuario);
		add(txtUsuario);
		add(labContraseña);
		add(txtContraseña);
		add(labNombre);
		add(txtNombreCliente);
		add(labNumeroDocumento);
		add(txtNumeroDocumento);
		add(labTelefono);
		add(txtTelefono);
		add(labCorreo);
		add(txtCorreo);
		add(labFechaNacimiento);
		add(txtFechaNacimiento);
		add(labNumeroLicencia);
		add(txtNumeroLicencia);
		add(labPaisExpedicion);
		add(txtPaisExpedicion);
		add(labFechaVencimientoLicencia);
		add(txtFechaVencimientoLicencia);
		add(labNumeroTarjeta);
		add(txtNumeroTarjeta);
		add(labCodigoSeguridad);
		add(txtCodigoSeguridad);
		add(labFechaVencimientoTarjeta);
		add(txtFechaVencimientoTarjeta);
		add(labEstado);
		add(btnCrearCliente);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnCrearCliente)
		{
			crearCliente();
		}

	}

	public void crearCliente()
	{
		ventanaPrincipal.crearCliente(txtUsuario.getText(),
				txtContraseña.getText(), "cliente", txtNombreCliente.getText(),
				txtNumeroDocumento.getText(), txtTelefono.getText(),
				txtCorreo.getText(), txtFechaNacimiento.getText(),
				txtNumeroLicencia.getText(), txtPaisExpedicion.getText(),
				txtFechaVencimientoLicencia.getText(),
				txtNumeroTarjeta.getText(), txtCodigoSeguridad.getText(),
				txtFechaVencimientoTarjeta.getText());

		JOptionPane.showMessageDialog(this, "Cliente creado con éxito",
				"Nuevo cliente", JOptionPane.INFORMATION_MESSAGE);

		ventanaCliente.cerrarVentana();
	}
}
