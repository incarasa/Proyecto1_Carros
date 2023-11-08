package manejoCSV;

import java.time.LocalDate;
import java.time.Month;

import Alquiler.Alquiler;
import Inventario.Carro;
import Inventario.Manejo_CSV;
import Tarifas.Conductor;

public class AlquilerCSV 
{
	public static String toCSV(Alquiler alquiler) 
	{
	    return alquiler.getPlacaAuto() + "," + alquiler.getCedulaCliente() + "," + 
	    		alquiler.getFechaRecogida() + "," + alquiler.getFechaEntrega() + "," + 
	    		alquiler.getSedeRecogida() + "," + alquiler.getSedeEntrega(); 
	}

	
	//Retorna un alquiler desde una linea de CSV (primera linea)
		public static Alquiler fromCSV(String lineaCSV) 
		{
		    String[] partes = lineaCSV.split(",");
		    if (partes.length != 6) {
		        throw new IllegalArgumentException("Formato CSV no válido");
		    }

		    String placaAuto = partes[0];
		    String cedulaCliente = partes[1];
		    LocalDate fechaRecogida = lineaCSVaDate(partes[2]);
		    LocalDate fechaEntrega = lineaCSVaDate(partes[3]);
		    String sedeRecogida = partes[4];
		    String sedeEntrega = partes[5];
		    
		    Alquiler alquiler = new Alquiler(placaAuto, cedulaCliente, fechaRecogida , fechaEntrega, sedeRecogida, sedeEntrega);
		    return alquiler;
		}
		
		public static LocalDate lineaCSVaDate(String linea)
		{
			String elementos[] = linea.split("-");
			int año = Integer.parseInt(elementos[0]);
			int mes = Integer.parseInt(elementos[1]);
			int dia = Integer.parseInt(elementos[2]);
			
			LocalDate fecha = LocalDate.of(año, mes, dia);
			return fecha;
			
			
		}
	
		
}
