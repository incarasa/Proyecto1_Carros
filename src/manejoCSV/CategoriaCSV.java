package manejoCSV;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;


public class CategoriaCSV 
{

	
	public static void actualizarCSV(Map<String, String> mapaCategorías, String nombreArchivo)
	//toma los elementos del mapa y actualiza el CSV.
	
	{
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) 
	    {
	        for (Map.Entry<String, String> entry : mapaCategorías.entrySet()) 
	        {
	        	String categoría = entry.getKey();
	            String precioDia = entry.getValue();
	            writer.write(categoría + "," + precioDia);
	            writer.newLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	

}
