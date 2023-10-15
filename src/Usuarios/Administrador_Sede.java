package Usuarios;

import Instalaciones.Sede;

public class Administrador_Sede extends Usuario
{
	//atributos
	private String nombre;
	private String nombreSede; //nombre sede para buscar en el map de Sedes
	
	
	//constructor
	public Administrador_Sede(String usuario, String contraseña, String tipoUsuario,
			String nombre , String nombreSede)
	{
		super(usuario, contraseña, tipoUsuario);
		this.nombre = nombre;
		this.nombreSede = nombreSede;
	}

}
