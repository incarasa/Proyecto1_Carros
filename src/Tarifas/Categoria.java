package Tarifas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import manejoCSV.CategoriaCSV;

public class Categoria 
{
	private Map<String, String> mapaCategorías;
	
	//constructor
	public Categoria()
	{
		mapaCategorías = new HashMap<>();
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
            while ((linea = reader.readLine()) != null) 
            {
            	String[] partes = linea.split(",");
            	mapaCategorías.put(partes[0], partes[1]);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
