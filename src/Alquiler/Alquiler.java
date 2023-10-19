package Alquiler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Tarifas.Conductor;

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
	
	public Alquiler(String placaAuto, String cedulaCliente, LocalDate fechaEntrega,
			String sedeRecogida, String sedeEntrega) 
	{
		this.placaAuto = placaAuto;
		this.cedulaCliente = cedulaCliente;
		this.fechaRecogida = LocalDate.now();
		this.fechaEntrega = fechaEntrega;
		this.sedeRecogida = sedeRecogida;
		this.sedeEntrega = sedeEntrega;
		this.conductores = new ArrayList<>();
	}

	//metodos
	
	public String getPlacaAuto() {
		return placaAuto;
	}

	public void setPlacaAuto(String placaAuto) {
		this.placaAuto = placaAuto;
	}

	public String getCedulaCliente() {
		return cedulaCliente;
	}

	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
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

	public void setSedeRecogida(String sedeRecogida) {
		this.sedeRecogida = sedeRecogida;
	}

	public String getSedeEntrega() {
		return sedeEntrega;
	}

	public void setSedeEntrega(String sedeEntrega) {
		this.sedeEntrega = sedeEntrega;
	}
	
	
	
	

	
}
