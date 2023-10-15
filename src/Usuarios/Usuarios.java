package Usuarios;

import java.util.HashMap;
import java.util.Map;

//La clase ususarios es una clase con un mapa encargada de gestionar los usuarios.
//El mapa tiene como llaves el usuario y como valor el usuario de acurdo con el mapa.
public class Usuarios 
{
	private Map<String, Cliente> mapaClientes;
	private Map<String, Administrador_Sede> mapaAdministradores_Sede;
	private Map<String, Empleado> mapaEmpleados;
	private Administrador_Principal admin_princ;
	
	
	//constructor
	
	public Usuarios()
	{
		mapaClientes = new HashMap<>();
		mapaAdministradores_Sede = new HashMap<>();
		mapaEmpleados = new HashMap<>();
	}
	
	//metodos
	
	
	

}
