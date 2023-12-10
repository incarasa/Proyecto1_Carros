package Interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import Inventario.Carro;
import Inventario.VehiculoBase;
import Proyecto.RentACar;

public class VentanaAdminCarros extends JFrame
{
	private PanelAdminCarrosInfo panelAdminCarrosInfo;
	private PanelAdminCarrosBotones panelAdminCarrosBotones;
	private JPanel panelCarroImagen = new JPanel();
	
	private JLabel labImagenCarro = new JLabel();
	
	private RentACar aplicacion;
	
	public VentanaAdminCarros(RentACar aplicacion)
	{
		this.aplicacion = aplicacion;
		
		setLayout(new BorderLayout());
		
		setTitle("Carros");
		setSize(1000,700);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		String[] tiposArray = getTipos();
		
		panelAdminCarrosInfo = new PanelAdminCarrosInfo(this, tiposArray);
		panelAdminCarrosBotones = new PanelAdminCarrosBotones(this);
		
		add(panelAdminCarrosInfo, BorderLayout.CENTER);
		add(panelAdminCarrosBotones, BorderLayout.SOUTH);
		add(panelCarroImagen, BorderLayout.WEST);
		
		panelCarroImagen.setPreferredSize(new Dimension(600,100));
		panelCarroImagen.setLayout(new BorderLayout());
		
		panelCarroImagen.add(labImagenCarro, BorderLayout.CENTER);
		
		
	}
	
	public void buscarCarro()
	{
		String placa = panelAdminCarrosInfo.darPlaca();
		panelAdminCarrosInfo.setVisibleElementos(true);
		try 
		{
			VehiculoBase carro = aplicacion.darCarro(placa);
			
			String transmision = carro.getCaracteristicas().get(0);
			
			panelAdminCarrosInfo.actualizarCamposdeTexto(carro.getPlaca(), carro.getMarca(), 
					carro.getModelo(), transmision, carro.getCategoría(), 
					carro.isAlquilado(), carro.isDisponible(), carro.getSede(), 
					carro.getLavandose(), carro.getEnMantenimiento(), 
					carro.getFechaDisponibleNuevamente(), carro.getDiasNoDisponible(), carro.getRutaImagen(),
					carro.getTipo());
			
			ImageIcon imagenCarro = new ImageIcon(carro.getRutaImagen());
			Image scaledImage = imagenCarro.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
			labImagenCarro.setIcon(new ImageIcon(scaledImage));
			panelAdminCarrosBotones.setEnabledBtnEliminarCarro(true);
			
		}
		catch (NullPointerException e) 
		{
			panelAdminCarrosInfo.setEstado("No se econtró el carro, "
					+ "cree uno nuevo, porfavor");
			
			panelAdminCarrosInfo.blanquearCampos();
			panelAdminCarrosBotones.setEnabledBtnCrearCarro(true);
			panelAdminCarrosInfo.ocultarCamposNuevoCarro();
		}
		
	}
	
	public void crearCarro()
	{
		String placa = panelAdminCarrosInfo.getPlaca();
		String marca = panelAdminCarrosInfo.getMarca();
		int modelo = panelAdminCarrosInfo.getModelo();
		String transmision = panelAdminCarrosInfo.getTransmision();
		char categoría = panelAdminCarrosInfo.getCategoria();
		String sede = panelAdminCarrosInfo.getSede();
		String rutaImagen = panelAdminCarrosInfo.getRutaImagen();
		String tipo = panelAdminCarrosInfo.getTipo();
		ArrayList<String> carac = new ArrayList<String>();
		carac.add(transmision);
		
		aplicacion.agregarCarro(placa, marca, modelo, carac, categoría, sede, rutaImagen, tipo);
		panelAdminCarrosInfo.setEstado("");
		
		JOptionPane.showMessageDialog( this , "Carro creado con éxito" ,"Agregar carro" , 
				JOptionPane.INFORMATION_MESSAGE );
	}
	
	public void eliminarCarro()
	{
		String placa = panelAdminCarrosInfo.getPlaca();
		aplicacion.eliminarCarro(placa);
		
		JOptionPane.showMessageDialog( this , "Carro eliminado con éxito" ,"Eliminar carro" , 
				JOptionPane.INFORMATION_MESSAGE );
	}
	
	public String[] getTipos()
	{
		ArrayList<String> tiposList = aplicacion.getTipos();
		String[] tiposArray = tiposList.toArray(new String[0]);
		return tiposArray;
	}
}
