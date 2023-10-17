package Tarifas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import manejoCSV.CategoriaCSV;

public class Seguros 
{
	private Map<String, String> mapaSeguros;
	
	//constructor
	public Seguros()
	{
		mapaSeguros = new HashMap<>();
	}
	
	//metodos
	public void añadirSeguro(String seguro, String precioDia)
	{
		mapaSeguros.put(seguro, precioDia);
		CategoriaCSV.actualizarCSV(mapaSeguros , "data/tarifas/seguros.csv");
	}
	
	public int precioSeguro(String seguro)
	{
		String precio = mapaSeguros.get(seguro);
		int precio_int = Integer.parseInt(precio);
		return precio_int;
	}
	
	//CARGAR DATOS
	public void cargarSegurosDesdeCSV() 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/tarifas/seguros.csv"))) 
        {
            String linea;
            while ((linea = reader.readLine()) != null) 
            {
            	String[] partes = linea.split(",");
            	mapaSeguros.put(partes[0], partes[1]);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
