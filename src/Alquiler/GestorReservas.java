package Alquiler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.PublicKey;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Usuarios.Cliente;
import manejoCSV.ClientesCSV;
import manejoCSV.ReservasCSV;

public class GestorReservas 
{
	private Map<String, List<Reserva>> mapaReservas;  //mapa con llave (documento), valor reservas del cliente
	private String rutaReservasCSV = "data/reservas/reservas.csv";
	
	public GestorReservas()
	{
		mapaReservas = new HashMap<String, List<Reserva>>();
	}
	
	public void crearReserva (LocalTime horaLlegada, LocalDate diaInicio, LocalDate diaFin, String documentoCliente,
			int precio, String categoria, String placaVehiculo)
	{
		Reserva reserva = new Reserva(horaLlegada, diaInicio, diaFin, documentoCliente, precio, 
				categoria, placaVehiculo);
		
		/*
		 * Lo que se hace es mirar si en el mapa hay un cliente con reservas. Si no hay se crea una lista
		 * se mete la reserva y se añade a su llave. Si hay se saca la lista y se mete la reserva.
		 */
		if(mapaReservas.get(documentoCliente) == null)
		{
			List<Reserva> listaReservas = new ArrayList<Reserva>();
			listaReservas.add(reserva);
			mapaReservas.put(documentoCliente, listaReservas);
		}
		else 
		{
			List<Reserva> listaReservas = mapaReservas.get(documentoCliente);
			listaReservas.add(reserva);
		}
		//Se actualiza el CSV
		ReservasCSV.actualizarCSV(mapaReservas, rutaReservasCSV);
	}
	
	public void cargarReservasDesdeCSV() 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaReservasCSV))) 
        {
            String linea;
            while ((linea = reader.readLine()) != null) 
            {
                Reserva reserva = ReservasCSV.fromCSV(linea);
                String documentoCliente = reserva.getDocumentoCliente();
                
                if(mapaReservas.get(documentoCliente) == null)
        		{
        			List<Reserva> listaReservas = new ArrayList<Reserva>();
        			listaReservas.add(reserva);
        			mapaReservas.put(documentoCliente, listaReservas);
        		}
        		else 
        		{
        			List<Reserva> listaReservas = mapaReservas.get(documentoCliente);
        			listaReservas.add(reserva);
        		}
                
            }
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
	
	public Map<String, List<Reserva>> getMap()
	{
		return mapaReservas;
	}
	
}
