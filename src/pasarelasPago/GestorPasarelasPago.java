package pasarelasPago;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GestorPasarelasPago {
	
	private static GestorPasarelasPago instancia;
	
	private ArrayList<String> pasarelas = new ArrayList<>();
	
	private GestorPasarelasPago() {
		cargarPasarelas();
	}
	
	public GestorPasarelasPago getInstance() {
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
	

}
