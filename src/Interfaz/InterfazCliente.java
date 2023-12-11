package Interfaz;

import java.awt.BorderLayout;
import java.security.PublicKey;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import Inventario.Carro;
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
		String [] arrayTipos = getTipos();
		
		panelOpciones = new icPanelOpciones(arrayCategorias , arraySedes , this, arrayTipos);
		add(panelOpciones, BorderLayout.EAST);
		
	}
	
	public void cerrarVentana()
	{
		dispose();
	}
	
	public void reservar(LocalDate fechaRecogida, LocalDate fechaDevolucion, LocalTime horaRecogida,
			LocalTime horaDevolucion, String sedeRecogida, String sedeDevolucion , 
			char categoria, String tipoDeVehiculo)
	{
		//TO-DO MAÑANA
		
		//problemas a manejar - que la sede esté cerrada, que no haya carro disponible. CHECK
		//que la sede sea igual o diferente
		//calcular el precio.
		
		Object[] returnation = aplicacion.reservarCarro(sedeRecogida, sedeDevolucion, fechaRecogida, fechaDevolucion, 
				horaRecogida, horaDevolucion, categoria, tipoDeVehiculo);
		
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
			panelOpciones.actualizarEstado("No hay vehículos disponibles con las características seleccionadas");
		}
		else
		{
			System.out.println("Reserva bien");
			
			
			VentanaReservar ventanaReservar = new VentanaReservar(cliente, aplicacion , 
					precios , carroSeleccionado, fechaRecogida, fechaDevolucion, horaRecogida,
					horaDevolucion, sedeRecogida, sedeDevolucion , categoria);
			
			ventanaReservar.setVisible(true);
		}
	}
	
	public String[] getTipos()
	{
		ArrayList<String> tiposList = aplicacion.getTipos();
		String[] tiposArray = tiposList.toArray(new String[0]);
		return tiposArray;
	}
}
