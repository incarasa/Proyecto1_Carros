package pasarelasPago;

import Usuarios.Cliente;
import pasarelasPago.exceptions.TarjetaBloqueadaException;
import pasarelasPago.exceptions.TarjetaSinCupoException;

public class PayU extends PasarelaPago{
	
	public boolean hacerPago(Cliente cliente, int monto) throws TarjetaBloqueadaException, TarjetaSinCupoException {
		return true;
	}

}
