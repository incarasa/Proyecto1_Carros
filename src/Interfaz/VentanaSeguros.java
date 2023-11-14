package Interfaz;

import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
	
	private LocalDate fechaEntregaAjustada;
	private String sedeEntregaAjustada;
	
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
		this.panelSeguros = new PanelSeguros(aplicacion, this);
		this.listaConductores = new ArrayList<Conductor>();
		
		this.fechaEntregaAjustada = fechaEntregaAjustada;
		this.sedeEntregaAjustada = sedeEntregaAjustada;
		
		add(panelSeguros, BorderLayout.CENTER);
		
		VentanaAñadirConductor ventanaAñadirConductor = new VentanaAñadirConductor(this);
	}
	
	public void añadirConductor(Conductor conductor)
	{
		listaConductores.add(conductor);
		System.out.println("Se ha añadido un conductor");
	}
	
	public void abrirVentanaConductor()
	{
		VentanaAñadirConductor ventanaAñadirConductor = new VentanaAñadirConductor(this);
		ventanaAñadirConductor.setVisible(true);
	}
	
	public void continuar()
	{
		LocalDate diaInicioDate = reserva.getDiaInicio();
		LocalDate diaFinDate = fechaEntregaAjustada;
		
		int dias = (int)(ChronoUnit.DAYS.between(diaInicioDate, diaFinDate));
		int numeroConductores = listaConductores.size();
		String sedeEntregaString = sedeEntregaAjustada;
		
		
		//CORREGIR VALORES QUE LE PASO
		double precio = aplicacion.calcularPrecioAlquiler(dias, 10000, numeroConductores, sedeEntregaAjustada, 50000);
		
		
		VentanaConfirmarAlquiler ventanaConfirmarAlquiler = new VentanaConfirmarAlquiler(aplicacion, precio);
		setVisible(true);
	}
}
