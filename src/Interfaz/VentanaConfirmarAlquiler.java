package Interfaz;

import java.awt.BorderLayout;
import java.awt.Window;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Alquiler.Reserva;
import Inventario.Carro;
import Proyecto.RentACar;
import Tarifas.Conductor;
import Usuarios.Cliente;
import Usuarios.Empleado;
import pasarelasPago.exceptions.TarjetaBloqueadaException;
import pasarelasPago.exceptions.TarjetaSinCupoException;

public class VentanaConfirmarAlquiler extends JFrame
{
	private RentACar aplicacion;
	private PanelConfirmarAlquiler panelConfirmarAlquiler;
	private Reserva reserva;
	private Cliente cliente;
	private Empleado empleado;
	private String seguroSeleccionado;
	
	private LocalDate fechaEntregaAjustada;
	private String sedeEntregaAjustada;
	
	private List<Conductor> listaConductores;
	
	public VentanaConfirmarAlquiler(RentACar aplicacion, double precio, Empleado empleado, 
			Reserva reserva, List<Conductor> listaConductores, LocalDate fechaEntregaAjustada,
			String sedeEntregaAjustada, String seguroSeleccionado)
	{
		this.aplicacion = aplicacion;
		this.empleado = empleado;
		this.listaConductores = listaConductores;
		this.reserva = reserva;
		this.fechaEntregaAjustada = fechaEntregaAjustada;
		this.sedeEntregaAjustada = sedeEntregaAjustada;
		this.seguroSeleccionado = seguroSeleccionado;
		
		setTitle("Confirmar alquiler");
		setSize(1000,700);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(new BorderLayout());
		
		this.panelConfirmarAlquiler = new PanelConfirmarAlquiler(this, precio, 
				reserva.getPrecio(), getPasarelas());
		add(panelConfirmarAlquiler, BorderLayout.CENTER);
		
		
		
	}
	
	//funcion para alquilar un vehículo.
	
	public void alquilar(double precioFinal)
	{
		aplicacion.alquilarVehiculo(reserva.getPlacaVehiculo(), reserva.getDocumentoCliente(), 
				reserva.getDiaInicio(), fechaEntregaAjustada, empleado.getNombreSede(),
				sedeEntregaAjustada, 
				listaConductores, seguroSeleccionado);
		
		JOptionPane.showMessageDialog(this, "Alquiler realizado con éxito",
				"Reserva exitosa", JOptionPane.INFORMATION_MESSAGE);
		
		dispose();
	}
	
	public boolean pagar(String pasarela, double precioF)
	{
		boolean retVar = false;
		try 
		{
			 retVar = aplicacion.pagar(pasarela, cliente, (int)precioF);
		} 
		catch (ClassNotFoundException e) 
		{
			JOptionPane.showMessageDialog(this, "ERROR EN LA PASARELA DE PAGO",
					"Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} 
		catch (TarjetaBloqueadaException e) 
		{
			JOptionPane.showMessageDialog(this, "LA TARJETA ESTÁ BLOQUEADA",
					"Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} 
		catch (TarjetaSinCupoException e) 
		{
			JOptionPane.showMessageDialog(this, "LA TARJETA NO TIENE CUPO",
					"Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return retVar;
	}

	public String[] getPasarelas()
	{
		String[] arrayPasarelas = aplicacion.getPasarelas().toArray(new String[0]);
		return arrayPasarelas;
	}
	
}
