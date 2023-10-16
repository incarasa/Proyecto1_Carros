package Usuarios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Inventario.Carro;
import Inventario.Manejo_CSV;
import manejoCSV.ClientesCSV;

//La clase ususarios es una clase con un mapa encargada de gestionar los usuarios.
//El mapa tiene como llaves el usuario y como valor el usuario de acurdo con el mapa.
public class Usuarios 
{
	private Map<String, Cliente> mapaClientes;
	private Map<String, Administrador_Sede> mapaAdministradores_Sede;
	private Map<String, Empleado> mapaEmpleados;
	private Administrador_Principal admin_princ;
	
	private String rutaClientesCSV;
	
	
	//constructor
	
	public Usuarios()
	{
		mapaClientes = new HashMap<>();
		mapaAdministradores_Sede = new HashMap<>();
		mapaEmpleados = new HashMap<>();
		
		rutaClientesCSV = "data/usuarios/clientes.csv";
	}
	
	//metodos
	
	public void crearCliente(String usuario, String contraseña, String tipoUsuario, String nombre, String numeroDocumento, 
			String telefono, String correo, String fecha_nacimiento, String numeroLicencia, 
			String paisExpedición, String fechaVencimientoLicencia, 
			String numeroTarjeta, String codigoSeguridad, String fechaVencimientoTarjeta)
	{
		Cliente cliente = new Cliente(usuario, contraseña, tipoUsuario, nombre, numeroDocumento, telefono, correo, fecha_nacimiento,
				numeroLicencia, paisExpedición, fechaVencimientoLicencia, numeroTarjeta, codigoSeguridad,
				fechaVencimientoTarjeta);
		
		mapaClientes.put(usuario, cliente);
		ClientesCSV.actualizarCSV(mapaClientes, rutaClientesCSV);
	}
	
	//Cargar Datos
	public void cargarClientesDesdeCSV() 
    {;
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaClientesCSV))) 
        {
            String linea;
            while ((linea = reader.readLine()) != null) 
            {
                Cliente cliente = ClientesCSV.fromCSV(linea);
                mapaClientes.put(cliente.getUsuario(), cliente);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
