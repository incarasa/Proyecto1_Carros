package pasarelasPago.exceptions;

@SuppressWarnings("serial")
public class TarjetaSinCupoException extends Exception{
	
	@Override 
	public String getMessage() {
		return "La tarjeta no tiene suficiente cupo";
	}
}
