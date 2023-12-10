package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import Proyecto.RentACar;

public class PanelAdminCarrosInfo extends JPanel
{
	private VentanaAdminCarros ventanaAdminCarros;
	
	private JScrollPane scroller;
	
	private JLabel labtitulo = new JLabel("Panel Carros");
	
	//tipo de vehículo
	private JLabel labTipoVehiculo = new JLabel("Tipo vehículo:");
	private JComboBox<String> boxTipoVehiculo;
	
	private JLabel labPlaca = new JLabel("Placa: ");
	private JTextField txtPlaca = new JTextField("");
	
	private JLabel labMarca = new JLabel("Marca: ");
	private JTextField txtMarca = new JTextField("");
	
	private JLabel labModelo = new JLabel("Modelo: ");
	private JTextField txtModelo = new JTextField("");
	
	private JLabel labTransmision = new JLabel("Transmisión: ");
	private JTextField txtTransmision = new JTextField("");
	
	private JLabel labCategoria = new JLabel("Categoria: ");
	private JTextField txtCategoria = new JTextField("");
	
	private JLabel labSede = new JLabel("Sede: ");
	private JTextField txtSede = new JTextField("");
	
	private JLabel labAlquilado = new JLabel("Alquilado: ");
	private JTextField txtAlquilado = new JTextField("");
	
	private JLabel labDisponible = new JLabel("Disponible: ");
	private JTextField txtDisponible = new JTextField("");
	
	private JLabel labLavandose = new JLabel("Lavandose: ");
	private JTextField txtLavandose = new JTextField("");
	
	private JLabel labEnMantenimiento = new JLabel("En mantenimiento: ");
	private JTextField txtEnMantenimiento = new JTextField("");
	
	private JLabel labFechaDisponibleNuevamente = new JLabel("Fecha disponible nuevamente: ");
	private JTextField txtFechaDisponibleNuevamente = new JTextField("");
	
	private JLabel labRutaFoto = new JLabel("Ruta foto: ");
	private JTextField txtRutaFoto = new JTextField("");
	
	private JLabel labDiasNoDisponible = new JLabel("Dias no disponible");
	private JList<LocalDate> listDiasNoDisponible;
	private DefaultListModel<LocalDate> listModel;
	
	private JLabel labEstado = new JLabel("");
	
	public PanelAdminCarrosInfo(VentanaAdminCarros ventanaAdminCarros, String[] tiposArray)
	{
		this.ventanaAdminCarros = ventanaAdminCarros;
		
		setLayout(new GridLayout(20,2));
		
		this.boxTipoVehiculo = new JComboBox<String>(tiposArray);
		
		add(labtitulo);
		add(new JLabel("."));
		
		add(labTipoVehiculo);
		add(boxTipoVehiculo);
		add(labPlaca);
		add(txtPlaca);
		add(labMarca);
		add(txtMarca);
		add(labModelo);
		add(txtModelo);
		add(labTransmision);
		add(txtTransmision);
		add(labCategoria);
		add(txtCategoria);
		add(labSede);
		add(txtSede);
		add(labAlquilado);
		add(txtAlquilado);
		add(labDisponible);
		add(txtDisponible);
		add(labLavandose);
		add(txtLavandose);
		add(labEnMantenimiento);
		add(txtEnMantenimiento);
		add(labFechaDisponibleNuevamente);
		add(txtFechaDisponibleNuevamente);
		add(labRutaFoto);
		add(txtRutaFoto);
		add(labDiasNoDisponible);
		
		labEstado.setForeground(Color.RED);
		labEstado.setFont(new Font("Sans-serif", Font.BOLD, 14));
		
		listModel = new DefaultListModel<>();
        listDiasNoDisponible = new JList<>(listModel);
        
        scroller = new JScrollPane(listDiasNoDisponible);
        listDiasNoDisponible.setVisibleRowCount(5);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroller);
        add(labEstado);
        
