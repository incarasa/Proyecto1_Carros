package model.Vehiculos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DTOInfoVehiculo {
	
	private String tipo;
	private String placa;
	private ArrayList<String> caracteristicas;
	private String marca;
	private int modelo;
	private char categoría;
	private String sede;
	private boolean alquilado;
	private boolean disponible;
	private boolean lavandose;
	private boolean enMantenimiento;
	private String fechaDisponibleNuevamente;
	private String rutaImagen;
	private List<LocalDate> diasNoDisponible;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public ArrayList<String> getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(ArrayList<String> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public int getModelo() {
		return modelo;
	}
	public void setModelo(int modelo) {
		this.modelo = modelo;
	}
	public char getCategoría() {
		return categoría;
	}
	public void setCategoría(char categoría) {
		this.categoría = categoría;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	public boolean isAlquilado() {
		return alquilado;
	}
	public void setAlquilado(boolean alquilado) {
		this.alquilado = alquilado;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public boolean isLavandose() {
		return lavandose;
	}
	public void setLavandose(boolean lavandose) {
		this.lavandose = lavandose;
	}
	public boolean isEnMantenimiento() {
		return enMantenimiento;
	}
	public void setEnMantenimiento(boolean enMantenimiento) {
		this.enMantenimiento = enMantenimiento;
	}
	public String getFechaDisponibleNuevamente() {
		return fechaDisponibleNuevamente;
	}
	public void setFechaDisponibleNuevamente(String fechaDisponibleNuevamente) {
		this.fechaDisponibleNuevamente = fechaDisponibleNuevamente;
	}
	public String getRutaImagen() {
		return rutaImagen;
	}
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	public List<LocalDate> getDiasNoDisponible() {
		return diasNoDisponible;
	}
	public void setDiasNoDisponible(List<LocalDate> diasNoDisponible) {
		this.diasNoDisponible = diasNoDisponible;
	}
}
