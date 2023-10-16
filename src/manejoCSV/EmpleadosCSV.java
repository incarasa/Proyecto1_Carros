package manejoCSV;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;


import Usuarios.Empleado;

public class EmpleadosCSV 
{
	//Paso un empleado y me devuelve una linea CSV
	public static String toCSV(Empleado empleado) 
	{
	    return empleado.getUsuario() + "," + empleado.getContraseña() + "," + empleado.getTipoUsuario() + "," 
	    		+ empleado.getNombre() + "," + empleado.getNombreSede();
	}
	public static void actualizarCSV(Map<String, Empleado> mapaEmpleados, String nombreArchivo)
	//toma los elementos del mapa y actualiza el CSV.
	
	{
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) 
	    {
	        for (Map.Entry<String, Empleado> entry : mapaEmpleados.entrySet()) 
	        {
	            Empleado empleado = entry.getValue();
	            writer.write(EmpleadosCSV.toCSV(empleado));
	            writer.newLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static Empleado fromCSV(String lineaCSV) 
	//Devuelve un empleado a partir de una linea CSV
	
	{
	    String[] partes = lineaCSV.split(",");
	    if (partes.length != 5) {
	        throw new IllegalArgumentException("Formato CSV no válido");
	    }

	    String usuario = partes[0];
	    String contraseña = partes[1];
	    String tipoUsuario = partes[2];
	    String nombre = partes[3];
	    String nombreSede = partes[4];
	    
	    
	    Empleado empleado = new Empleado(usuario, contraseña, tipoUsuario, nombre, nombreSede);
	    return empleado;
	}
	
	
}