        //poner invisible
        setVisibleElementos(false);
        
        
		
	}
	
	public void actualizarCamposdeTexto(String placa, String marca, int modelo, String transmision, char categoría,
    		boolean alquilado, boolean disponible, String sede, boolean lavandose,
    		boolean enMantenimiento, String fechaDisponibleNuevamente, List<LocalDate>fechasNoDisponible,
    		String rutaFoto, String tipo)
	{
		boxTipoVehiculo.setSelectedItem(tipo);
		txtPlaca.setText(placa);
		txtMarca.setText(marca);
		txtModelo.setText(Integer.toString(modelo));
		txtTransmision.setText(transmision);
		txtCategoria.setText(categoría+"");
		txtAlquilado.setText(String.valueOf(alquilado));
		txtDisponible.setText(String.valueOf(disponible));
		txtSede.setText(sede);
		txtLavandose.setText(String.valueOf(lavandose));
		txtEnMantenimiento.setText(String.valueOf(enMantenimiento));
		txtFechaDisponibleNuevamente.setText(fechaDisponibleNuevamente);
		txtRutaFoto.setText(rutaFoto);
		
		for(LocalDate fecha : fechasNoDisponible)
		{
			listModel.addElement(fecha);
		}
			
	}
	
	public String darPlaca()
	{
		return txtPlaca.getText();
	}
	
	public void setEstado(String estado)
	{
		labEstado.setText(estado);
	}
	
	public void setVisibleElementos(boolean estado)
	{
		labMarca.setVisible(estado);
		txtMarca.setVisible(estado);
		labModelo.setVisible(estado);
		txtModelo.setVisible(estado);
		labTransmision.setVisible(estado);
		txtTransmision.setVisible(estado);
		labCategoria.setVisible(estado);
		txtCategoria.setVisible(estado);
		labSede.setVisible(estado);
		txtSede.setVisible(estado);
		labAlquilado.setVisible(estado);
		txtAlquilado.setVisible(estado);
		labDisponible.setVisible(estado);
		txtDisponible.setVisible(estado);
		labLavandose.setVisible(estado);
		txtLavandose.setVisible(estado);
		labEnMantenimiento.setVisible(estado);
		txtEnMantenimiento.setVisible(estado);
		labFechaDisponibleNuevamente.setVisible(estado);
		txtFechaDisponibleNuevamente.setVisible(estado);
		labRutaFoto.setVisible(estado);
		txtRutaFoto.setVisible(estado);
		labDiasNoDisponible.setVisible(estado);
		scroller.setVisible(estado);
	}
	
	public void blanquearCampos()
	{
		txtPlaca.setText("");
		txtMarca.setText("");
		txtModelo.setText("");
		txtTransmision.setText("");
		txtCategoria.setText("");
		txtAlquilado.setText(String.valueOf(""));
		txtDisponible.setText(String.valueOf(""));
		txtSede.setText("");
		txtLavandose.setText(String.valueOf(""));
		txtEnMantenimiento.setText(String.valueOf(""));
		txtFechaDisponibleNuevamente.setText("");
		txtRutaFoto.setText("");
		listModel.clear();
		
	}
	public void ocultarCamposNuevoCarro()
	{
		txtDisponible.setVisible(false);
		txtAlquilado.setVisible(false);
		txtLavandose.setVisible(false);
		txtEnMantenimiento.setVisible(false);
		txtFechaDisponibleNuevamente.setVisible(false);
		scroller.setVisible(false);
	}
	
	//funciones para obtener info de lo txt
	public String getPlaca()
	{
		return txtPlaca.getText();
	}
	public String getMarca()
	{
		return txtMarca.getText();
	}
	public int getModelo()
	{
		return Integer.parseInt(txtModelo.getText());
	}
	public String getTransmision()
	{
		return txtTransmision.getText();
	}
	public char getCategoria()
	{
		return txtCategoria.getText().charAt(0);
	}
	public String getSede()
	{
		return txtSede.getText();
	}
	public String getRutaImagen()
	{
		return txtRutaFoto.getText();
	}
	public String getTipo()
	{
		return (String)boxTipoVehiculo.getSelectedItem();
	}

	
}
