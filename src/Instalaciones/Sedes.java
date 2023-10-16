package Instalaciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
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
	private List<String> listaArchivos; //Guarda rutas de archivos sede
	private Map<String , Sede> mapaSedes;
	
	
	
	//contructor
	public Sedes() 
	{
		this.listaArchivos = new ArrayList<>();
		this.mapaSedes = new HashMap<>();
	}
	
	//METODOS PARA PASAR DE LISTA A ARCHIVO Y VICEVERSA
	public void cargarListaArchivos()
	//este metodo carga todos los elementos en el archivo de rutas a la lista de archivos de sedes.
	
	{
		try (BufferedReader br = new BufferedReader(new FileReader("data/sedes/rutas.txt"))) 
		{
			String linea;
	        while ((linea = br.readLine()) != null) 
	        {
	        	listaArchivos.add(linea);
	        }
	    }
		
		catch (IOException e) {
	            e.printStackTrace();
	        }

	}
	
	public void escribirListaEnArchivo()
	
	//este metodo escribe las rutas en la lista en el archivo te texto que persiste.
	{
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/sedes/rutas.txt"))) 
        {
            for (String linea : listaArchivos) 
            {
                bw.write(linea);
                bw.newLine(); // Agregar una nueva línea después de cada cadena
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
	
	public void cargarSedesMapa()
	//carga las sedes al mapa desde la lista de rutas.
	
	
	{
		for (String sede_archivo : listaArchivos)
		{
			Sede sede = Sede.cargarDesdeArchivo(sede_archivo);
			mapaSedes.put(sede.getNombre(), sede);
		}
	}

	
	//MANIPULACIÓN DE LAS SEDES
	
	public void crearSede(String nombreSede , String direccionSede)
	//
	{
		Sede sede = new Sede(nombreSede , direccionSede); //crea la sede
		
		mapaSedes.put(nombreSede, sede); //la mete al mapa
		
		//persistencia
		
		String ruta_sede = "data/sedes/" + nombreSede + ".txt";
		sede.guardarEnArchivo(ruta_sede); //guarda la sede en archivo
		listaArchivos.add(ruta_sede); //se añade la ruta del archivo a la lista de rutas.
		escribirListaEnArchivo();// se sobreescribe el archivo de rutas
		
	}
	
	public void definirHorarioSede(String nombreSede, DayOfWeek dia, LocalTime horaApertura, LocalTime horaCierre)
	{
		//este metodo saca la sede del mapa, actualiza su horario y la vuelve a meter.
		
		Sede sede = mapaSedes.get(nombreSede); //pedir la sede
		sede.agregarHorario(dia, horaApertura, horaCierre); //actualizar sede
		mapaSedes.put(nombreSede, sede); //devolver la sede
		
		//como ya se creo el archivo solo se sobreescribe con la nueva info
		sede.guardarEnArchivo("data/sedes/" + nombreSede + ".txt");
		
	}
	//GETTER
	public Map<String, Sede> getMapaSedes() {
		return mapaSedes;
	
	}

	public List<String> getListaArchivos() {
		return listaArchivos;
	}
	
	
	
}
