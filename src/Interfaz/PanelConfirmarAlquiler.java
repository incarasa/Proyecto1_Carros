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
	private JLabel labEl30 = new JLabel("- el 30%: ");
	private JLabel lab30 = new JLabel("");
	private JLabel labElValorFinal = new JLabel("El valor final del alquiler es: ");
	private JLabel labValorFinal = new JLabel("");
	private JLabel labDeseaReservar = new JLabel("¿Desea pagar y alquilar el vehiculo?");
	
	private double precioFinal;
	
	public PanelConfirmarAlquiler(VentanaConfirmarAlquiler ventanaConfirmarAlquiler,
			double precio, double precioReserva)
	{
		this.ventanaConfirmarAlquiler = ventanaConfirmarAlquiler;
		setLayout(new GridLayout(22,1));
		
		this.btnAlquilar = new JButton("Alquilar");
		
		//action listener
		btnAlquilar.addActionListener(this);
		
		//calcular el precio a pagar
		precioFinal = precio - precioReserva;
		
		//cambiar la informacion vacía
		labPrecioCompleto.setText(Double.toString(precio));
		lab30.setText(Double.toString(precioReserva));
		labValorFinal.setText(Double.toString(precioFinal));
		
		add(labTitulo);
		add(labElPrecioCompleto);
		add(labPrecioCompleto);
		add(labEl30);
		add(lab30);
		add(labElValorFinal);
		add(labValorFinal);
		add(labDeseaReservar);
		add(btnAlquilar);
		
		
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
