package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Inventario.InventarioCarros;
import Inventario.VehiculoBase;
import controller.AppManager;
import controller.ControladorReservas;
import manejoCSV.ReservasCSV;
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
		InventarioCarros inventarioCarros = new InventarioCarros();
		inventarioCarros.cargarCarrosDesdeCarpeta();
		appManager.setVehiculos(inventarioCarros.getInventario());
	}
	private void cargarUsuarios()
	{
		HashMap<String, Usuario> clientes = new HashMap<String, Usuario>();
		try (BufferedReader reader = new BufferedReader(
				new FileReader("data/usuarios/clientes.csv")))
		{
			String linea;
			while ((linea = reader.readLine()) != null)
			{
				Usuario cliente = persistenciaUsuario.fromCSV(linea);
				clientes.put(cliente.getUsuario(), cliente);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		appManager.setUsuarios(clientes);
	}
	private void cargarSedes()
	{
		HashMap<String, Sede> sedes = new HashMap<String, Sede>();
		File carpeta = new File("./data/sedes");
		File[] archivos = carpeta.listFiles();

		for (File archivoSede : archivos)
		{
			Sede sede = persistenciaSede.cargarDesdeArchivo(archivoSede);

			sedes.put(sede.getNombre(), sede);
		}
		appManager.setSedes(sedes);
	}
	private void cargarTarifasCategoria()
	{
		HashMap<String, Integer> tarifas = new HashMap<String, Integer>();
		try (BufferedReader reader = new BufferedReader(
				new FileReader("./data/tarifas/categorías.csv")))
		{
			String linea;
			while ((linea = reader.readLine()) != null)
			{
				String[] arrayLinea = linea.split(",");
				tarifas.put(arrayLinea[0], Integer.parseInt(arrayLinea[1]));

			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		ControladorReservas.setTarifasCategoria(tarifas);
	}

	public void guardarInformacion()
	{
		guardarReservas();

		guardarUsuarios();
	}
	public void guardarReservas()
	{
		HashMap<String, Reserva> mapaReservas = appManager.getReservas();
		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter("data/reservas/reservas.csv")))
		{
			for (Reserva reserva : mapaReservas.values())
			{

				writer.write(reservaToCSV(reserva));
				writer.newLine();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	public static String reservaToCSV(Entry<String, Reserva> entry) 
	{
	    return entry.getHoraLlegada() + "," + entry.getDiaInicio() + "," + entry.getDiaFin() + "," 
	    		+ entry.getDocumentoCliente() + "," + entry.getPrecio() + "," + entry.getCategoria() + ","+
	    		entry.getPlacaVehiculo();
	}
	public void guardarUsuarios()
	{
		HashMap<String, Usuario> mapaClientes = appManager.getUsuarios();
		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter("data/usuarios/clientes.csv")))
		{
			for (Entry<String, Usuario> entry : mapaClientes.entrySet())
			{
				Usuario cliente = entry.getValue();
				writer.write(clienteToCSV(cliente));
				writer.newLine();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public static String clienteToCSV(Usuario cliente)
	{
		return cliente.getUsuario() + "," + cliente.getContraseña() + ","
				+ cliente.getTipoUsuario() + "," + cliente.getNombre() + ","
				+ cliente.getNumeroDocumento() + "," + cliente.getTelefono()
				+ "," + cliente.getCorreo() + ","
				+ cliente.getFecha_nacimiento() + ","
				+ cliente.getNumeroLicencia() + ","
				+ cliente.getPaisExpedición() + ","
				+ cliente.getFechaVencimientoLicencia() + ","
				+ cliente.getNumeroTarjeta() + ","
				+ cliente.getCodigoSeguridad() + ","
				+ cliente.getFechaVencimientoTarjeta() + ","
				+ cliente.isBloqueada() + "," + cliente.getCupoTarjeta();
	}

}
