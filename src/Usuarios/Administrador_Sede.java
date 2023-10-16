package Usuarios;


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

	
	//getters-setters
	
	public String getNombre() {
		return nombre;
	}


	public String getNombreSede() {
		return nombreSede;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
	}
	
}
