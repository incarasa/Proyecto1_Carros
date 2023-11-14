package Interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import Proyecto.RentACar;
import Tarifas.Conductor;

public class VentanaAñadirConductor extends JFrame
{
	private PanelAñadirConductor panelAñadirConductor;
	private VentanaSeguros ventanaSeguros;
	private RentACar aplicacion;
	
	public VentanaAñadirConductor(VentanaSeguros ventanaSeguros)
	{
		setTitle("Añadir Conductor");
		setSize(800,600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(new BorderLayout());
		
		this.ventanaSeguros = ventanaSeguros;
		
		this.panelAñadirConductor = new PanelAñadirConductor(this);
		add(panelAñadirConductor, BorderLayout.CENTER);

	}
	
	public void añadirConductor(String nombre, String numeroLicencia, String pais, String vencimiento)
	{
		Conductor conductor = new Conductor(nombre, numeroLicencia, pais, vencimiento);
		ventanaSeguros.añadirConductor(conductor);
		dispose();
		
	}
}
