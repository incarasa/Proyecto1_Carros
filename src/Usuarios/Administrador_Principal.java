package Usuarios;

public class Administrador_Principal extends Usuario
{
	//atributos
	private String nombre;
	//constructor
	public Administrador_Principal(String usuario, String contraseña, String tipoUsuario , 
			String nombre)
	{
		super(usuario, contraseña, tipoUsuario);
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	
}
