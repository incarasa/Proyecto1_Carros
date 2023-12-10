package pasarelasPago;

import Usuarios.Cliente;
import pasarelasPago.exceptions.TarjetaBloqueadaException;
import pasarelasPago.exceptions.TarjetaSinCupoException;

public class PayPal extends PasarelaPago{

	@Override
	public boolean hacerPago(Cliente cliente, int monto)throws TarjetaBloqueadaException, TarjetaSinCupoException {
		
		if (cliente.isBloqueada()) {
			throw new TarjetaBloqueadaException();
		} else if (monto > cliente.getCupoTarjeta()) {
			throw new TarjetaSinCupoException();
		} else {
			return true;
		}
		
	}

}
