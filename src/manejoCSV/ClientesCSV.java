package manejoCSV;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import Usuarios.Cliente;

public class ClientesCSV 
{
	//Paso un cliente y me devuelve una linea CSV
	public static String toCSV(Cliente cliente) 
	{
	    return cliente.getUsuario() + "," + cliente.getContraseña() + "," + cliente.getTipoUsuario() + "," 
	    		+ cliente.getNombre() + "," + cliente.getNumeroDocumento() + "," + cliente.getTelefono() + "," + cliente.getCorreo() + "," 
	    		+ cliente.getFecha_nacimiento() + "," + cliente.getNumeroLicencia() + "," + cliente.getPaisExpedición()+","+
	    		cliente.getFechaVencimientoLicencia()+","+ cliente.getNumeroTarjeta()+","+cliente.getCodigoSeguridad()
	    		+","+cliente.getFechaVencimientoTarjeta() + "," + cliente.isBloqueada() + "," + cliente.getCupoTarjeta();
	}
	public static void actualizarCSV(Map<String, Cliente> mapaClientes, String nombreArchivo)
	//toma los elementos del mapa y actualiza el CSV.
	
	{
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) 
	    {
	        for (Map.Entry<String, Cliente> entry : mapaClientes.entrySet()) 
	        {
	            Cliente cliente = entry.getValue();
	            writer.write(ClientesCSV.toCSV(cliente));
	            writer.newLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static Cliente fromCSV(String lineaCSV) 
	//Devuelve un cliente a partir de una linea CSV
	
	{
	    String[] partes = lineaCSV.split(",");
	    if (partes.length != 16) {
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
	    boolean tarjetaBloqueada = Boolean.parseBoolean(partes[14]);
	    int cupoTarjeta = Integer.parseInt(partes[15]);
	    
	    Cliente cliente = new Cliente(usuario, contraseña, tipoUsuario, nombre, numeroDocumento, 
	    		telefono, correo, fecha_nacimiento, numeroLicencia, paisExpedicion, 
	    		fechaVencimientoLicencia, numeroTarjeta, codigoSeguridad, fechaVencimientoTarjeta, tarjetaBloqueada, cupoTarjeta);
	    return cliente;
	}
	
	
}
