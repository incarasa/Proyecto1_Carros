package Tarifas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import manejoCSV.CategoriaCSV;

public class Categorias 
{
	private Map<String, String> mapaCategorías;
	
	//constructor
	public Categorias()
	{
		mapaCategorías = new HashMap<String, String>();
	}
	
	//metodos
	public void añadirCategoría(String categoría, String precioDia)
	{
		mapaCategorías.put(categoría, precioDia);
		CategoriaCSV.actualizarCSV(mapaCategorías , "data/tarifas/categorías.csv");
	}
	
	public int precioCategoría(String categoría)
	{
		String precio = mapaCategorías.get(categoría);
		int precio_int = Integer.parseInt(precio);
		return precio_int;
	}
	
	//CARGAR DATOS
	public void cargarCategoríasDesdeCSV() 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/tarifas/categorías.csv"))) 
        {
            String linea;
            linea = reader.readLine();
            while (linea != null) 
            {
            	String[] partes = linea.split(",");
            	mapaCategorías.put(partes[0], partes[1]);
            	linea = reader.readLine();
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
	
	public Map<String, String> darMapaCategorias()
	{
		return mapaCategorías;
	}
}


	