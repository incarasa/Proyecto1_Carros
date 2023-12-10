package Inventario;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FactoryVehiculos {
	
	private ArrayList<String> tipos = new ArrayList<>();
	
	public static FactoryVehiculos instancia = null;
	
	private FactoryVehiculos() {
		cargarTipos();
	}
	
	public static FactoryVehiculos getInstance() {
		if (instancia == null) {
			instancia = new FactoryVehiculos();
		}
		
		return instancia;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public VehiculoBase crearVehiculo(DTOInfoVehiculo datos) {
		
		VehiculoBase retorno = null;
		try {
			Class vehiculo = Class.forName("Inventario."+ datos.getTipo());
			Class dto = DTOInfoVehiculo.class;
			retorno = (VehiculoBase) vehiculo.getDeclaredConstructor(dto).newInstance(datos);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	public void cargarTipos() {
		try {
			BufferedReader lector = new BufferedReader(new FileReader("data/tipos/tipos.txt"));
			String linea = lector.readLine();
			
			while(linea != null) {
				tipos.add(linea);
				linea = lector.readLine();
			}
			lector.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getTipos(){
		return tipos;
	}
	
}
