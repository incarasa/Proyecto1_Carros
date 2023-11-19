package Inventario;

import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Instalaciones.horarioAtencion;

public class Carro 

{
    private String placa;
    private String marca;
    private int modelo;
    private String transmision;
    private char categoría;
    private String sede;
    
    private boolean alquilado;
    private boolean disponible;
    
    private boolean lavandose;
    private boolean enMantenimiento;
    private String fechaDispnibleNuevamente;
    private String rutaImagen; 
    
    private List<LocalDate> diasNoDisponible;  //lista de días que el vehiculo esta reservado

    
    //constructor

    public Carro(String placa, String marca, int modelo, String transmision, char categoría,
    		boolean alquilado, boolean disponible, String sede, boolean lavandose,
    		boolean enMantenimiento, String fechaDisponibleNuevamente, String rutaImagen) {
		super();
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.transmision = transmision;
		this.categoría = categoría;
		this.alquilado = alquilado;
		this.disponible = disponible;
		this.sede = sede;
		
		
		this.lavandose = lavandose;
		this.enMantenimiento = enMantenimiento;
		this.fechaDispnibleNuevamente = fechaDisponibleNuevamente;
		this.rutaImagen = rutaImagen;
		
		diasNoDisponible = new ArrayList<LocalDate>();
	}

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

	public String getTransmision() {
		return transmision;
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
    
    
    
    
    

}