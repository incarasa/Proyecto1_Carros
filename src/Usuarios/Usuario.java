package Usuarios;

public abstract class Usuario 
{
	//atributos
	
	private String usuario;
	private String contraseña;
	private String tipoUsuario;
	
	//constructor
	
	public Usuario(String usuario, String contraseña, String tipoUsuario)
	{
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.tipoUsuario = tipoUsuario;
		
	}

	//metodos
	
	public String getUsuario() {
		return usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}
	
}
