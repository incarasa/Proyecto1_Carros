package pasarelasPago;

import Usuarios.Cliente;

public abstract class PasarelaPago {

	public abstract void hacerPago(Cliente cliente, int monto);
	
}
