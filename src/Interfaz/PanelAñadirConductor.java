package Interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelAñadirConductor extends JPanel implements ActionListener
{
	private VentanaAñadirConductor ventanaAñadirConductor;
	
	private JLabel labTitulo = new JLabel("Añadir Conductor");
	
	private JLabel labNombre = new JLabel("Nombre conductor:");
	private JTextField txtNombre = new JTextField();
	
	private JLabel labNumeroLicencia = new JLabel("Numero licencia:");
	private JTextField txtNumeroLicencia = new JTextField();
	
	private JLabel labPaisExpedicion = new JLabel("Pais Expedición:");
	private JTextField txtPaisExpedicion = new JTextField();
	
	private JLabel labFechaVencimientoLicencia = new JLabel("Fecha vencimiento licencia");
	private JTextField txtFechaVencimientoLicencia= new JTextField();
	
	private JButton btnAñadirConductor = new JButton("Añadir conductor");
	
	public PanelAñadirConductor(VentanaAñadirConductor ventanaAñadirConductor)
	{
		this.ventanaAñadirConductor = ventanaAñadirConductor;
		
		setLayout(new GridLayout(22,1));
		
		setPreferredSize(new Dimension(500,20));
		setBackground(Color.WHITE);
		labTitulo.setFont(new Font("Sans-serif", Font.BOLD, 20));
		
		btnAñadirConductor.addActionListener(this);
		
		add(labTitulo);
		add(new JLabel(""));
		add(labNombre);
		add(txtNombre);
		add(labNumeroLicencia);
		add(txtNumeroLicencia);
		add(labPaisExpedicion);
		add(txtPaisExpedicion);
		add(labFechaVencimientoLicencia);
		add(txtFechaVencimientoLicencia);
		add(btnAñadirConductor);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btnAñadirConductor)
		{
			
		}
		
	}
	
	public void añadirConductor()
	{
		
	}
	
}
