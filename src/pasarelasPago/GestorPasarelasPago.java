package pasarelasPago;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Usuarios.Cliente;
import pasarelasPago.exceptions.TarjetaBloqueadaException;
import pasarelasPago.exceptions.TarjetaSinCupoException;

public class GestorPasarelasPago {
	
	private static GestorPasarelasPago instancia;
	
	private ArrayList<String> pasarelas = new ArrayList<>();
	
	private PasarelaPago pasarela = null;
	
	private GestorPasarelasPago() {
		cargarPasarelas();
	}
	
	public static GestorPasarelasPago getInstance() {
		if (instancia == null) {
			instancia = new GestorPasarelasPago();
		} 
		
		return instancia;
	}
	/**
	 * Carga las pasarelas de pago desde el txt que tiene sus nombres
	 */
	public void cargarPasarelas() {
		try {
			BufferedReader lector = new BufferedReader(new FileReader("data/pasarelasPago/pasarelasPago.txt"));
			String linea = lector.readLine();
			
			while(linea != null) {
				pasarelas.add(linea);
				linea = lector.readLine();
			}
			lector.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getPasarelas(){
		return pasarelas;
	}
	
	public boolean SeleccionarPasarela(String name) throws ClassNotFoundException, Exception{
		
		name = "pasarelasPago." + name;
		
			Class elegida = Class.forName(name);
			this.pasarela = (PasarelaPago) elegida.getDeclaredConstructor(null).newInstance(null);
		
		return true;
	}
	
	public boolean pagar(Cliente cliente, int monto) throws TarjetaBloqueadaException, TarjetaSinCupoException{
		return pasarela.hacerPago(cliente, monto);
	}
	
	public void reiniciarPasarela() {
		pasarela = null;
	}
	

}
