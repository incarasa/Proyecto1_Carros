package Tarifas;

public class Conductor 
{
	private static int precioConductorAdicional = 20000;
	
	private String nombre;
	private String numeroLicencia; //8
	private String paisExpedición; //9
	private String fechaVencimientoLicencia; //10
	
	public Conductor(String nombre, String numeroLicencia, String paisExpedición, String fechaVencimientoLicencia) {
		super();
		this.nombre = nombre;
		this.numeroLicencia = numeroLicencia;
		this.paisExpedición = paisExpedición;
		this.fechaVencimientoLicencia = fechaVencimientoLicencia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	public static int getPrecioCondAdicional()
	{
		return precioConductorAdicional;
	}
	
}
