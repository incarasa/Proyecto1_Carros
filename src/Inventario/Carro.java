package Inventario;

public class Carro 

{
    private String placa;
    private String marca;
    private int modelo;
    private String transmision;
    private String categoría;
    private boolean alquilado;
    private boolean disponible;
    private String sede;

    
    //contructor

    public Carro(String placa, String marca, int modelo, String transmision, String categoría,
    		String sede) {
		super();
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.transmision = transmision;
		this.categoría = categoría;
		this.alquilado = false;
		this.disponible = true;
		this.sede = sede;
	}

	// Métodos para cambiar el estado del carro
    public void alquilarCarro(String cliente) {
        if (!alquilado) 
        {
            alquilado = true;
            sede = "Cliente: " + cliente;
        }
    }

    public void devolverCarro() {
        if (alquilado) {
            alquilado = false;
        }
    }

    public void cambiarSede(String nuevaSede) {
        sede = nuevaSede;
    }
    
    //GETTERS Y SETTERS
	public String getCategoría() {
		return categoría;
	}

	public void setCategoría(String categoría) {
		this.categoría = categoría;
	}

	public boolean isAlquilado() {
		return alquilado;
	}

	public void setAlquilado(boolean alquilado) {
		this.alquilado = alquilado;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getPlaca() {
		return placa;
	}

	public String getMarca() {
		return marca;
	}

	public int getModelo() {
		return modelo;
	}

	public String getTransmision() {
		return transmision;
	}

    



    
    
    
    
    

}