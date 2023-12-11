package model.Vehiculos;

import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.HorarioAtencion;

public abstract class Vehiculo 

{
	protected String tipo;
    protected String placa;
    protected String marca;
    protected int modelo;
    protected char categoría;
    protected String sede;
    
    protected boolean alquilado;
    protected boolean disponible;
    
    protected boolean lavandose;
    protected boolean enMantenimiento;
    protected String fechaDispnibleNuevamente;
    protected String rutaImagen; 
    
    protected List<LocalDate> diasNoDisponible;  //lista de días que el vehiculo esta reservado

    
    //constructor
    
    //METODOS Reservas
    public boolean esReservable(LocalDate diaInicio, LocalDate diaFin) // O ALQUILABLE?
    {
    	boolean reservable = true;
    	while((diaInicio.isBefore(diaFin) || diaInicio.isEqual(diaFin))&&(reservable))
    	{
    		if(diasNoDisponible.contains(diaInicio)) 
    		{
    			System.out.println("DIA NO DISPONIBLE " + diaInicio);
    			reservable = false;
    		}
    		diaInicio = diaInicio.plusDays(1);
    		
    	}
    	return reservable;
    }
    
    public void reservar(LocalDate diaInicio, LocalDate diaFin)
    {
    	while((diaInicio.isBefore(diaFin) || diaInicio.isEqual(diaFin)))
    	{
    		diasNoDisponible.add(diaInicio);
    		diaInicio = diaInicio.plusDays(1);
    		
    	}
    }

    public void quitarReserva(LocalDate diaInicio, LocalDate diaFin)
    {
    	while((diaInicio.isBefore(diaFin) || diaInicio.isEqual(diaFin)))
    	{
    		diasNoDisponible.remove(diaInicio);
    		diaInicio = diaInicio.plusDays(1);
    	}
    }
    
    
    //GETTERS Y SETTERS
	public char getCategoría() {
		return categoría;
	}

	public void setCategoría(char categoría) {
		this.categoría = categoría;
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

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getPlaca() {
		return placa;
	}

	public String getMarca() {
		return marca;
	}

	public int getModelo() {
		return modelo;
	}
	
	public boolean getLavandose() {
		return lavandose;
	}
    
	public boolean getEnMantenimiento() {
		return enMantenimiento;
	}
	public String getFechaDisponibleNuevamente() 
	{
		return fechaDispnibleNuevamente;
	}
	
	
	public String getRutaImagen() 
	{
		return rutaImagen;
	}
	
	public List<LocalDate> getDiasNoDisponible() 
	{
		return diasNoDisponible;
	}
    
	public abstract ArrayList<String> getCaracteristicas();
    
	public abstract void setCaracterísticas(ArrayList<String> datos);
	
	public abstract String getTipo();
    
    
    

}