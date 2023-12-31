package Interfaz;

import java.awt.BorderLayout;
import java.security.PublicKey;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.*;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import Inventario.Carro;
import Inventario.VehiculoBase;
import Proyecto.RentACar;
import Usuarios.Cliente;
import pasarelasPago.exceptions.TarjetaBloqueadaException;
import pasarelasPago.exceptions.TarjetaSinCupoException;

public class VentanaReservar extends JFrame
{
	private RentACar aplicacion;
	private icPanelOpciones reservarPanelInformacion;
	private VehiculoBase carroSeleccionado;
	
	private LocalDate fechaRecogida;
	private LocalDate fechaDevolucion;
	private LocalTime horaRecogida;
	private LocalTime horaDevolucion;
	private String sedeRecogida;
	private String sedeDevolucion;
	private char categoria;
	
	
	private Cliente cliente;
	
	private ReservaPanelCentro reservaPanelCentro;
	
	//lleva en 0 el precio total y en 1 el 30%
	private double[] precios;
	
	public VentanaReservar(Cliente cliente, RentACar aplicacion , double[] precios , VehiculoBase carroSeleccionado,
			LocalDate fechaRecogida, LocalDate fechaDevolucion, LocalTime horaRecogida,
			LocalTime horaDevolucion, String sedeRecogida, String sedeDevolucion , char categoria)
	{
		this.aplicacion = aplicacion;
		
		setLayout(new BorderLayout());
		
		setTitle("Reservar");
		setSize(1000,700);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		this.reservaPanelCentro = new ReservaPanelCentro(this, getPasarelas());
		this.precios = precios;
		this.carroSeleccionado = carroSeleccionado;
		this.cliente = cliente;
		
		add(reservaPanelCentro, BorderLayout.CENTER);
		
		double precioTot = precios[0];
		double precio30 = precios[1];
		
		reservaPanelCentro.setPrecioCompleto(precioTot);
		reservaPanelCentro.set30(precio30);
		
		//paremetros adicionales
		this.fechaRecogida = fechaRecogida;
		this.fechaDevolucion = fechaDevolucion;
		this.horaRecogida = horaRecogida;
		this.horaDevolucion = horaDevolucion;
		this.sedeRecogida = sedeRecogida;
		this.sedeDevolucion = sedeDevolucion;
		this.categoria = categoria;
	}
	
	public void cerrarVentana()
	{
		dispose();
	}
	
	//es llamada desde el panel
	public void reservarLazy()
	{
		reservarCompleta(cliente, precios[1], carroSeleccionado,fechaRecogida, fechaDevolucion, horaRecogida, horaDevolucion, sedeRecogida, sedeDevolucion, categoria);
	}
	
	public void reservarCompleta(Cliente cliente, double precio30, VehiculoBase carroSelecionado2, 
			LocalDate fechaRecogida, LocalDate fechaDevolucion, 
			LocalTime horaRecogida, LocalTime horaDevolucion, 
			String sedeRecogida, String sedeDevolucion, char categoria)
	{
		
		//se actualiza la informacion creando la reserva y actualizando la data del vechiculo
		aplicacion.reservarDefinitivo(cliente, precio30, carroSelecionado2, 
				fechaRecogida, fechaDevolucion, 
				 horaRecogida,  horaDevolucion, 
				 sedeRecogida,  sedeDevolucion,  categoria);
		
		JOptionPane.showMessageDialog(this, "Reserva realizada con éxito",
				"Reserva exitosa", JOptionPane.INFORMATION_MESSAGE);
		
		cerrarVentana();
	}
	
	public String[] getPasarelas()
	{
		String[] arrayPasarelas = aplicacion.getPasarelas().toArray(new String[0]);
		return arrayPasarelas;
	}

	public boolean pagar(String pasarela)
	{
		boolean retVar = false;
		try 
		{
			 retVar = aplicacion.pagar(pasarela, cliente, (int)precios[1]);
			 //cliente.setBloqueada(true);
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
}
