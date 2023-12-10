package Inventario;

import java.util.ArrayList;

public class TestGuardadoVehiculo {
	
	private InventarioCarros inventario = new InventarioCarros();
	
	public void test() {
		
		FactoryVehiculos factory = FactoryVehiculos.getInstance();
		ArrayList<String> tipos = factory.getTipos();
	}

	public static void main (String[] args) {
		TestGuardadoVehiculo test = new TestGuardadoVehiculo();
		test.test();
	}
}
