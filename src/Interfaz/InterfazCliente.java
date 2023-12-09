package Interfaz;

import java.awt.BorderLayout;
import java.security.PublicKey;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.swing.*;

import Inventario.VehiculoBase;
import Proyecto.RentACar;
import Usuarios.Cliente;

public class InterfazCliente extends JFrame
{
	private RentACar aplicacion;
	private icPanelOpciones panelOpciones;
	private Cliente cliente;
	
	public InterfazCliente(RentACar aplicacion, Cliente cliente)
	{
		this.aplicacion = aplicacion;
		this.cliente = cliente;
		
		setLayout(new BorderLayout());
		
		setTitle("Cliente");
		setSize(1000,700);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//Acá tengo que crear los arrays para los JBox de el panel.
		String[] arrayCategorias =  aplicacion.darCategorias();
		String[] arraySedes = aplicacion.darSedes();
		
		panelOpciones = new icPanelOpciones(arrayCategorias , arraySedes , this);
		add(panelOpciones, BorderLayout.EAST);
		
	}
	
	public void cerrarVentana()
	{
		dispose();
	}
	
	public void reservar(LocalDate fechaRecogida, LocalDate fechaDevolucion, LocalTime horaRecogida,
			LocalTime horaDevolucion, String sedeRecogida, String sedeDevolucion , char categoria)
	{
		//TO-DO MAÑANA
		
		//problemas a manejar - que la sede esté cerrada, que no haya carro disponible. CHECK
		//que la sede sea igual o diferente
		//calcular el precio.
		
		Object[] returnation = aplicacion.reservarCarro(sedeRecogida, sedeDevolucion, fechaRecogida, fechaDevolucion, 
				horaRecogida, horaDevolucion, categoria);
		
		//RETURNATION ES UN INT (0) Y UN ARREGLO DE DOUBLE CON PRECIOS (1)
		
		int comandoError = (int)returnation[0];
		double[] precios = (double[])returnation[1];
		VehiculoBase carroSeleccionado = (VehiculoBase)returnation[2];
		
		if(comandoError == 0)
		{
			panelOpciones.actualizarEstado("La sede de entrega no está abierta en la hora seleccionada");
		}
		
		else if(comandoError == 1)
		{
			panelOpciones.actualizarEstado("La sede de devolucion no está abierta en la hora seleccionada");
		}
		
		else if(comandoError == 2)
		{
			panelOpciones.actualizarEstado("No hay carros disponibles con las características seleccionadas");
		}
		else
		{
			System.out.println("Reserva bien");
			
			
			VentanaReservar ventanaReservar = new VentanaReservar(cliente, aplicacion , precios , carroSeleccionado, fechaRecogida, fechaDevolucion, horaRecogida,
					horaDevolucion, sedeRecogida, sedeDevolucion , categoria);
			ventanaReservar.setVisible(true);
		}
	}
}
