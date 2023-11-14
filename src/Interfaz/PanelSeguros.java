package Interfaz;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Proyecto.RentACar;
import Tarifas.Conductor;

public class PanelSeguros extends JPanel implements ActionListener
{
	private Map<String, String> mapaSeguros;
	private JComboBox boxSeguros;
	
	public PanelSeguros(RentACar aplicacion)
	{
		setLayout(new GridLayout(20,1));
		
		JLabel labTitulo = new JLabel("Añadir Seguros y Conductores");
		
		mapaSeguros = aplicacion.darMapaSeguros();
		
		List<String> listaSeguros = new ArrayList<String>(mapaSeguros.keySet());
		String[] segurosArray = listaSeguros.toArray(new String[0]);
		
		boxSeguros = new JComboBox(segurosArray);
		
		JButton btnAgregarConductor = new JButton("Agregar Conductor");
		JButton btnContinuar = new JButton("Continuar");
		
		//action listeners
		btnAgregarConductor.addActionListener(this);
		btnContinuar.addActionListener(this);
		
		add(labTitulo);
		add(new JLabel(""));
		add(boxSeguros);
		add(new JLabel(""));
		add(btnContinuar);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
}
