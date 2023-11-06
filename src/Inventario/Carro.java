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
    private String categoría;
    private String sede;
    
    private boolean alquilado;
    private boolean disponible;
    
    private List<LocalDate> diasReservado;  //lista de días que el vehiculo esta reservado

    
    //constructor

    public Carro(String placa, String marca, int modelo, String transmision, String categoría,
    		String sede) {
		super();
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.transmision = transmision;
		this.categoría = categoría;
		this.alquilado = false;
		this.disponible = true;
		this.sede = sede;
		
		diasReservado = new ArrayList<LocalDate>();
	}

	// Métodos para cambiar el estado del carro
    public void alquilarCarro(String cliente) {
        if (!alquilado) 
        {
            alquilado = true;
            sede = "Cliente: " + cliente;
        }
    }

    public void devolverCarro() {
        if (alquilado) {
            alquilado = false;
        }
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
    public boolean esReservable(LocalDate diaInicio, LocalDate diaFin)
    {
    	boolean reservable = true;
    	//Mira cada dia del intervalo y revisa si esta disponible
    	while((diaInicio.isBefore(diaFin) || diaInicio.isEqual(diaFin))&&(reservable))
    	{
    		if(diasReservado.contains(diaInicio)) 
    		{
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
    		diasReservado.add(diaInicio);
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
    		diasReservado.remove(diaInicio);
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
            for (LocalDate diaReservado : diasReservado) 
            {
                writer.write(diaReservado.getYear() + "/" + diaReservado.getMonth() + "/" + diaReservado.getDayOfMonth() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    //GETTERS Y SETTERS
	public String getCategoría() {
		return categoría;
	}

	public void setCategoría(String categoría) {
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

    


    
    
    
    
    

}