package manejoCSV;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import Usuarios.Administrador_Sede;


public class AdminSedeCSV 
{
	//Paso un admin y me devuelve una linea CSV
	public static String toCSV(Administrador_Sede admin) 
	{
	    return admin.getUsuario() + "," + admin.getContraseña() + "," + admin.getTipoUsuario() + "," 
	    		+ admin.getNombre() + "," + admin.getNombreSede();
	}
	public static void actualizarCSV(Map<String, Administrador_Sede> mapaAdmin, String nombreArchivo)
	//toma los elementos del mapa y actualiza el CSV.
	
	{
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) 
	    {
	        for (Map.Entry<String, Administrador_Sede> entry : mapaAdmin.entrySet()) 
	        {
	            Administrador_Sede admin = entry.getValue();
	            writer.write(AdminSedeCSV.toCSV(admin));
	            writer.newLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static Administrador_Sede fromCSV(String lineaCSV) 
	//Devuelve un admin a partir de una linea CSV
	
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
	    
	    
	    Administrador_Sede admin = new Administrador_Sede(usuario, contraseña, tipoUsuario, nombre, nombreSede);
	    return admin;
	}
	
	
}