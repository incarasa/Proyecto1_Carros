package Inventario;

import java.lang.reflect.InvocationTargetException;

public class FactoryVehiculos {
	
	public static FactoryVehiculos instancia = null;
	
	private FactoryVehiculos() {
		
	}
	
	public static FactoryVehiculos getInstance() {
		if (instancia == null) {
			instancia = new FactoryVehiculos();
		}
		
		return instancia;
	}
	
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
	
}
