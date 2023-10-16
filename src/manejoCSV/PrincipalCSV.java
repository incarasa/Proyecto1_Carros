package manejoCSV;

import Usuarios.Administrador_Principal;


public class PrincipalCSV 
{
	public static Administrador_Principal fromCSV(String lineaCSV) 
	//Devuelve un admin a partir de una linea CSV
	
	{
	    String[] partes = lineaCSV.split(",");
	    if (partes.length != 4) {
	        throw new IllegalArgumentException("Formato CSV no válido");
	    }

	    String usuario = partes[0];
	    String contraseña = partes[1];
	    String tipoUsuario = partes[2];
	    String nombre = partes[3];
	    
	    
	    Administrador_Principal adminp = new Administrador_Principal(usuario, contraseña, tipoUsuario, nombre);
	    return adminp;
	}
}
