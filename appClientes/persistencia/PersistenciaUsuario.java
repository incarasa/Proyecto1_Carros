package persistencia;

import model.Usuario;

public class PersistenciaUsuario
{
	public  Usuario fromCSV(String lineaCSV) 
	
	{
	    String[] partes = lineaCSV.split(",");
	    if (partes.length != 14) {
	        throw new IllegalArgumentException("Formato CSV no válido");
	    }

	    String usuario = partes[0];
	    String contraseña = partes[1];
	    String tipoUsuario = partes[2];
	    String nombre = partes[3];
	    String numeroDocumento = partes[4];
	    String telefono = partes[5];
	    String correo = partes[6];
	    String fecha_nacimiento = partes[7];
	    String numeroLicencia = partes[8];
	    String paisExpedicion = partes[9];
	    String fechaVencimientoLicencia = partes[10];
	    String numeroTarjeta = partes[11];
	    String codigoSeguridad = partes[12];
	    String fechaVencimientoTarjeta = partes[13];
	    
	    Usuario usuarioCliente = new Usuario(usuario, contraseña, tipoUsuario, nombre, numeroDocumento, 
	    		telefono, correo, fecha_nacimiento, numeroLicencia, paisExpedicion, 
	    		fechaVencimientoLicencia, numeroTarjeta, codigoSeguridad, fechaVencimientoTarjeta);
	    return usuarioCliente;
	}
}
