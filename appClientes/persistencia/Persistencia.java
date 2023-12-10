package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import controller.AppManager;
import model.*;

public class Persistencia

{
	private AppManager appManager;
	private PersistenciaReserva persistenciaReserva = new PersistenciaReserva();
	private PersistenciaSede persistenciaSede = new PersistenciaSede();
	private PersistenciaUsuario persistenciaUsuario = new PersistenciaUsuario();
	private PersistenciaVehiculo persistenciaVehiculo = new PersistenciaVehiculo();

	public Persistencia(AppManager appManager)
	{
		this.appManager = appManager;
	}

	public void cargarInformacion() throws FileNotFoundException, IOException
	{
		cargarReservas();
		cargarVehiculos();
		cargarUsuarios();
		cargarTarifasCategoria();
		cargarTarifasSeguros();
		cargarSedes();
	}
	private void cargarReservas() throws FileNotFoundException, IOException
	{
		HashMap<String, Reserva> reservas = new HashMap<String, Reserva>();
		try (BufferedReader reader = new BufferedReader(
				new FileReader("data/reservas/reservas.csv")))
		{
			String linea;
			while ((linea = reader.readLine()) != null)
			{
				Reserva reserva = persistenciaReserva.fromCSV(linea);
				String documentoCliente = reserva.getDocumentoCliente();
				reservas.put(documentoCliente, reserva);
			}
			appManager.setReservas(reservas);
		}

	}
	private void cargarVehiculos()
	{

	}
	private void cargarUsuarios()
	{
		HashMap<String,Usuario> clientes = new HashMap<String,Usuario>();
		try (BufferedReader reader = new BufferedReader(new FileReader("data/usuarios/clientes.csv"))) 
        {
            String linea;
            while ((linea = reader.readLine()) != null) 
            {
                Usuario cliente = persistenciaUsuario.fromCSV(linea);
                clientes.put(cliente.getUsuario(), cliente);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	private void cargarSedes()
	{
		HashMap<String, Sede> sedes = new HashMap<String,Sede>();
		File carpeta = new File("./data/sedes");
		File[] archivos = carpeta.listFiles();
	
		for (File archivoSede : archivos)
		{
			Sede sede = persistenciaSede.cargarDesdeArchivo(archivoSede);
			
			sedes.put(sede.getNombre(), sede);
		}
    }
	private void cargarTarifasCategoria()
	{

	}
	private void cargarTarifasSeguros()
	{

	}
	public void guardarInformacion()
	{

	}
	public void guardarReservas()
	{

	}
	public void guardarVehiculos()
	{

	}
	public void guardarUsuarios()
	{

	}

}
