package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Usuarios.Cliente;
import Usuarios.Usuarios;
import pasarelasPago.GestorPasarelasPago;
import pasarelasPago.exceptions.TarjetaBloqueadaException;
import pasarelasPago.exceptions.TarjetaSinCupoException;

public class TestPasarelasPago {
	
	private static Usuarios gestorUsuarios = new Usuarios();
	private static GestorPasarelasPago gestorPasarelas = GestorPasarelasPago.getInstance();

	@BeforeAll
	public static void iniciarEscenario() {
		
		gestorUsuarios.cargarUsuarios();
	}
	
	@Test
	public void testCargaPasarelas() {
		assertTrue(gestorPasarelas.getPasarelas().size() == 3);
	}
	
	@Test
	public void testElegirPasarela1() {
		try {
			assertTrue(gestorPasarelas.SeleccionarPasarela("PayPal"));
		} catch (ClassNotFoundException e) {
			fail("La pasarela de pago no existe");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("Hubo un error desconocido");
		}
	}
	
	@Test
	public void testElegirPasarela2() {
		try {
			assertTrue(gestorPasarelas.SeleccionarPasarela("PayU"));
		} catch (ClassNotFoundException e) {
			fail("La pasarela de pago no existe");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("Hubo un error desconocido");
		}
	}
	
	@Test
	public void testElegirPasarela3() {
		try {
			assertTrue(gestorPasarelas.SeleccionarPasarela("Sire"));
		} catch (ClassNotFoundException e) {
			fail("La pasarela de pago no existe");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail ("Hubo un error desconocido");
		}
	}
	
	@Test
	public void testPago1() {
		Cliente test = gestorUsuarios.retornarClienteCedula("1000271188");
		test.setBloqueada(false);
		try {
			gestorPasarelas.SeleccionarPasarela("PayPal");
			assertTrue(gestorPasarelas.pagar(test, 150000));
		} catch (TarjetaBloqueadaException e) {
			fail("La tarjeta estaba bloqueada");
		} catch (TarjetaSinCupoException e) {
			fail("La tarjeta estaba sin cupo");
		} catch (ClassNotFoundException e) {
			fail("La pasarela de pago no existe");
		} catch (Exception e) {
			fail ("Hubo un error desconocido");
		}
	}
	
	@Test
	public void testPago2() {
		Cliente test = gestorUsuarios.retornarClienteCedula("1000271188");
		test.setBloqueada(false);
		try {
			gestorPasarelas.SeleccionarPasarela("PayU");
			assertTrue(gestorPasarelas.pagar(test, 2000000));
		} catch (TarjetaBloqueadaException e) {
			fail("La tarjeta estaba bloqueada");
		} catch (TarjetaSinCupoException e) {
			fail("La tarjeta estaba sin cupo");
		} catch (ClassNotFoundException e) {
			fail("La pasarela de pago no existe");
		} catch (Exception e) {
			fail ("Hubo un error desconocido");
		}
	}
	
	@Test 
	public void testBloqueada() {
		Cliente test = gestorUsuarios.retornarClienteCedula("1000271188");
		test.setBloqueada(true);
		
		assertThrows(TarjetaBloqueadaException.class, () ->
		{
			gestorPasarelas.SeleccionarPasarela("PayU");
			assertTrue(gestorPasarelas.pagar(test, 2000000));
		}) ;
	}
	
	@Test 
	public void testCupoInsuficiente() {
		Cliente test = gestorUsuarios.retornarClienteCedula("1000271188");
		test.setBloqueada(false);
		
		assertThrows(TarjetaSinCupoException.class, () ->
		{
			gestorPasarelas.SeleccionarPasarela("PayU");
			assertTrue(gestorPasarelas.pagar(test, 50000000));
		}) ;
	}
	
	@AfterEach
	public void reiniciarPasarela() {
		gestorPasarelas.reiniciarPasarela();
	}
}
