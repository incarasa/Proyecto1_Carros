package Inventario;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Manejo_CSV 

{
	//Paso un carro y me devuelve una linea CSV
	public static String toCSV(Carro carro) 
	{
	    return carro.getPlaca() + "," + carro.getMarca() + "," + carro.getModelo() + "," 
	    		+ carro.getTransmision() + "," + carro.getCategoría() + "," 
	    		+ carro.isAlquilado() + "," + carro.isDisponible() + "," + carro.getSede();
	}
	public static void actualizarCSV(Map<String, Carro> mapaCarros, String nombreArchivo)
	//toma los elementos del mapa y actualiza el CSV.
	
	{
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) 
	    {
	        for (Map.Entry<String, Carro> entry : mapaCarros.entrySet()) 
	        {
	            Carro carro = entry.getValue();
	            writer.write(Manejo_CSV.toCSV(carro));
	            writer.newLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static Carro fromCSV(String lineaCSV) 
	{
	    String[] partes = lineaCSV.split(",");
	    if (partes.length != 8) {
	        throw new IllegalArgumentException("Formato CSV no válido");
	    }

	    String placa = partes[0];
	    String marca = partes[1];
	    int modelo = Integer.parseInt(partes[2]);
	    String transmision = partes[3];
	    String categoria = partes[4];
	    boolean alquilado = Boolean.parseBoolean(partes[5]);
	    boolean disponible = Boolean.parseBoolean(partes[6]);
	    String sede = partes[7];
	    
	    Carro carro = new Carro(placa, marca, modelo, transmision, categoria, sede);
	    return carro;
	}
	
	
}
