package Proyecto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import Instalaciones.Sede;
import Instalaciones.Sedes;
import Inventario.Carro;
import Inventario.InventarioCarros;
import Usuarios.Administrador_Principal;
import Usuarios.Administrador_Sede;
import Usuarios.Cliente;
import Usuarios.Empleado;
import Usuarios.Usuarios;

public class main {

	public static void main(String[] args) 
	{
		Usuarios usuarios = new Usuarios();
		usuarios.cargarClientesDesdeCSV();
		usuarios.cargarEmpleadosDesdeCSV();
		usuarios.cargarAdminsDesdeCSV();
		usuarios.cargarPrincipalDesdeCSV();
		
		boolean continuar = true;
		while (true) 
		{
			System.out.println("Bienvenido al sistema de reservas de vehículos");
			System.out.println("1. Iniciar sesión como cliente");
			System.out.println("2. Iniciar sesión como empleado");
			System.out.println("3. Iniciar sesión como administrador");
			System.out.println("4. Iniciar sesión como administrador principal");
			System.out.println("5. Salir");


			int opcion_seleccionada;
			try 
			{
				opcion_seleccionada = Integer.parseInt(input("Seleccione el tipo de usuario (1/2/3/4): "));
				if (opcion_seleccionada == 1)
					ejecutarIniciarSesiónCliente(usuarios);
				else if (opcion_seleccionada == 2)
					ejecutarIniciarSesiónEmpleado(usuarios);
				else if (opcion_seleccionada == 3)
					ejecutarIniciarSesiónAdminSede(usuarios);
				else if (opcion_seleccionada == 4)
					ejecutarIniciarSesiónAdminPrincipal(usuarios);
				else if (opcion_seleccionada == 5)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;	
				}
				else{
					System.out.println("Seleccione una opción valida");
				}
				
			} 
			catch (NumberFormatException e) 
			{
				System.out.println("Seleccione una opcion valida");
				e.printStackTrace();
			}

		}
	}
	//fin main
	
	private static void ejecutarIniciarSesiónCliente(Usuarios usuarios)
	{
		String usuario = input("Porfavor ingrese su usuario:");
		String contraseña = input("Porfavor ingrese su contraseña:");
		
		boolean acceso = usuarios.verificarCliente(usuario, contraseña);
		if(acceso)
		{
			Cliente cliente = usuarios.retornarCliente(usuario);
			System.out.println("Bienvenido" + cliente.getNombre());
		}
		else {
			System.out.println("Contraseña incorrecta");
		}
		

	}
	
	private static void ejecutarIniciarSesiónEmpleado(Usuarios usuarios)
	{
		String usuario = input("Porfavor ingrese su usuario:");
		String contraseña = input("Porfavor ingrese su contraseña:");
		
		boolean acceso = usuarios.verificarEmpleado(usuario, contraseña);
		if(acceso)
		{
			Empleado empleado = usuarios.retornarEmpleado(usuario);
			System.out.println("Bienvenido" + empleado.getNombre());
		}
		else {
			System.out.println("Contraseña incorrecta");
		}
	}
	
	private static void ejecutarIniciarSesiónAdminSede(Usuarios usuarios)
	{
		String usuario = input("Porfavor ingrese su usuario:");
		String contraseña = input("Porfavor ingrese su contraseña:");
		
		boolean acceso = usuarios.verificarAdmin(usuario, contraseña);
		if(acceso)
		{
			Administrador_Sede admin = usuarios.retornarAdminSede(usuario);
			System.out.println("Bienvenido" + admin.getNombre());
		}
		else {
			System.out.println("Contraseña incorrecta");
		}
	}
	
	private static void ejecutarIniciarSesiónAdminPrincipal(Usuarios usuarios)
	{
		String usuario = input("Porfavor ingrese su usuario:");
		String contraseña = input("Porfavor ingrese su contraseña:");
		
		boolean acceso = usuarios.verificarAdminP(usuario, contraseña);
		if(acceso)
		{
			Administrador_Principal adminp = usuarios.retornarAdminPrincipal(usuario);
			System.out.println("Bienvenido" + adminp.getNombre());
		}
		else {
			System.out.println("Contraseña incorrecta");
		}
	}
	
	
	
	
	
	
	
	public static String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

				
}


