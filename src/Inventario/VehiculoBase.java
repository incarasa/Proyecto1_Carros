package Inventario;

import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Instalaciones.horarioAtencion;

public abstract class VehiculoBase 

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

	// Métodos para cambiar ALQUILAR CARRO
    
    
    public void alquilarCarro(String cliente , LocalDate diaInicio, LocalDate diaFin) 
    {
        if (!alquilado) 
        {
            alquilado = true;
            sede = "Cliente: " + cliente;
            
            diaFin.plusDays(3); //por temas de limieza se añaden tres días.
            
            //añadir los días del alquiler a días no disponibles
            while((diaInicio.isBefore(diaFin) || diaInicio.isEqual(diaFin)))
        	{
        		diasNoDisponible.add(diaInicio);
        		diaInicio = diaInicio.plusDays(1);
        		
        		guardarEnArchivo("data/carros/" + placa + ".txt");
        	}
        }
    }

    public void devolverCarro() {
        if (!alquilado) 
        {
            System.out.println("El carro no estaba alquilado");
        }
        else
        {
        	alquilado = false;
        }
    }
    
    public void lavarCarro()
    {
    	lavandose = true;
    }
    
    public void manteniemiento()
    {
    	enMantenimiento = true;
    }
    
    public void fechaDisponible(String fecha)
    {
    	fechaDispnibleNuevamente = fecha;
    }
    
    

    public void cambiarSede(String nuevaSede) {
        sede = nuevaSede;
    }
    
    
    //METODOS Reservas
    
    /** Este método debe recorrer cada uno de los días del intervalo
     *  y mirar si es reservable en los días seleccionados
     *  hace un loop y mira si alguno de los días esta en la lista de dias reservados
     *  y de ser así devuelve false.
     */
    public boolean esReservable(LocalDate diaInicio, LocalDate diaFin) // O ALQUILABLE?
    {
    	boolean reservable = true;
    	//Mira cada dia del intervalo y revisa si esta disponible
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
    
    /**
     * Este metodo añade a la lista de dias reservados del vehiculo los días en el intervalo.
     * Y actualiza su archivo CSV.
     * @param diaInicio
     * @param diaFin
     */
    public void reservar(LocalDate diaInicio, LocalDate diaFin)
    {
    	while((diaInicio.isBefore(diaFin) || diaInicio.isEqual(diaFin)))
    	{
    		diasNoDisponible.add(diaInicio);
    		diaInicio = diaInicio.plusDays(1);
    		
    		guardarEnArchivo("data/carros/" + placa + ".txt");
    	}
    }
    /**
     * Pone disponibles los días en el rango
     * @param diaInicio
     * @param diaFin
     */
    public void quitarReserva(LocalDate diaInicio, LocalDate diaFin)
    {
    	while((diaInicio.isBefore(diaFin) || diaInicio.isEqual(diaFin)))
    	{
    		diasNoDisponible.remove(diaInicio);
    		diaInicio = diaInicio.plusDays(1);
    	}
    }
    
    //PERSISTENCIA
    
    /**
     * Este metodo recibe el nombre del archivo y guarda un carro con la primera linea con la 
     * información y la segunda linea en adelante con los días que está reservado.
     */
    public void guardarEnArchivo(String nombreArchivo) 
	{
        try (FileWriter writer = new FileWriter(nombreArchivo)) 
        {
            writer.write(Manejo_CSV.toCSV(this) + "\n");
            for (LocalDate diaReservado : diasNoDisponible) 
            {
                writer.write(diaReservado.getYear() + "/" + diaReservado.getMonth() + "/" + diaReservado.getDayOfMonth() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
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
    
	//Por cada subclase se deben concretar estas funciones para que la carga y guardado de datos funcionen bien
	
	/**
	 * Retorna una lista con las caracteristicas del tipo concreto de objeto
	 * Por ejemplo, si es un Carro, retorna una lista con un elemento que representa su transmision
	 * @return
	 */
	public abstract ArrayList<String> getCaracteristicas();
    
	/**
	 * Agrega las caracteristicas concretas a la instancia concreta de vehiculo
	 * Por ejemplo, si es un Carro, se le pasa una lista cuyo primer elemento sea la transmision del vehiculo
	 * @param datos
	 */
	public abstract void setCaracterísticas(ArrayList<String> datos);
	
	/**
	 * Devuelve el nombre del tipo y clase concreto del objeto
	 * Por ejemplo, si es un Carro, retorna "Carro"
	 * @return
	 */
	public abstract String getTipo();
    
	/**
	 * Retorna una lista con la cantidad y nombre en orden de las caracteristicas de la clase concreta
	 * Por ejemplo, si es un Carro, retorna una lista con el String "Carro"
	 * Si es moto, retorna una lista con dos elementos, el primero es "Cilindraje" y el segundo es "Transmision"
	 * @return
	 */
    public abstract ArrayList<String> getCamposNecesarios();
    

}