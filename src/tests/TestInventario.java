package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Inventario.FactoryVehiculos;
import Inventario.InventarioCarros;
import Inventario.VehiculoBase;
import Inventario.DTOInfoVehiculo;

public class TestInventario {
	
	private static InventarioCarros inventario = new InventarioCarros();
	private FactoryVehiculos factory = FactoryVehiculos.getInstance();
	
	@BeforeAll
	public static void prepararEscenario() {
		inventario.cargarCarrosDesdeCarpeta();
	}

	@Test
	/**
	 * Verifica que se cree un carro de forma adecuada 
	 */
	public void testCreacionCarro() {
		
		ArrayList<String> carac = new ArrayList<>();
		
		carac.add("funciona la creacion dinamica");
		
		inventario.agregarVehiculo("testCreacionCarro", "", 2023, carac, 'A', false, false, "MODELIA", false, false, "", "", "Carro");
		
		File carroGuardado = new File("data/carros/testCreacionCarro.txt");
		
		assertTrue(carroGuardado.exists());
	}
	
	@Test
	/**
	 * Verifica que se cree una moto de forma adecuada
	 */
	public void testCreacionMoto() {
		ArrayList<String> carac = new ArrayList<>();
		
		carac.add("funciona");
		carac.add("la creacion dinamica");
		
		inventario.agregarVehiculo("testCreacionMoto", "", 2023, carac, 'A', false, false, "MODELIA", false, false, "", "", "Moto");
		
		File motoGuardada = new File("data/carros/testCreacionMoto.txt");
		
		assertTrue(motoGuardada.exists());
		
	}
	
	@Test
	/**
	 * Verifica que no se arroje la excepcion adecuada al crear instancias de subclases inexistentes de VehiculoBase en factory
	 */
	public void testCreacionTipoInexistente() {
		assertThrows(ClassNotFoundException.class, ()->{
			DTOInfoVehiculo datos = new DTOInfoVehiculo();
			
			datos.setTipo("NaveEspacial");
			VehiculoBase vehiculo = factory.crearVehiculo(datos);
		});
	}
	
	@AfterAll
	//Borra todos los vehiculos creados en las pruebas
	public static void borrarVehiculosAgregados() {
		inventario.eliminarCarro("testCreacionCarro");
		inventario.eliminarCarro("testCreacionMoto");
	}
}
