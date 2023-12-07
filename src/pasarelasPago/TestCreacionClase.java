package pasarelasPago;

import pasarelasPago.exceptions.TarjetaBloqueadaException;
import pasarelasPago.exceptions.TarjetaSinCupoException;

public class TestCreacionClase {
	
	private GestorPasarelasPago gestor = GestorPasarelasPago.getInstance();
	
	public void pagar() {
		try {
			gestor.SeleccionarPasarela("PayPal");
			if(gestor.pagar(null, 0)) {
				System.out.println("Pago hecho");
			}
			
			
		} catch (TarjetaBloqueadaException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (TarjetaSinCupoException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println("No has elegido una pasarela");
		}
	}
	
	public static void main(String[] args) {
		TestCreacionClase main = new TestCreacionClase();
		main.pagar();
	}
}
