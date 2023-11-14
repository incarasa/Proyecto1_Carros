package Interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelOpcionesAlquiler extends JPanel implements ActionListener
{
	private VentanaClienteConReserva ventanaCliente;

	private JComboBox<String> boxSedeDevolucion;
	private JTextField txtFechaDevolucion = new JTextField();
	private JButton btnReservar = new JButton("Continuar");
	
	private JLabel labTitulo = new JLabel("Opciones Alquiler");
	private JLabel labSedeDevolucion = new JLabel("Sede de Devolución");
	private JLabel labFechaDevolucion = new JLabel("Fecha de entrega (dd/mm/aaaa)");
	private JLabel labEstado = new JLabel("");
	
	
	public PanelOpcionesAlquiler(VentanaClienteConReserva ventanaCliente, String[] arraySedes)
	{
		setLayout(new GridLayout(20,1));
		this.ventanaCliente = ventanaCliente;
		this.boxSedeDevolucion = new JComboBox<String>(arraySedes);
		
		setPreferredSize(new Dimension(500,20));
		setBackground(Color.WHITE);
		
		labTitulo.setForeground(Color.BLACK); //Color del título
		labTitulo.setFont(new Font("Sans-serif", Font.BOLD, 20));
		
		labEstado.setForeground(new Color(215, 0, 64)); //Color Carmine
		labEstado.setFont(new Font("Sans-serif", Font.BOLD, 15));
		
		
		//action listeners
		btnReservar.addActionListener(this);
		
		add(labTitulo);
		add(labSedeDevolucion);
		add(boxSedeDevolucion);
		add(labFechaDevolucion);
		add(txtFechaDevolucion);
		add(labEstado);
		add(btnReservar);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btnReservar)
		{
			enviarInfo();
			
		}
		
	}
	
	public void enviarInfo()
	{
		String fechaDevolucionStr =  txtFechaDevolucion.getText();
		LocalDate fechaDevolucion = stringToDate(fechaDevolucionStr);
		
		String sedeDevolucion = (String)boxSedeDevolucion.getSelectedItem();
		
		ventanaCliente.setFechaEntrega(fechaDevolucion);
		ventanaCliente.setSedeEntrega(sedeDevolucion);
	
		ventanaCliente.continuarASeguros();
		
	}
	
	
	
	public LocalDate stringToDate(String fechaStr)
	{
		LocalDate fecha = null;
		try
		{
			String[] partes = fechaStr.split("/");
			int año = Integer.parseInt(partes[2]);
			int mes = Integer.parseInt(partes[1]);
			int dia = Integer.parseInt(partes[0]);
			fecha =  LocalDate.of(año, mes, dia);
		}
		catch (DateTimeException e) 
		{
			labEstado.setText("La fecha que ingresó no es válida");
		}
		catch (ArrayIndexOutOfBoundsException e) 
		{
			labEstado.setText("Ha ingresado la fecha sin el backslash /");
		}
		
		return fecha;
	}
		
}
	
