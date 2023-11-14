package Interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelConfirmarAlquiler extends JPanel implements ActionListener
{
	private VentanaConfirmarAlquiler ventanaConfirmarAlquiler;
	private JButton btnAlquilar;
	
	private JLabel labTitulo = new JLabel("Reservar");
	private JLabel labElPrecioCompleto = new JLabel("El precio del alquiler es:");
	private JLabel labPrecioCompleto = new JLabel("");
	private JLabel labEl30 = new JLabel("- el 30% pagado por usted es: ");
	private JLabel lab30 = new JLabel("");
	private JLabel labDeseaReservar = new JLabel("¿Desea pagar y alquilar el vehiculo?");
	
	private double precioFinal;
	
	public PanelConfirmarAlquiler(VentanaConfirmarAlquiler ventanaConfirmarAlquiler,
			double precio, double precioReserva)
	{
		this.ventanaConfirmarAlquiler = ventanaConfirmarAlquiler;
		setLayout(new GridLayout(22,1));
		
		this.btnAlquilar = new JButton("Alquilar");
		
		btnAlquilar.addActionListener(this);
		
		labPrecioCompleto.setText(Double.toString(precio));
		
		precioFinal = precio - precioReserva;
		
		lab30.setText(Double.toString(precioFinal));
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btnAlquilar)
		{
			ventanaConfirmarAlquiler.alquilar(precioFinal);
		}
		
	}
}
