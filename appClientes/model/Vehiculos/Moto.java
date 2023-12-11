package model.Vehiculos;

import java.time.LocalDate;
import java.util.ArrayList;

public class Moto extends Vehiculo{
	
	private String cilindraje;
	private String transmision;
	
	public Moto(DTOInfoVehiculo datos) {
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
		
		this.cilindraje = datos.getCaracteristicas().get(0);
		this.transmision = datos.getCaracteristicas().get(1);
		this.diasNoDisponible = new ArrayList<LocalDate>();
	}
	
	@Override
	public ArrayList<String> getCaracteristicas() {
		ArrayList<String> datos = new ArrayList<String>();
		datos.add(cilindraje);
		datos.add(transmision);
		
		return datos;
	}

	@Override
	public void setCaracterísticas(ArrayList<String> datos) {
		this.cilindraje = datos.get(0);
		this.transmision = datos.get(1);
	}

	@Override
	public String getTipo() {
		// TODO Auto-generated method stub
		return tipo;
	}

}
