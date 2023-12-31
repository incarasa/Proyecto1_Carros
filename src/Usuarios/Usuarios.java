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
	private Map<String, Cliente> mapaClientesCedula;
	private Map<String, Empleado> mapaEmpleados;
	private Map<String, Administrador_Sede> mapaAdministradores_Sede;
	
	
	private String rutaClientesCSV;
	private String rutaEmpleadosCSV;
	private String rutaAdminsCSV;
	private String rutaPrincipalCSV;
	
	/*
	 * Este artributo sera 1 - cliente, 2 - empleado, 3 - admin, 4 - principal
	 * 0 - usuario no existe, 10 - contraseña errada. 
	 * Me permite saber
	 * quien fue el que inicio sesión.
	 */
	private int tipoUsuario;
	
	//constructor
	
	public Usuarios()
	{
		mapaClientes = new HashMap<>();
		mapaClientesCedula = new HashMap<>();
		mapaEmpleados = new HashMap<>();
		mapaAdministradores_Sede = new HashMap<>();
			
		rutaClientesCSV = "data/usuarios/clientes.csv";
		rutaEmpleadosCSV = "data/usuarios/empleados.csv";
		rutaAdminsCSV = "data/usuarios/admins.csv";
		rutaPrincipalCSV = "data/usuarios/principal.csv";
	}
	
	//metodos
	
	public void crearCliente(String usuario, String contraseña, String tipoUsuario, String nombre, String numeroDocumento, 
			String telefono, String correo, String fecha_nacimiento, String numeroLicencia, 
			String paisExpedición, String fechaVencimientoLicencia, 
			String numeroTarjeta, String codigoSeguridad, String fechaVencimientoTarjeta, boolean tarjetaBloqueada, int cupoTarjeta)
	{
		Cliente cliente = new Cliente(usuario, contraseña, tipoUsuario, nombre, numeroDocumento, telefono, correo, fecha_nacimiento,
				numeroLicencia, paisExpedición, fechaVencimientoLicencia, numeroTarjeta, codigoSeguridad,
				fechaVencimientoTarjeta, tarjetaBloqueada, cupoTarjeta);
		
		mapaClientes.put(usuario, cliente);
		mapaClientesCedula.put(numeroDocumento, cliente);
		ClientesCSV.actualizarCSV(mapaClientes, rutaClientesCSV);
	}
	
	
	public void crearEmpleado(String usuario, String contraseña, String tipoUsuario, String nombre,
			String nombreSede)
	{
		Empleado empleado = new Empleado(usuario, contraseña, tipoUsuario, nombre, nombreSede);
		mapaEmpleados.put(usuario, empleado);
		EmpleadosCSV.actualizarCSV(mapaEmpleados, rutaEmpleadosCSV);
	}
	
	public void eliminarEmpleado(String usuario)
	{
		mapaEmpleados.remove(usuario);
		EmpleadosCSV.actualizarCSV(mapaEmpleados, rutaEmpleadosCSV);
	}
	
	
	public void crearAdminSede(String usuario, String contraseña, String tipoUsuario, String nombre,
			String nombreSede)
	{
		Administrador_Sede admin = new Administrador_Sede(usuario, contraseña, tipoUsuario, nombre, nombreSede);
		mapaAdministradores_Sede.put(usuario, admin);
		AdminSedeCSV.actualizarCSV(mapaAdministradores_Sede, rutaAdminsCSV);
	}
	
	public void eliminarAdminSede(String usuario)
	{
		mapaAdministradores_Sede.remove(usuario);
		AdminSedeCSV.actualizarCSV(mapaAdministradores_Sede, rutaAdminsCSV);
	}
	
	/**
	 * Este metodo carga a los todos usuarios desde sus archivos CSV 
	 * llamando a las funciones auxiliares.
	 */
	public void cargarUsuarios()
	{
		cargarClientesDesdeCSV();
		cargarClientesCedulaDesdeCSV();
		cargarEmpleadosDesdeCSV();
		cargarAdminsDesdeCSV();
		cargarPrincipalDesdeCSV();
		
	}
	
	
	private void cargarClientesDesdeCSV() 
    {
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
	
	private void cargarClientesCedulaDesdeCSV() 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaClientesCSV))) 
        {
            String linea;
            while ((linea = reader.readLine()) != null) 
            {
                Cliente cliente = ClientesCSV.fromCSV(linea);
                mapaClientesCedula.put(cliente.getNumeroDocumento(), cliente);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
	
	private void cargarEmpleadosDesdeCSV() 
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
	
	private void cargarAdminsDesdeCSV() 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaAdminsCSV))) 
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
	
	private void cargarPrincipalDesdeCSV() 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaPrincipalCSV))) 
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
	
	//FUNCIONES DE VERIFICACION DE USUARIO
	
	/**
	 * La funcion verificar va verificando en las diferentes funciones qué tipo de usuario es.
	 * @param usuario
	 * @param contraseña
	 */
	public void verificar(String usuario, String contraseña)
	{
		this.tipoUsuario = 0;
		verificarCliente(usuario, contraseña); // si no es un cliente sigue saltando a los otros
											   // a través del bloque catch
	}
	
	public void verificarCliente(String usuario, String contraseña)
	{
		try 
		{
			Usuario user = mapaClientes.get(usuario);
			String clave = user.getContraseña();
			if(contraseña.equals(clave))
			{
				this.tipoUsuario = 1; //es cliente
				System.out.println("Ha accedido correctamente como un cliente");
			}
			else
			{
				this.tipoUsuario = 10; //clave incorrecta
				System.out.println("Contraseña incorrecta");
			}
		}
		catch (NullPointerException e) 
		{
			System.out.println("El usuario ingresado no es un cliente");
			verificarEmpleado(usuario, contraseña);
		}
	}
	
	public void verificarEmpleado(String usuario, String contraseña)
	{
		try 
		{
			Usuario user = mapaEmpleados.get(usuario);
			String clave = user.getContraseña();
			if(contraseña.equals(clave))
			{
				this.tipoUsuario = 2; //cambia el tipo de usuario
				System.out.println("Ha accedido correctamente como un empleado");
			}
			else 
			{
				this.tipoUsuario = 10; //clave incorrecta
				System.out.println("Contraseña incorrecta");
			}
		}
		catch (NullPointerException e) 
		{
			System.out.println("El usuario ingresado no es un empleado");
			verificarAdmin(usuario, contraseña);
		}
	}
	
	public void verificarAdmin(String usuario, String contraseña)
	{ 
		try
		{
			Usuario user = mapaAdministradores_Sede.get(usuario);
			String clave = user.getContraseña();
			if(clave.equals(contraseña))
			{
				this.tipoUsuario = 3; //cambia el tipo de usuario
				System.out.println("Ha accedido correctamente como un administrador");
				//si inicia correctamente retorna true
			}
			else 
			{
				this.tipoUsuario = 10; //clave incorrecta
				System.out.println("Contraseña incorrecta");
			}
		}
		catch (NullPointerException e) 
		{
			System.out.println("El usuario ingresado no es un admin");
			verificarAdminP(usuario, contraseña);
		}
	}
	
	public void verificarAdminP(String usuario, String contraseña)
	{
		
		Usuario administradorPrincipal = admin_princ;
		String clave = administradorPrincipal.getContraseña();
		if(administradorPrincipal.getUsuario().equals(usuario))
		{
			if(contraseña.equals(clave))
			{
				this.tipoUsuario = 4; //cambia el tipo de usuario
				System.out.println("Ha accedido correctamente como el administrador principal");
			}
			else 
			{
				this.tipoUsuario = 10; //contraseña incorrecta
				System.out.println("Contraseña incorrecta");
			}
		}
		else 
		{
			System.out.println("El usuario ingresado no es un prinicipal");
		}

	}
	
	
	public Cliente retornarCliente(String usuario)
	{
		Cliente cliente = mapaClientes.get(usuario);
		return cliente;
		
	}
	public Cliente retornarClienteCedula(String cedula)
	{
		Cliente cliente = mapaClientesCedula.get(cedula);
		return cliente;
		
	}
	
	public Empleado retornarEmpleado(String usuario)
	{
		Empleado empleado = mapaEmpleados.get(usuario);
		return empleado;
		
	}
	
	public Administrador_Sede retornarAdminSede(String usuario)
	{
		Administrador_Sede admin = mapaAdministradores_Sede.get(usuario);
		return admin;
		
	}
	
	public Administrador_Principal retornarAdminPrincipal(String usuario)
	{
		Administrador_Principal adminp = admin_princ;
		return adminp;
		
	}
	
	public int getTipoUsuario()
	{
		return tipoUsuario;
	}

}
