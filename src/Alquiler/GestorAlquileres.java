package Alquiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Inventario.Carro;
import Inventario.Manejo_CSV;
import Tarifas.Conductor;
import manejoCSV.AlquilerCSV;
import manejoCSV.ConductorCSV;

public class GestorAlquileres 
{
	private Map<String, Alquiler> mapaAlquileres;
	
	public GestorAlquileres()
	{
		this.mapaAlquileres = new HashMap<String, Alquiler>();
	}
	
	public void crearAlquiler(String placaAuto, String cedulaCliente, LocalDate fechaRecogida, LocalDate fechaEntrega,
			String sedeRecogida, String sedeEntrega , List<Conductor> listaConductores)
	{
		Alquiler alquiler = new Alquiler(placaAuto, cedulaCliente, fechaRecogida, fechaEntrega, 
				sedeRecogida, sedeEntrega);
		
		//añade los conductores de la lista
		for(Conductor conductor : listaConductores)
		{
			alquiler.añadirConductor(conductor);
		}
		
		mapaAlquileres.put(cedulaCliente, alquiler);
		
		//persistencia
		String ruta_alquiler = "data/alquileres/" + cedulaCliente + ".txt";
    	alquiler.guardarEnArchivo(ruta_alquiler); //guarda el alquiler en archivo
    	System.out.println("Aquiler creado");
		
	}
	/**
	 * Devuelve un alquiler dado un número de cedula
	 * @param cedula
	 * @return
	 */
	public Alquiler darAlquiler(String cedula)
	{
		return mapaAlquileres.get(cedula);
	}
	
	public void cargarAlquileresDesdeCarpeta() 
    {
    	File carpetaAlquileres = new File("./data/alquileres");
	
    	//IMPORTANTE
    	File[] archivosAlquileres = carpetaAlquileres.listFiles();
    	for(File alquilerArchivo: archivosAlquileres)
    	{
    		//aca tengo un archivo de alquiler
    		try (BufferedReader br = new BufferedReader(new FileReader(alquilerArchivo))) 
    		{
    			String primeraLinea = br.readLine();
    			Alquiler alquiler = AlquilerCSV.fromCSV(primeraLinea); //retorna un alquiler
    			
    			String linea;
    			linea = br.readLine();
    			while (linea != null) 
    			{
    				//aca debe añadirse al mapa de conductores los conductores
    				alquiler.añadirConductor(ConductorCSV.conductorFromCSV(linea));
    				linea = br.readLine(); //incrementar la linea
    			}
    			mapaAlquileres.put(alquiler.getCedulaCliente(), alquiler); //añado el alquiler al mapa
    		}
		
    		catch (IOException e) 
    		{
	            e.printStackTrace();
    		}
    	}
	}
}
