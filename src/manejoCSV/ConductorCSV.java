package manejoCSV;

import Tarifas.Conductor;

public class ConductorCSV 
{
	public static String toCSV(Conductor conductor) 
	{
	    return conductor.getNombre() + "," + conductor.getNumeroLicencia() + "," + 
	    		conductor.getPaisExpedición() + "," + conductor.getFechaVencimientoLicencia();
	}
	
	//Retorna un Conductor desde una linea CSV
			public static Conductor conductorFromCSV(String lineaCSV)
			{
				String[] partes = lineaCSV.split(",");
				
				String nombre = partes[0];
			    String numeroLicencia = partes[1];
			    String paisExpedición = partes[2];
			    String fechaVencimientoLicencia = partes[3];
			    
			    Conductor conductor = new Conductor(nombre, numeroLicencia, paisExpedición, fechaVencimientoLicencia);
			    
			    return conductor;
			}
}
