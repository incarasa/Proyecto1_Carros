package Instalaciones;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.EnumMap;
import java.util.Map;

public class Sede 
{
	
	private String nombre;
	private String direccion;
	private Map<DayOfWeek , horarioAtencion> horarios;
	
	//contructor
	public Sede(String nombre, String direccion)
	{
		this.nombre = nombre;
		this.direccion = direccion;
		this.horarios = new EnumMap<>(DayOfWeek.class);
	}
	
	//metodos
	public void agregarHorario(DayOfWeek dia, LocalTime horaApertura, LocalTime horaCierre)
	{
		//este metodo recibe un día y una hora de apertura y cierre y 
		//crea un horario para este día.
		
		horarioAtencion horario = new horarioAtencion(horaApertura, horaCierre);
		horarios.put(dia, horario);

	}
	
	public boolean estaAbierta(DayOfWeek dia, LocalTime horaCliente)
	{
		//me dice si la sede está abierta en un día y un horario
		
		horarioAtencion horario = horarios.get(dia);
		return horario != null && horario.estaEnHorario(horaCliente);
	}
	
	//metodos de persistencia
	public void guardarEnArchivo(String nombreArchivo) 
	{
        try (FileWriter writer = new FileWriter(nombreArchivo)) 
        {
            writer.write(nombre + "\n");
            writer.write(direccion + "\n");
            for (Map.Entry<DayOfWeek, horarioAtencion> entry : horarios.entrySet()) 
            {
                writer.write(entry.getKey() + "," + entry.getValue().getHoraApertura() + "," + entry.getValue().getHoraCierre() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static Sede cargarDesdeArchivo(File archivo) 
	{
        Sede sede = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) 
        {
            String nombre = reader.readLine();
            String direccion = reader.readLine();
            sede = new Sede(nombre , direccion);
            String linea;
            while ((linea = reader.readLine()) != null) 
            {
                String[] partes = linea.split(",");
                DayOfWeek dia = DayOfWeek.valueOf(partes[0]);
                LocalTime horaApertura = LocalTime.parse(partes[1]);
                LocalTime horaCierre = LocalTime.parse(partes[2]);
                sede.agregarHorario(dia, horaApertura, horaCierre);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return sede;
    }
	
	//GETTERS
	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}

}
