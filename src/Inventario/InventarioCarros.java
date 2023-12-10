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
	
	private FactoryVehiculos factory = FactoryVehiculos.getInstance();
    private Map<String, VehiculoBase> inventario;
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
    public void agregarVehiculo(String placa, String marca, int modelo, ArrayList<String> carac, 
    		char categoría, boolean alquilado, boolean disponible, String sede,
    		boolean lavandose, boolean enMantenimiento, String fechaDisponibleNuevamente,
    		String rutaImagen, String tipo) 
    {
    	//agrega un carro
    	DTOInfoVehiculo datos = new DTOInfoVehiculo();
    	
    	datos.setTipo(tipo);
    	datos.setPlaca(placa);
    	datos.setMarca(marca);
    	datos.setModelo(modelo);
    	datos.setCaracteristicas(carac);
    	datos.setCategoría(categoría);
    	datos.setAlquilado(alquilado);
    	datos.setDisponible(disponible);
    	datos.setSede(sede);
    	datos.setLavandose(lavandose);
    	datos.setEnMantenimiento(enMantenimiento);
    	datos.setFechaDisponibleNuevamente(fechaDisponibleNuevamente);
    	datos.setRutaImagen(rutaImagen);
    	
        VehiculoBase vehiculo = factory.crearVehiculo(datos);
        
        //persistencia
        String ruta_carro = "data/carros/" + placa + ".txt";
    	vehiculo.guardarEnArchivo(ruta_carro); //guarda el carro en archivo
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
        
        String ruta_carro_imagen = "data/fotosCarros/" + placa + ".jpg";
        File carro_imagen = new File(ruta_carro_imagen);
        carro_imagen.delete();
        
        System.out.println("Carro eliminado");
            
    }

    public VehiculoBase buscarCarroPorPlaca(String placa) {
        return inventario.get(placa);
    }
    
    public List<VehiculoBase> filtroPorTipos(List<VehiculoBase> datos, String tipo){
    	List<VehiculoBase> retorno = new ArrayList<>();
    	
    	for (VehiculoBase data: datos) {
    		if (data.getTipo().equals(tipo)) {
    			retorno.add(data);
    		}
    	}
    	
    	return retorno;
    	
    }
    
    
    public List<VehiculoBase> carrosDisponibles(String sede, LocalDate fechaInicio, LocalDate fechaFin,
    		char categoria, String tipoDeVehiculo)
    {
    	//filtrar por sede
    	List<VehiculoBase> listaDisponibles = carrosDisponiblesEnSede(sede);
    	
    	//filtrar por dias disponibles
    	listaDisponibles = DisponiblesEnFechas(listaDisponibles, fechaInicio,
    			fechaFin);
    	
    	//filtrar por tipo de vehiculo
    	
    	listaDisponibles = filtroPorTipos(listaDisponibles, tipoDeVehiculo);
    	
    	//______
    	listaDisponibles = DisponiblesCategoriaOSuperior(listaDisponibles, categoria);
    	
    	return listaDisponibles;
    }
    
    /**
     * Esta funcion retorna los carros que no estan alquilados ni en mantenimiento ni lavando
     * en una sede.
     * @param sede
     * @return
     */
    public List<VehiculoBase> carrosDisponiblesEnSede(String sede) {
        List<VehiculoBase> carrosDisponibles = new ArrayList<>();

        for (VehiculoBase carro : inventario.values()) {
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
    public List<VehiculoBase> DisponiblesEnFechas(List<VehiculoBase> carros , LocalDate fechaInicio, LocalDate fechaFin)
    {

    	List<VehiculoBase> carrosDisponibles = new ArrayList<VehiculoBase>();
    	
    	//se recorre para mirar que carros estan disponibles.
    	for(VehiculoBase carro : carros)
		{
    		if(carro.esReservable(fechaInicio, fechaFin))
    		{

    			carrosDisponibles.add(carro);
    		}		
		}
    	return carrosDisponibles;
	}
    
    public List<VehiculoBase> DisponiblesCategoriaOSuperior(List<VehiculoBase> carros , char categoria)
    {
    	List<VehiculoBase> carrosCategoriaoSup = new ArrayList<VehiculoBase>();
    	
    	while((carrosCategoriaoSup.size() == 0) && (categoria >= "A".charAt(0)))
    	{
    		for(VehiculoBase carro : carros)
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
    			VehiculoBase carro = Manejo_CSV.fromCSV(primeraLinea); //retorna un carro
    			
    			String linea;
    			linea = br.readLine();
    			while (linea != null && (!linea.equals(""))) 
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
    	VehiculoBase carro = buscarCarroPorPlaca(placa);
    	carro.reservar(diaInicio, diaFin);
    }
    
    public void alquilarCarro(String placa, String cedulaCliente, LocalDate diaInicio, 
    		LocalDate diaFin)
    {
    	VehiculoBase carro = buscarCarroPorPlaca(placa);
    	carro.alquilarCarro(cedulaCliente, diaInicio, diaFin);
    }

	public Map<String, VehiculoBase> getInventario() 
	{
		return inventario;
	}
    
	public void devolverCarro(String placa, boolean lavar, boolean 
			mantenimiento, String fechaDisponibleNuevamente)
	{
		VehiculoBase carro = inventario.get(placa);
		carro.devolverCarro();
		if(lavar)
		{
			carro.lavarCarro();
		}
		if(mantenimiento)
		{
			carro.manteniemiento();
		}
		
		if(lavar || mantenimiento)
		{
			carro.fechaDisponible(fechaDisponibleNuevamente);
		}
	}
	
	public ArrayList<String> getTipos(){
		return factory.getTipos();
	}
    
}


