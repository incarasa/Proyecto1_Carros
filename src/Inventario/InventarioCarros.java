package Inventario;

import java.awt.Robot;
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

import javax.print.attribute.standard.Media;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import Instalaciones.Sede;
import Tarifas.Categorias;

public class InventarioCarros {
	
	//atributos
    private Map<String, Carro> inventario;
    private String rutaCSV;
    private char[] categorias = {'N', 'M', 'L', 'K', 'J', 'I', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A'};

    
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
    public void agregarCarro(String placa, String marca, int modelo, String transmision, char categoría, String sede) 
    {
    	//agrega un carro
        Carro carro = new Carro(placa,  marca,  modelo,  transmision,  categoría, sede);
        inventario.put(placa, carro);
        
        //persistencia
        String ruta_carro = "data/carros/" + placa + ".txt";
    	carro.guardarEnArchivo(ruta_carro); //guarda el carro en archivo
    	System.out.println("Carro creado");
        
    	
    }
    
    public void eliminarCarro(String placa) 
    {
    	//elimina un carro
        inventario.remove(placa);
        
        //elimina el archivo del carro
        String ruta_carro = "data/carros/" + placa + ".txt";
        File archivoCarro = new File(ruta_carro);
        archivoCarro.delete();
        System.out.println("Carro eliminado");
            
    }

    public Carro buscarCarroPorPlaca(String placa) {
        return inventario.get(placa);
    }
    
    
    public List<Carro> carrosDisponibles(String sede, LocalDate fechaInicio, LocalDate fechaFin,
    		char categoria)
    {
    	//filtrar por sede
    	List<Carro> listaDisponibles = carrosDisponiblesEnSede(sede);
    	
    	//filtrar por dias disponibles
    	listaDisponibles = DisponiblesEnFechas(listaDisponibles, fechaInicio,
    			fechaFin);
    	
    	listaDisponibles = DisponiblesCategoriaOSuperior(listaDisponibles, categoria);

    		
    	
    	return listaDisponibles;
    }
    
    /**
     * Esta funcion retorna los carros que no estan alquilados ni en mantenimiento ni lavando
     * en una sede.
     * @param sede
     * @return
     */
    public List<Carro> carrosDisponiblesEnSede(String sede) {
        List<Carro> carrosDisponibles = new ArrayList<>();

        for (Carro carro : inventario.values()) {
        	//Que el carro no esté alquilado y que esté disponible (mantenimiento o aseo).
        	
            if ((!carro.isAlquilado()) && (carro.isDisponible()) &&
            		((carro.getSede()).equals(sede)) ) 
            {
                carrosDisponibles.add(carro);
            }
        }

        return carrosDisponibles;

    // Otros métodos para listar carros, alquilar, devolver y cambiar sede, etc.
}
    /**
     * Este metodo recibe una lista de carros y escoge los que puedan estar disponibles
     * para reservar en las fechas seleccionadas.
     * @param carros
     * @param fechaInicio
     * @param fechaFin
     * @return lista carros disponibles
     */
    public List<Carro> DisponiblesEnFechas(List<Carro> carros , LocalDate fechaInicio, LocalDate fechaFin)
    {

    	List<Carro> carrosDisponibles = new ArrayList<Carro>();
    	
    	//se recorre para mirar que carros estan disponibles.
    	for(Carro carro : carros)
		{
    		if(carro.esReservable(fechaInicio, fechaFin))
    		{

    			carrosDisponibles.add(carro);
    		}		
		}
    	return carrosDisponibles;
	}
    
    public List<Carro> DisponiblesCategoriaOSuperior(List<Carro> carros , char categoria)
    {
    	List<Carro> carrosCategoriaoSup = new ArrayList<Carro>();
    	
    	while((carrosCategoriaoSup.size() == 0) && (categoria >= "A".charAt(0)))
    	{
    		for(Carro carro : carros)
        	{
        		char categoriaSTR = carro.getCategoría();
        		if(categoria == categoriaSTR)
        		{
        			carrosCategoriaoSup.add(carro);
        		}
        	}
    	categoria--;
    	}
    	
    	return carrosCategoriaoSup;
    }
    
    /**
     * Este metodo carga los datos de los vehiculos.
     */
    public void cargarCarrosDesdeCarpeta() 
    {
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
    			while (linea != null && (linea != "")) 
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
    
    public void alquilarCarro(String placa, LocalDate diaInicio, LocalDate diaFin)
    {
    	Carro carro = buscarCarroPorPlaca(placa);
    	carro.alquilarCarro(placa, diaInicio, diaFin);
    }

	public Map<String, Carro> getInventario() {
		return inventario;
	}
    
    
}


