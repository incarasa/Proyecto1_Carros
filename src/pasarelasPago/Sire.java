package pasarelasPago;

import Usuarios.Cliente;
import pasarelasPago.exceptions.TarjetaBloqueadaException;
import pasarelasPago.exceptions.TarjetaSinCupoException;

public class Sire extends PasarelaPago{

	@Override
	public boolean hacerPago(Cliente cliente, int monto) throws TarjetaBloqueadaException, TarjetaSinCupoException {
		// TODO Auto-generated method stub
		return true;
	}

}
