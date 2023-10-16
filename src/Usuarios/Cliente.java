package Usuarios;

public class Cliente extends Usuario
{
	//atributos
	//Se extienden el user, password y tipousuario
	
	//heredadas usuario
	//heredadas contraseña
	//heredadas tipo de usuario
	
	private String nombre; //3
	private String numeroDocumento; //4
	private String telefono; //5
	private String correo; //6
	private String fecha_nacimiento; //7
	
	//atributos de licencia
	private String numeroLicencia; //8
	private String paisExpedición; //9
	private String fechaVencimientoLicencia; //10
	//TO-DO imagen de la licencia
	
	//atributos de la tarjeta
	private String numeroTarjeta; //11
	private String codigoSeguridad; //12
	private String fechaVencimientoTarjeta; //13
	
	
	
	//contructor

	public Cliente(String usuario, String contraseña, String tipoUsuario, String nombre, String numeroDocumento,
			String telefono, String correo, String fecha_nacimiento, String numeroLicencia, String paisExpedición,
			String fechaVencimientoLicencia, String numeroTarjeta, String codigoSeguridad,
			String fechaVencimientoTarjeta) {
		super(usuario, contraseña, tipoUsuario);
		this.nombre = nombre;
		this.numeroDocumento = numeroDocumento;
		this.telefono = telefono;
		this.correo = correo;
		this.fecha_nacimiento = fecha_nacimiento;
		this.numeroLicencia = numeroLicencia;
		this.paisExpedición = paisExpedición;
		this.fechaVencimientoLicencia = fechaVencimientoLicencia;
		this.numeroTarjeta = numeroTarjeta;
		this.codigoSeguridad = codigoSeguridad;
		this.fechaVencimientoTarjeta = fechaVencimientoTarjeta;
	}


	//GETTERS Y SETTERS
	
	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getNumeroDocumento() {
		return numeroDocumento;
	}



	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}



	public String getTelefono() {
		return telefono;
	}



	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	public String getCorreo() {
		return correo;
	}



	public void setCorreo(String correo) {
		this.correo = correo;
	}



	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}



	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}



	public String getNumeroLicencia() {
		return numeroLicencia;
	}



	public void setNumeroLicencia(String numeroLicencia) {
		this.numeroLicencia = numeroLicencia;
	}



	public String getPaisExpedición() {
		return paisExpedición;
	}



	public void setPaisExpedición(String paisExpedición) {
		this.paisExpedición = paisExpedición;
	}



	public String getFechaVencimientoLicencia() {
		return fechaVencimientoLicencia;
	}



	public void setFechaVencimientoLicencia(String fechaVencimientoLicencia) {
		this.fechaVencimientoLicencia = fechaVencimientoLicencia;
	}



	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}



	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}



	public String getCodigoSeguridad() {
		return codigoSeguridad;
	}



	public void setCodigoSeguridad(String codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}



	public String getFechaVencimientoTarjeta() {
		return fechaVencimientoTarjeta;
	}



	public void setFechaVencimientoTarjeta(String fechaVencimientoTarjeta) {
		this.fechaVencimientoTarjeta = fechaVencimientoTarjeta;
	}
	
	//metodos
	
	
}