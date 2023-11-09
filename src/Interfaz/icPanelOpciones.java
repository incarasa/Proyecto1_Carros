package Interfaz;

import java.awt.GridLayout;
import java.io.Closeable;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class icPanelOpciones extends JPanel
{
	private JComboBox<String> boxCategorias;
	private JComboBox boxSedeRecogida;
	private JTextField txtFechaRecogida;
	private JTextField txtFechaEntrega;
	private JButton btnCancelar;
	private JButton btnReservar;
	
	
	public icPanelOpciones(String[] arrayCategorias)
	{
		setLayout(new GridLayout(20,1));
		
		this.boxCategorias = new JComboBox(arrayCategorias);
		this.boxSedeRecogida = new JComboBox<String>();
		this.txtFechaRecogida = new JTextField();
		this.txtFechaEntrega = new JTextField();
		this.btnReservar = new JButton("Reservar");
		this.btnCancelar = new JButton("Cancelar");
		
		add(boxCategorias);
		
		
		
	}
}
