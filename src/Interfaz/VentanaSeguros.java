package Interfaz;

import java.awt.BorderLayout;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JFrame;

import Alquiler.Reserva;
import Proyecto.RentACar;
import Tarifas.Conductor;
import Usuarios.Cliente;
import Usuarios.Empleado;

public class VentanaSeguros extends JFrame
{
	private PanelSeguros panelSeguros;
	private RentACar aplicacion;
	private Reserva reserva;
	private Cliente cliente;
	private List<Conductor> listaConductores;
	
	public VentanaSeguros(RentACar aplicacion, Cliente cliente, Reserva reserva,
			LocalDate fechaEntregaAjustada, String sedeEntregaAjustada)
	{
		setTitle("Seguros");
		setSize(800,600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(new BorderLayout());
		
		this.aplicacion = aplicacion;
		this.panelSeguros = new PanelSeguros(aplicacion);
		
		add(panelSeguros, BorderLayout.CENTER);
		
		
	}
}
