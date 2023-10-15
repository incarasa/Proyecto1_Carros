package Usuarios;

public class Cliente extends Usuario
{
	//atributos
	//Se extienden el user, password y tipousuario
	
	private String nombre;
	private String numeroDocumento;
	private String telefono;
	private String correo;
	private String fecha_nacimiento;
	
	//atributos de licencia
	private String numeroLicencia;
	private String paisExpedición;
	private String fechaVencimientoLicencia;
	//TO-DO imagen de la licencia
	
	//atributos de la tarjeta
	private String numeroTarjeta;
	private String codigoSeguridad;
	private String fechaVencimientoTarjeta;
	
	
	
	//contructor
	public Cliente(String usuario, String contraseña, String tipoUsuario)
	{
		super(usuario, contraseña, tipoUsuario);
	}
	
	//metodos
	
	
}