package Inventario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventarioCarros {
	
	//atributos
    private Map<String, Carro> inventario;
    private String rutaCSV;

    
    //constructor
    public InventarioCarros() {
        inventario = new HashMap<>();
        rutaCSV = "data/carros/carros.csv";
    }

    
    //METODOS
    public void agregarCarro(String placa, String marca, int modelo, String transmision, String categoría, String sede) 
    {
    	//agrega un carro
        Carro carro = new Carro(placa,  marca,  modelo,  transmision,  categoría, sede);
        inventario.put(placa, carro);
        Manejo_CSV.actualizarCSV(inventario, rutaCSV);
        
        
    }

    public Carro buscarCarroPorPlaca(String placa) {
        return inventario.get(placa);
    }
    
    public List<Carro> carrosDisponiblesEnSede(String sede) {
        List<Carro> carrosDisponibles = new ArrayList<>();

        for (Carro carro : inventario.values()) {
            if (!carro.isAlquilado() && carro.getSede().equals(sede)) {
                carrosDisponibles.add(carro);
            }
        }

        return carrosDisponibles;

    // Otros métodos para listar carros, alquilar, devolver y cambiar sede, etc.
}
    
    
    //cargar datos
    public void cargarCarrosDesdeCSV() 
    {;
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaCSV))) 
        {
            String linea;
            while ((linea = reader.readLine()) != null) 
            {
                Carro carro = Manejo_CSV.fromCSV(linea);
                inventario.put(carro.getPlaca(), carro);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }


	public Map<String, Carro> getInventario() {
		return inventario;
	}
    
    
}