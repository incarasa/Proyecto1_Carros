package Inventario;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Map;

public class Manejo_CSV 

{
	
	private static FactoryVehiculos factory = FactoryVehiculos.getInstance();
	
	/**
	 * Paso un carro y me devuelve un string con CSV
	 * @param carro
	 * @return String (linea CSV)
	 */
	public static String toCSV(VehiculoBase carro) 
	{
		ArrayList<String> caracteristicas = carro.getCaracteristicas();
		
		String agregar = "";
		int i = 0;
		for (String dato: caracteristicas) {
			if (i > 0) {
			agregar = agregar + ";" + dato;
			} else {
				agregar = dato;
			}
			
			i++;
		}
		
	    return carro.getPlaca() + "," + carro.getMarca() + "," + carro.getModelo() + "," 
	    		+ agregar + "," + carro.getCategoría() + "," 
	    		+ carro.isAlquilado() + "," + carro.isDisponible() + "," + carro.getSede() + 
	    		"," + carro.getLavandose() +  "," + carro.getEnMantenimiento() 
	    		+ "," + carro.getFechaDisponibleNuevamente() + "," + carro.getRutaImagen() + "," + carro.getTipo();
	}
	
	//Retorna un carro desde una linea de CSV
	public static VehiculoBase fromCSV(String lineaCSV) 
	{
	    String[] partes = lineaCSV.split(",");
	    if (partes.length != 13) {
	        throw new IllegalArgumentException("Formato CSV no válido");
	    }
	    
	    DTOInfoVehiculo datosCrear = new DTOInfoVehiculo();
	    
	    datosCrear.setPlaca(partes[0]);
	    datosCrear.setMarca(partes[1]);
	    datosCrear.setModelo(Integer.parseInt(partes[2]));
	    
	    
	    ArrayList<String> caracAgregar = new ArrayList<>();
	    String[] caracteristicas = partes[3].split(";");
		for (String carac: caracteristicas) {
		    caracAgregar.add(carac);
		 }
	    
	    datosCrear.setCaracteristicas(caracAgregar);
	    datosCrear.setCategoría(partes[4].charAt(0));
	    datosCrear.setAlquilado(Boolean.parseBoolean(partes[5]));
	    datosCrear.setDisponible(Boolean.parseBoolean(partes[6]));
	    datosCrear.setSede(partes[7]);
	    datosCrear.setLavandose(Boolean.parseBoolean(partes[8]));
	    datosCrear.setEnMantenimiento(Boolean.parseBoolean(partes[9]));
	    datosCrear.setFechaDisponibleNuevamente(partes[10]);
	    datosCrear.setRutaImagen(partes[11]);
	    datosCrear.setTipo(partes[12]);
	  
	    VehiculoBase retorno = null;
		try {
			retorno = factory.crearVehiculo(datosCrear);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return retorno;
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
	
	public static LocalDate lineaCSVaDateMesNumero(String linea) //con mes número
	{
		String elementos[] = linea.split("/");
		int año = Integer.parseInt(elementos[0]);
		int mes = Integer.parseInt(elementos[1]);
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
