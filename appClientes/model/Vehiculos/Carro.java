package model.Vehiculos;

import java.time.LocalDate;
import java.util.ArrayList;

public class Carro extends Vehiculo{
	
	private String transmision;
	
	public Carro(DTOInfoVehiculo datos) {
		
		this.tipo = datos.getTipo();
		this.placa = datos.getPlaca();
		this.marca = datos.getMarca();
		this.modelo = datos.getModelo();
		this.categoría = datos.getCategoría();
		this.alquilado = datos.isAlquilado();
		this.disponible = datos.isDisponible();
		this.sede = datos.getSede();
		this.lavandose = datos.isLavandose();
		this.enMantenimiento = datos.isEnMantenimiento();
		this.fechaDispnibleNuevamente = datos.getFechaDisponibleNuevamente();
		this.rutaImagen = datos.getRutaImagen();
		this.diasNoDisponible = datos.getDiasNoDisponible();
		
		this.transmision = datos.getCaracteristicas().get(0);
		this.diasNoDisponible = new ArrayList<LocalDate>();
	}

	public String getTransmision() {
		return transmision;
	}

	public void setTransmision(String transmision) {
		this.transmision = transmision;
	}
	
	public ArrayList<String> getCaracteristicas() {
		ArrayList<String> datos = new ArrayList<>();
		datos.add(transmision);
		return datos;
	}

	@Override
	public void setCaracterísticas(ArrayList<String> datos) {
		if (datos.size() == 1) {
			this.transmision = datos.get(0);
		}
		
	}

	@Override
	public String getTipo() {
		return tipo;
	}
	
}
