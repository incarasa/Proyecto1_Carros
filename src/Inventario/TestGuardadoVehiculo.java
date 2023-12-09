package Inventario;

import java.util.ArrayList;

public class TestGuardadoVehiculo {
	
	private InventarioCarros inventario = new InventarioCarros();
	
	public void test() {
		ArrayList<String> a = new ArrayList<>();
		a.add("a");
		inventario.agregarVehiculo("a", "a", 0, a, 'A', false, false, "a", false, false, "2023/NOVEMBER/17", "data/fotosCarros/HLT788.jpg", "Carro");
	}

	public static void main (String[] args) {
		TestGuardadoVehiculo test = new TestGuardadoVehiculo();
		test.test();
	}
}
