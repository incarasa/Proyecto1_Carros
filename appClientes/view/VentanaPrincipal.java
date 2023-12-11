package view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

import controller.AppManager;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame 
{
	private AppManager aplicacion;
	private PanelReserva panelReserva;
	private PanelRegistro panelRegistro;
	private PanelLogin panelLogin;
	private PanelDisponibilidad panelDisponibilidad;
	public VentanaPrincipal(AppManager appManager)
	{
		setTitle("Rent A Car");
		setSize(1000, 700);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		// aplicacion
		aplicacion = appManager;

		// layout
		setLayout(new BorderLayout());

		this.panelLogin = new PanelLogin(this);
		add(panelLogin, BorderLayout.CENTER);
		revalidate();

	}

	public void validarInicioSesion(String usuario, String contraseña)
	{
		if (aplicacion.comprobarUsuario(usuario, contraseña))
		{
			panelReserva = new PanelReserva(this, aplicacion.getArraySedes());
			remove(panelLogin);
			add(panelReserva);
			revalidate();
		} ;

	}
	public void nuevoUsuario()
	{
		panelRegistro = new PanelRegistro(this);
		remove(panelLogin);
		add(panelRegistro);
		revalidate();
	}

	public void crearCliente(String usuario, String contraseña,
			String tipoUsuario, String nombre, String numeroDocumento,
			String telefono, String correo, String fecha_nacimiento,
			String numeroLicencia, String paisExpedición,
			String fechaVencimientoLicencia, String numeroTarjeta,
			String codigoSeguridad, String fechaVencimientoTarjeta)
	{
		aplicacion.registrarUsuario(usuario, contraseña, tipoUsuario, nombre,
				numeroDocumento, telefono, correo, fecha_nacimiento,
				numeroLicencia, paisExpedición, fechaVencimientoLicencia,
				numeroTarjeta, codigoSeguridad, fechaVencimientoTarjeta);
		remove(panelRegistro);
		add(panelLogin);
	}

	public void cerrarVentana()
	{
		remove(panelReserva);
		add(panelLogin);
		revalidate();
		
	}

	public void reservar(ArrayList<String> datosReserva)
	{
		aplicacion.crearReserva(datosReserva);
		
	}

}
