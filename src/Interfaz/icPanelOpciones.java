package Interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Closeable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class icPanelOpciones extends JPanel implements ActionListener
{
	private InterfazCliente ventanaCliente;
	
	private JLabel labTipoVehiculo = new JLabel("Tipo vehículo:");
	private JComboBox<String> boxTipoVehiculo;
	
	private JComboBox<String> boxCategorias;
	private JComboBox<String> boxSedeRecogida;
	private JComboBox<String> boxSedeDevolucion;
	private JTextField txtFechaRecogida;
	private JTextField txtHoraRecogida;
	private JTextField txtFechaDevolucion;
	private JTextField txtHoraDevolucion;
	private JButton btnCancelar;
	private JButton btnReservar;
	
	//labels
	private JLabel labTitulo = new JLabel("Reserva tu vehículo");
	
	private JLabel labCategoria = new JLabel("Categoría del vehiculo");
	private JLabel labRecogida = new JLabel("Recogida");
	private JLabel labSedeRecogida = new JLabel("Sede de Recogida");
	private JLabel labFechaRecogida = new JLabel("Fecha de recogida (dd/mm/aaaa)");
	private JLabel labHoraRecogida = new JLabel("Hora de recogida (hh:mm)");
	
	private JLabel labDevolucion = new JLabel("Devolución");
	private JLabel labSedeDevolucion = new JLabel("Sede de Devolución");
	private JLabel labFechaDevolucion = new JLabel("Fecha de entrega (dd/mm/aaaa)");
	private JLabel labHoraDevolucion = new JLabel("Hora de entrega (hh:mm)");
	
	private JLabel labEstado = new JLabel("");
	
	
	public icPanelOpciones(String[] arrayCategorias , String[] arraySedes , 
			InterfazCliente ventanaCliente, String[] tiposArray)
	{
		setLayout(new GridLayout(22,1));
		
		this.ventanaCliente = ventanaCliente;
		
		setPreferredSize(new Dimension(500,20));
		setBackground(Color.WHITE);
		
		//TITULO
		labTitulo.setForeground(Color.BLACK); //Color del título
		labTitulo.setFont(new Font("Sans-serif", Font.BOLD, 20));
		
		//Relleno
		JLabel relleno = new JLabel("");
		
		//SUBTITULO RECOGIDA
		labRecogida.setFont(new Font("Sans-serif", Font.BOLD, 15));
		
		//SUBTITULO DEVOLUCION
		labDevolucion.setFont(new Font("Sans-serif", Font.BOLD, 15));
		
		//ESTADO
		labEstado.setForeground(new Color(215, 0, 64)); //Color Carmine
		labEstado.setFont(new Font("Sans-serif", Font.BOLD, 15));
		
		this.boxTipoVehiculo = new JComboBox<String>(tiposArray);
		this.boxCategorias = new JComboBox<String>(arrayCategorias);
		this.boxSedeRecogida = new JComboBox<String>(arraySedes);
		this.boxSedeDevolucion= new JComboBox<String>(arraySedes);
		this.txtFechaRecogida = new JTextField();
		this.txtHoraRecogida = new JTextField();
		this.txtFechaDevolucion = new JTextField();
		this.txtHoraDevolucion = new JTextField();
		this.btnReservar = new JButton("Reservar");
		this.btnCancelar = new JButton("Cancelar");
		
		//action listeners
		btnCancelar.addActionListener(this);
		btnReservar.addActionListener(this);
		
		//se añaden los elementos
		
		add(labTitulo);
		
		add(labTipoVehiculo);
		add(boxTipoVehiculo);
		
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
		ventanaCliente.cerrarVentana();
	}
	
	public void reservar()
	{
		/*
		 * Se recopila la informacion de los campos de texto y se convierte al formato
		 * adecuado antes de pasar los valores.
		 */
		
		String fechaRecogidaStr =  txtFechaRecogida.getText();
		String horaRecogidaStr = txtHoraRecogida.getText();

		String fechaDevolucionStr =  txtFechaDevolucion.getText();
		String horaDevolucionStr = txtHoraDevolucion.getText();
		
		LocalDate fechaRecogida = stringToDate(fechaRecogidaStr);
		LocalTime horaRecogida = stringToTime(horaRecogidaStr);
		LocalDate fechaDevolucion = stringToDate(fechaDevolucionStr);
		LocalTime horaDevolucion =  stringToTime(horaDevolucionStr);
		
		//Toma las sedes seleccionadas y las guarda en variables.
		String sedeRecogida = (String)boxSedeRecogida.getSelectedItem();
		String sedeDevolucion = (String)boxSedeDevolucion.getSelectedItem();
		String tipoVehiculo = (String)boxTipoVehiculo.getSelectedItem();
		
		//Categoria
		char categoria = ((String)boxCategorias.getSelectedItem()).charAt(0);
		
		//envia la información para reservar.
		ventanaCliente.reservar(fechaRecogida, fechaDevolucion, horaRecogida, 
				horaDevolucion, sedeRecogida, sedeDevolucion , categoria, tipoVehiculo);
	}
	
	
	/**
	 * Convierte lo ingresado por el usuario en una fecha.
	 * Exepciones el usuario no ingresa los /
	 * y el usuario ingresa una fecha que no tiene sentido.
	 * @param fechaStr
	 * @return LocalDate
	 */
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
	
	/**
	 * Convierte lo ingresado por el usuario en una hora
	 * Exepciones: El usuario no ingresa los : para partir la fecha
	 * El usuario no ingresa una hora valida.
	 * @param tiempoStr
	 * @return LocalTime
	 */
	public LocalTime stringToTime(String tiempoStr)
	{
		String[] partes = tiempoStr.split(":");
		int hora = Integer.parseInt(partes[0]);
		int minuto = Integer.parseInt(partes[1]);
		
		return LocalTime.of(hora, minuto);
	}
	
	public void actualizarEstado(String estado)
	{
		labEstado.setText(estado);
	}
}
