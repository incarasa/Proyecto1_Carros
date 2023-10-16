package Usuarios;

public class Empleado extends Usuario
{
	//atributos}
	private String nombre;
	private String nombreSede;
	
	//contructor
	public Empleado(String usuario, String contraseña, String tipoUsuario , String nombre,
			String nombreSede)
	{
		super(usuario, contraseña, tipoUsuario);
		this.nombre = nombre;
		this.nombreSede = nombreSede;
	}
	
	
	// getters-setters
	
	public String getNombreSede() {
		return nombreSede;
	}

	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
	}

	public String getNombre() {
		return nombre;
	}
	
	
}
