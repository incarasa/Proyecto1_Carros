package pasarelasPago.exceptions;

@SuppressWarnings("serial")
public class TarjetaBloqueadaException extends Exception{

	
	@Override
	public String getMessage() {
		return "La tarjeta se encuentra bloqueada";
	}
}
