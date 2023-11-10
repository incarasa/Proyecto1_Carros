package Interfaz;

import java.awt.GridLayout;
import java.io.Closeable;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
	
	//labels
	private JLabel labCategoria = new JLabel("Categoría del vehiculo");
	private JLabel labRecogida = new JLabel("Recogida");
	private JLabel labSedeRecogida = new JLabel("Sede de Recogida");
	private JLabel labFechaHoraRecogida = new JLabel("Fecha");
	
	private JLabel labDevolucion;
	private JLabel labSedeDevolucion;
	private JLabel labFechaHoraDevolucion;
	
	
	public icPanelOpciones(String[] arrayCategorias , String[] arraySedes)
	{
		setLayout(new GridLayout(20,2));
		
		this.boxCategorias = new JComboBox<String>(arrayCategorias);
		this.boxSedeRecogida = new JComboBox<String>(arraySedes);
		this.txtFechaRecogida = new JTextField();
		this.txtFechaEntrega = new JTextField();
		this.btnReservar = new JButton("Reservar");
		this.btnCancelar = new JButton("Cancelar");
		
		//creacion de Jlabels
		
		
		add(boxCategorias);
		add(boxSedeRecogida);
		
		
		
	}
}
