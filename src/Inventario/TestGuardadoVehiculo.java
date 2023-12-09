package Inventario;

import java.util.ArrayList;

public class TestGuardadoVehiculo {
	
	private InventarioCarros inventario = new InventarioCarros();
	
	public void test() {
		ArrayList<String> a = new ArrayList<>();
		a.add("a");
		a.add("b");
		inventario.cargarCarrosDesdeCarpeta();
		System.out.println("Carga funciona sin problemas");
		inventario.agregarVehiculo("Placa123", "Kawasaki", 2023, a, 'A', false, false, "MODELIA", false, false, "", "data/fotosCarros/HLT788.jpg", "Moto");
		System.out.println("Moto creada");
	}

	public static void main (String[] args) {
		TestGuardadoVehiculo test = new TestGuardadoVehiculo();
		test.test();
	}
}
