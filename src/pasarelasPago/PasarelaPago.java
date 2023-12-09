package pasarelasPago;

import Usuarios.Cliente;
import pasarelasPago.exceptions.TarjetaBloqueadaException;
import pasarelasPago.exceptions.TarjetaSinCupoException;

public abstract class PasarelaPago {

	public abstract boolean hacerPago(Cliente cliente, int monto) throws TarjetaBloqueadaException, TarjetaSinCupoException;
	
}
