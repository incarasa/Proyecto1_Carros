package Instalaciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sedes 

//esta clase maneja un mapa con las sedes y permite crear y borrar sedes.
//

{
	//atributos
	private Map<String , Sede> mapaSedes;
	
	
	
	//contructor
	public Sedes() 
	{
		this.mapaSedes = new HashMap<>();
	}
	
	//METODOS
	
	
	public void cargarSedesMapa()
	//carga las sedes al mapa desde la carpeta de sedes.
	
	{
		File carpeta = new File("./data/sedes");
		File[] archivos = carpeta.listFiles();
	
		for (File archivoSede : archivos)
		{
			Sede sede = Sede.cargarDesdeArchivo(archivoSede);
			mapaSedes.put(sede.getNombre(), sede);
		}
	}

	
	//MANIPULACIÓN DE LAS SEDES
	
	public void crearSede(String nombreSede , String direccionSede)
	//
	{
		Sede sede = new Sede(nombreSede , direccionSede); //crea la sede
		
		mapaSedes.put(nombreSede, sede); //la mete al mapa
		
		//horarios por defecto
		definirHorarioSede(nombreSede, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(17, 0));
		definirHorarioSede(nombreSede, DayOfWeek.TUESDAY, LocalTime.of(9, 0), LocalTime.of(17, 0));
		definirHorarioSede(nombreSede, DayOfWeek.WEDNESDAY, LocalTime.of(9, 0), LocalTime.of(17, 0));
		definirHorarioSede(nombreSede, DayOfWeek.THURSDAY, LocalTime.of(9, 0), LocalTime.of(17, 0));
		definirHorarioSede(nombreSede, DayOfWeek.FRIDAY, LocalTime.of(9, 0), LocalTime.of(17, 0));
		definirHorarioSede(nombreSede, DayOfWeek.SATURDAY, LocalTime.of(9, 0), LocalTime.of(17, 0));
		
		//persistencia
		
		String ruta_sede = "data/sedes/" + nombreSede + ".txt";
		sede.guardarEnArchivo(ruta_sede); //guarda la sede en archivo
		
	}
	
	public void eliminarSede(String nombreSede)
	{
		mapaSedes.remove(nombreSede); //remueve la sede del mapa
		
		String ruta_sede = "data/sedes/" + nombreSede + ".txt";
		File archivoSede =  new File(ruta_sede);
		archivoSede.delete(); //se borra el archivo del computador
	}
	
	public void definirHorarioSede(String nombreSede, DayOfWeek dia, LocalTime horaApertura, LocalTime horaCierre)
	{
		//este metodo saca la sede del mapa, actualiza su horario y la vuelve a meter.
		
		Sede sede = mapaSedes.get(nombreSede); //pedir la sede
		if(sede != null)
		{
			sede.agregarHorario(dia, horaApertura, horaCierre); //actualizar sede
			mapaSedes.put(nombreSede, sede); //devolver la sede
		
			//como ya se creo el archivo solo se sobreescribe con la nueva info
			sede.guardarEnArchivo("data/sedes/" + nombreSede + ".txt");
		}
		else {
			System.out.println("SEDE MAL ESCRITA");
		}
		
	}
	//GETTERS
	public Map<String, Sede> getMapaSedes() 
	{
		return mapaSedes;
	}
	
	/**
	 * Retorna una sede dado su nombre
	 * @param nombreSede
	 * @return
	 */
	public Sede getSede(String nombreSede)
	{
		Sede sede = mapaSedes.get(nombreSede);
		return sede;
	}
	
	public int numeroSedes()
	{
		return mapaSedes.size();
	}
	
	public String[] darSedes()
	{
		int numeroSedes = numeroSedes();
		String[] sedesArray = new String[numeroSedes];
		List<String> listaSedes = new ArrayList<String>(mapaSedes.keySet());
		int i = 0;
		for(String sede : listaSedes)
		{
			sedesArray[i] = sede;
			i++;
		}
		return sedesArray;
	}

	
	
	
}
