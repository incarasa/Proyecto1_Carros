package Alquiler;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Inventario.Manejo_CSV;
import Tarifas.Conductor;
import manejoCSV.AlquilerCSV;
import manejoCSV.ConductorCSV;

//hola
public class Alquiler 
{
	private String placaAuto;
	private String cedulaCliente;
	private LocalDate fechaRecogida;
	private LocalDate fechaEntrega;
	private String sedeRecogida;
	private String sedeEntrega;
	private List<Conductor> conductores;
	
	public Alquiler(String placaAuto, String cedulaCliente, LocalDate fechaEntrega, LocalDate fechaRecogida,
			String sedeRecogida, String sedeEntrega)
	{
		this.placaAuto = placaAuto;
		this.cedulaCliente = cedulaCliente;
		this.fechaRecogida = fechaRecogida;
		this.fechaEntrega = fechaEntrega;
		this.sedeRecogida = sedeRecogida;
		this.sedeEntrega = sedeEntrega;
		this.conductores = new ArrayList<>();
	}

	
	/**
	 * Guarda un alquiler en un archivo y sus clientes los guarda en las siguientes lineas.
	 * @param nombreArchivo
	 */
	 public void guardarEnArchivo(String nombreArchivo) 
		{
	        try (FileWriter writer = new FileWriter(nombreArchivo)) 
	        {
	            writer.write(AlquilerCSV.toCSV(this) + "\n");
	            for (Conductor conductor : conductores) 
	            {
	                writer.write(ConductorCSV.toCSV(conductor)+"\n");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 
	 /**
	  * Añade un conductor al Alquiler
	  * @param conductor
	  */
	 public void añadirConductor(Conductor conductor)
	 {
		 conductores.add(conductor);
	 }
	
	 
	 
	 
	//metodos
	
	public String getPlacaAuto() {
		return placaAuto;
	}

	public String getCedulaCliente() {
		return cedulaCliente;
	}


	public LocalDate getFechaRecogida() {
		return fechaRecogida;
	}

	public void setFechaRecogida(LocalDate fechaRecogida) {
		this.fechaRecogida = fechaRecogida;
	}

	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getSedeRecogida() {
		return sedeRecogida;
	}

	public String getSedeEntrega() {
		return sedeEntrega;
	}
	
	public List<Conductor> getListaConductores()
	{
		return conductores;
	}

	public void setSedeEntrega(String sedeEntrega) {
		this.sedeEntrega = sedeEntrega;
	}
	
	
	
	

	
}
