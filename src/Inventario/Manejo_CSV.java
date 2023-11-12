package Inventario;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

public class Manejo_CSV 

{
	/**
	 * Paso un carro y me devuelve un string con CSV
	 * @param carro
	 * @return String (linea CSV)
	 */
	public static String toCSV(Carro carro) 
	{
	    return carro.getPlaca() + "," + carro.getMarca() + "," + carro.getModelo() + "," 
	    		+ carro.getTransmision() + "," + carro.getCategoría() + "," 
	    		+ carro.isAlquilado() + "," + carro.isDisponible() + "," + carro.getSede();
	}
	
	//Retorna un carro desde una linea de CSV
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
	    char categoria = partes[4].charAt(0);
	    boolean alquilado = Boolean.parseBoolean(partes[5]);
	    boolean disponible = Boolean.parseBoolean(partes[6]);
	    String sede = partes[7];
	    
	    Carro carro = new Carro(placa, marca, modelo, transmision, categoria, sede);
	    return carro;
	}
	
	/**
	 * Esta funcion toma una linea del CSV y la convierte en una fecha LocalDate
	 * @param linea
	 * @return
	 */
	public static LocalDate lineaCSVaDate(String linea)
	{
		String elementos[] = linea.split("/");
		int año = Integer.parseInt(elementos[0]);
		Month mes = Month.valueOf(elementos[1]);
		int dia = Integer.parseInt(elementos[2]);
		
		LocalDate fecha = LocalDate.of(año, mes, dia);
		return fecha;
		
		
	}
	
	
//----------------------------------------
	/*
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
	
	*/
	
}
