package Interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelAdminCarrosBotones extends JPanel implements ActionListener
{
	private VentanaAdminCarros ventanaAdminCarros;
	
	private JButton btnBuscarCarro = new JButton("Buscar vehiculo");
	private JButton btnAñadirCarro = new JButton("Añadir vehiculo");
	private JButton btnDarCarrodeBaja = new JButton("Dar de baja");
	
	public PanelAdminCarrosBotones(VentanaAdminCarros ventanaAdminCarros)
	{
		this.ventanaAdminCarros = ventanaAdminCarros;
		setLayout(new GridLayout(1,3));
		setPreferredSize(new Dimension(100,100));
		
		add(btnBuscarCarro);
		add(btnAñadirCarro);
		add(btnDarCarrodeBaja);
		
		btnBuscarCarro.addActionListener(this);
		btnAñadirCarro.addActionListener(this);
		btnDarCarrodeBaja.addActionListener(this);
		
		btnAñadirCarro.setEnabled(false);
		btnDarCarrodeBaja.setEnabled(false);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btnBuscarCarro)
		{
			ventanaAdminCarros.buscarCarro();
		}
		else if(e.getSource() == btnAñadirCarro)
		{
			ventanaAdminCarros.crearCarro();
			setEnabledBtnCrearCarro(false);
		}
		else if(e.getSource() == btnDarCarrodeBaja)
		{
			ventanaAdminCarros.eliminarCarro();
			setEnabledBtnEliminarCarro(false);
		}
		
	}
	
	public void setEnabledBtnEliminarCarro(boolean estado)
	{
		btnDarCarrodeBaja.setEnabled(estado);
	}
	public void setEnabledBtnCrearCarro(boolean estado)
	{
		btnAñadirCarro.setEnabled(estado);
	}
}
