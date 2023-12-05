package pasarelasPago;

import java.lang.reflect.InvocationTargetException;

public class testCreacionClase {
	
	
	public static void main(String[] args) {
		try {
			Class pago = Class.forName("pasarelasPago.PayU");
			PasarelaPago pasarela = (PasarelaPago) pago.getDeclaredConstructor(null).newInstance(null);
			
			pasarela.hacerPago(null, 2);
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			System.out.println("No existe la clase");
		}
	}
}
