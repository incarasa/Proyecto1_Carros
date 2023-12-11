package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelReserva extends JPanel implements ActionListener
{
	private VentanaPrincipal ventanaPrincipal;
	private JComboBox<String> boxCategorias;
	private JComboBox<String> boxSedeRecogida;
	private JComboBox<String> boxSedeDevolucion;
	private JTextField txtFechaRecogida;
	private JTextField txtHoraRecogida;
	private JTextField txtFechaDevolucion;
	private JTextField txtHoraDevolucion;
	private JButton btnCancelar;
	private JButton btnReservar;

	// labels
	private JLabel labTitulo = new JLabel("Reserva tu vehículo");

	private JLabel labCategoria = new JLabel("Categoría del vehiculo");
	private JLabel labRecogida = new JLabel("Recogida");
	private JLabel labSedeRecogida = new JLabel("Sede de Recogida");
	private JLabel labFechaRecogida = new JLabel(
			"Fecha de recogida (dd/mm/aaaa)");
	private JLabel labHoraRecogida = new JLabel("Hora de recogida (hh:mm)");

	private JLabel labDevolucion = new JLabel("Devolución");
	private JLabel labSedeDevolucion = new JLabel("Sede de Devolución");
	private JLabel labFechaDevolucion = new JLabel(
			"Fecha de entrega (dd/mm/aaaa)");
	private JLabel labHoraDevolucion = new JLabel("Hora de entrega (hh:mm)");

	private JLabel labEstado = new JLabel("");

	PanelReserva(VentanaPrincipal ventanaPrincipal, String[] arraySedes)
	{
		setLayout(new GridLayout(22, 1));
		this.ventanaPrincipal = ventanaPrincipal;
		setPreferredSize(new Dimension(500, 20));
		setBackground(Color.WHITE);

		// TITULO
		labTitulo.setForeground(Color.BLACK); // Color del título
		labTitulo.setFont(new Font("Sans-serif", Font.BOLD, 20));

		// Relleno
		JLabel relleno = new JLabel("");

		// SUBTITULO RECOGIDA
		labRecogida.setFont(new Font("Sans-serif", Font.BOLD, 15));

		// SUBTITULO DEVOLUCION
		labDevolucion.setFont(new Font("Sans-serif", Font.BOLD, 15));

		// ESTADO
		labEstado.setForeground(new Color(215, 0, 64)); // Color Carmine
		labEstado.setFont(new Font("Sans-serif", Font.BOLD, 15));

		String[] arrayCategorias =
		{"A", "B", "C", "D", "E", "F", "G"};
		this.boxCategorias = new JComboBox<String>(arrayCategorias);

		this.boxSedeRecogida = new JComboBox<String>(arraySedes);
		this.boxSedeDevolucion = new JComboBox<String>(arraySedes);
		this.txtFechaRecogida = new JTextField();
		this.txtHoraRecogida = new JTextField();
		this.txtFechaDevolucion = new JTextField();
		this.txtHoraDevolucion = new JTextField();
		this.btnReservar = new JButton("Reservar");
		this.btnCancelar = new JButton("Cancelar");

		// action listeners
		btnCancelar.addActionListener(this);
		btnReservar.addActionListener(this);

		// se añaden los elementos

		add(labTitulo);

		add(labCategoria);
		add(boxCategorias);

		add(labRecogida);
		add(labSedeRecogida);
		add(boxSedeRecogida);

		add(labFechaRecogida);
		add(txtFechaRecogida);
		add(labHoraRecogida);
		add(txtHoraRecogida);

		add(labDevolucion);

		add(labSedeDevolucion);
		add(boxSedeDevolucion);

		add(labFechaDevolucion);
		add(txtFechaDevolucion);
		add(labHoraDevolucion);
		add(txtHoraDevolucion);

		add(labEstado);

		add(btnCancelar);
		add(btnReservar);

	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btnCancelar)
		{
			cerrarVentanaUsuario();
		}
		else if(e.getSource()==btnReservar)
		{
			reservar();
		}
	}
	public void cerrarVentanaUsuario()
	{
		ventanaPrincipal.cerrarVentana();
	}
	
	public void reservar()
	{
		/*
		 * Se recopila la informacion de los campos de texto y se convierte al formato
		 * adecuado antes de pasar los valores.
		 */
		
		String fechaRecogida =  txtFechaRecogida.getText();
		String horaRecogida = txtHoraRecogida.getText();

		String fechaDevolucion =  txtFechaDevolucion.getText();
		String horaDevolucion = txtHoraDevolucion.getText();
		
		
		String sedeRecogida = (String)boxSedeRecogida.getSelectedItem();
		String sedeDevolucion = (String)boxSedeDevolucion.getSelectedItem();
		
		//Categoria
		String categoria = ((String)boxCategorias.getSelectedItem());
		
		//envia la información para reservar.
		ArrayList<String> datosReserva = new ArrayList<String>();
		datosReserva.add(categoria);
		datosReserva.add(sedeRecogida);
		datosReserva.add(fechaRecogida);
		datosReserva.add(horaRecogida);
		datosReserva.add(sedeDevolucion);
		datosReserva.add(horaDevolucion);
		
		ventanaPrincipal.reservar(datosReserva);
	}

}
