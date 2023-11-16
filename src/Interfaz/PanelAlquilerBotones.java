package Interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelAlquilerBotones extends JPanel implements ActionListener
{
	private JButton btnCrearCliente = new JButton("Crear Cliente");
	private JButton btnAlquilarVehiculo = new JButton("Alquilar Vehiculo");
	private JButton btnCancelar = new JButton("Cancelar");
	
	private VentanaAlquilar ventanaAlquilar;
	
	public PanelAlquilerBotones(VentanaAlquilar ventanaAlquilar)
	{
		setPreferredSize(new Dimension(100,50));
		
		this.ventanaAlquilar = ventanaAlquilar;
		setLayout(new GridLayout(1,3));
		
		btnCrearCliente.setEnabled(false);
		btnAlquilarVehiculo.setEnabled(false);
		
		//actionlisteners
		btnAlquilarVehiculo.addActionListener(this);
		btnCrearCliente.addActionListener(this);
		btnCancelar.addActionListener(this);
		
		add(btnCrearCliente);
		add(btnAlquilarVehiculo);
		add(btnCancelar);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btnCrearCliente) 
		{
			ventanaAlquilar.ventanaCliente();
		}
		else if (e.getSource()==btnCancelar)
		{
			cerrarVentanaAlquilar();
		}
		
	}
	
	public void estadoBtnCrearCliente(boolean estado)
	{
		btnCrearCliente.setEnabled(estado);
	}
	public void estadoBtnAlquilarVehiculo(boolean estado)
	{
		btnAlquilarVehiculo.setEnabled(estado);
	}
	
	public void cerrarVentanaAlquilar()
	{
		ventanaAlquilar.cerrarVentana();
	}
}
