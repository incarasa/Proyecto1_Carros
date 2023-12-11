package Inventario;

import java.util.ArrayList;

public class TestGuardadoVehiculo {
	
	private InventarioCarros inventario = new InventarioCarros();
	
	public void test() {
		
		try {
			ArrayList<String> datos = inventario.getCamposSegunTipo("Moto");
			for (String info: datos) {
				System.out.println(info);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main (String[] args) {
		TestGuardadoVehiculo test = new TestGuardadoVehiculo();
		test.test();
	}
}
