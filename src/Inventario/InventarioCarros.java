package Inventario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Instalaciones.Sede;

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
    
    /**
     * Crea un carro nuevo y crea un archivo con la información del carro. Los días reservados
     * están vacío por ahora
     * @param placa
     * @param marca
     * @param modelo
     * @param transmision
     * @param categoría
     * @param sede
     */
    public void agregarCarro(String placa, String marca, int modelo, String transmision, String categoría, String sede) 
    {
    	//agrega un carro
        Carro carro = new Carro(placa,  marca,  modelo,  transmision,  categoría, sede);
        inventario.put(placa, carro);
        
        //persistencia
        String ruta_carro = "data/carros/" + placa + ".txt";
    	carro.guardarEnArchivo(ruta_carro); //guarda el carro en archivo
        
    }
    
    public void eliminarCarro(String placa) 
    {
    	//elimina un carro
        inventario.remove(placa);
        //ELIMINAR ESA FUNCION
        // Manejo_CSV.actualizarCSV(inventario, rutaCSV);
        
        
        
    }

    public Carro buscarCarroPorPlaca(String placa) {
        return inventario.get(placa);
    }
    
    
    //REVISAR
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
    
    
    /**
     * Este metodo carga los datos de los vehiculos.
     */
    public void cargarCarrosDesdeCarpeta() 
    {;
    	File carpetaCarros = new File("./data/carros");
	
    	//IMPORTANTE
    	File[] archivosCarros = carpetaCarros.listFiles();
    	for(File carroArchivo: archivosCarros)
    	{
    		//aca tengo un archivo de carro
    		try (BufferedReader br = new BufferedReader(new FileReader(carroArchivo))) 
    		{
    			String primeraLinea = br.readLine();
    			Carro carro = Manejo_CSV.fromCSV(primeraLinea); //retorna un carro
    			
    			String linea;
    			linea = br.readLine();
    			while (linea != null) 
    			{
    				//aca debe añadirse al mapa de reservas un día particular
    				carro.reservar(Manejo_CSV.lineaCSVaDate(linea), Manejo_CSV.lineaCSVaDate(linea));
    				linea = br.readLine(); //incrementar la linea
    			}
    			inventario.put(carro.getPlaca(), carro); //añado el carro al mapa
    		}
		
    		catch (IOException e) 
    		{
	            e.printStackTrace();
    		}
    	}
	}

    /**Este metodo busca el carro que se quiere reservar en el mapa y reserva los días.
    la persistencia la maneja el metodo reservar de carro
    */
    
    public void reservarCarro(String placa, LocalDate diaInicio, LocalDate diaFin)
    {
    	Carro carro = buscarCarroPorPlaca(placa);
    	carro.reservar(diaInicio, diaFin);
    }

	public Map<String, Carro> getInventario() {
		return inventario;
	}
    
    
}


