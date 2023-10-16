package Usuarios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Inventario.Carro;
import Inventario.Manejo_CSV;
import manejoCSV.AdminSedeCSV;
import manejoCSV.ClientesCSV;
import manejoCSV.EmpleadosCSV;
import manejoCSV.PrincipalCSV;

//La clase ususarios es una clase con un mapa encargada de gestionar los usuarios.
//El mapa tiene como llaves el usuario y como valor el usuario de acurdo con el mapa.
public class Usuarios 
{
	private Administrador_Principal admin_princ;
	private Map<String, Cliente> mapaClientes;
	private Map<String, Empleado> mapaEmpleados;
	private Map<String, Administrador_Sede> mapaAdministradores_Sede;
	
	
	private String rutaClientesCSV;
	private String rutaEmpleadosCSV;
	private String rutaAdminsCSV;
	
	
	//constructor
	
	public Usuarios()
	{
		mapaClientes = new HashMap<>();
		mapaEmpleados = new HashMap<>();
		mapaAdministradores_Sede = new HashMap<>();
			
		rutaClientesCSV = "data/usuarios/clientes.csv";
		rutaEmpleadosCSV = "data/usuarios/empleados.csv";
		rutaAdminsCSV = "data/usuarios/admins.csv";
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
	
	
	public void crearEmpleado(String usuario, String contraseña, String tipoUsuario, String nombre,
			String nombreSede)
	{
		Empleado empleado = new Empleado(usuario, contraseña, tipoUsuario, nombre, nombreSede);
		mapaEmpleados.put(usuario, empleado);
		EmpleadosCSV.actualizarCSV(mapaEmpleados, rutaEmpleadosCSV);
	}
	
	public void crearAdminSede(String usuario, String contraseña, String tipoUsuario, String nombre,
			String nombreSede)
	{
		Administrador_Sede admin = new Administrador_Sede(usuario, contraseña, tipoUsuario, nombre, nombreSede);
		mapaAdministradores_Sede.put(usuario, admin);
		AdminSedeCSV.actualizarCSV(mapaAdministradores_Sede, rutaAdminsCSV);
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
	
	
	public void cargarEmpleadosDesdeCSV() 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaEmpleadosCSV))) 
        {
            String linea;
            while ((linea = reader.readLine()) != null) 
            {
                Empleado empleado = EmpleadosCSV.fromCSV(linea);
                mapaEmpleados.put(empleado.getUsuario(), empleado);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
	
	public void cargarAdminsDesdeCSV() 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaEmpleadosCSV))) 
        {
            String linea;
            while ((linea = reader.readLine()) != null) 
            {
                Administrador_Sede admin = AdminSedeCSV.fromCSV(linea);
                mapaAdministradores_Sede.put(admin.getUsuario(), admin);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
	
	public void cargarPrincipalDesdeCSV() 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaEmpleadosCSV))) 
        {
            String linea;
            while ((linea = reader.readLine()) != null) 
            {
                Administrador_Principal adminp = PrincipalCSV.fromCSV(linea);
                this.admin_princ = adminp;
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
	
}
