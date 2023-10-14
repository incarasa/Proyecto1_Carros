package Proyecto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventarioCarros {
    private Map<String, Carro> inventario;

    public InventarioCarros() {
        inventario = new HashMap<>();
    }

    public void agregarCarro(String placa, String modelo) {
        Carro carro = new Carro(placa, modelo);
        inventario.put(placa, carro);
    }

    public Carro buscarCarroPorPlaca(String placa) {
        return inventario.get(placa);
    }
    
    public List<Carro> carrosDisponiblesEnSede(String sede) {
        List<Carro> carrosDisponibles = new ArrayList<>();

        for (Carro carro : inventario.values()) {
            if (!carro.isAlquilado() && carro.getSedeActual().equals(sede)) {
                carrosDisponibles.add(carro);
            }
        }

        return carrosDisponibles;

    // Otros métodos para listar carros, alquilar, devolver y cambiar sede, etc.
}
}