package Interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReservaPanelCentro extends JPanel implements ActionListener
{
	
	private VentanaReservar ventanaReservar;
	
	private JButton btnCancelar;
	private JButton btnReservar;

	private JLabel labTitulo = new JLabel("Reservar");
	private JLabel labElPrecioCompleto = new JLabel("El precio del alquiler es:");
	private JLabel labPrecioCompleto = new JLabel("");
	private JLabel labEl30 = new JLabel("El 30% para reservar es: ");
	private JLabel lab30 = new JLabel("");
	private JLabel labDeseaReservar = new JLabel("¿Desea pagar y reservar?");
	
	public ReservaPanelCentro(VentanaReservar ventanaReservar)
	
	{
		this.ventanaReservar = ventanaReservar;
		
		setLayout(new GridLayout(22,1));
		
		//TITULO
		labTitulo.setForeground(Color.BLACK); //Color del título
		labTitulo.setFont(new Font("Sans-serif", Font.BOLD, 20));
		
		this.btnCancelar = new JButton("Cancelar");
		this.btnReservar = new JButton("Reservar");
		
		//ACTION LISTENERS
		btnCancelar.addActionListener(this);
		btnReservar.addActionListener(this);
		
		add(labTitulo);
		add(labElPrecioCompleto);
		add(labPrecioCompleto);
		add(labEl30);
		add(lab30);
		add(labDeseaReservar);
		add(btnCancelar);
		add(btnReservar);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btnCancelar)
		{
			cerrarVentanaReservas();
		}
		else if(e.getSource()==btnReservar)
		{
			ventanaReservar.reservarLazy();
		}
	}
	
	public void cerrarVentanaReservas()
	{
		ventanaReservar.cerrarVentana();
	}
	
	//SETTER GETTER

	public void setPrecioCompleto(double precio)
	{
		labPrecioCompleto.setText(Double.toString(precio));
	}
	
	public void set30(double precio)
	{
		lab30.setText(Double.toString(precio));
	}
}
