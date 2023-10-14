package Proyecto;

public class Carro 

{
    private String placa;
    private String modelo;
    private boolean alquilado;
    private String sedeActual;

    
    //contructor
    public Carro(String placa, String modelo) 
    {
        this.placa = placa;
        this.modelo = modelo;
        this.alquilado = false;
        this.sedeActual = "Sede principal"; // Puedes establecer una sede predeterminada
    }

    // Métodos para cambiar el estado del carro
    public void alquilarCarro(String cliente) {
        if (!alquilado) {
            alquilado = true;
            sedeActual = "Cliente: " + cliente;
        }
    }

    public void devolverCarro() {
        if (alquilado) {
            alquilado = false;
            sedeActual = "Sede principal";
        }
    }

    public void cambiarSede(String nuevaSede) {
        sedeActual = nuevaSede;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public boolean isAlquilado() {
        return alquilado;
    }

    public String getSedeActual() {
        return sedeActual;
    }
}