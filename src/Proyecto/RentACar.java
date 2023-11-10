package Proyecto;

import java.io.ObjectInputStream.GetField;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.formdev.flatlaf.FlatLightLaf;

import Alquiler.Alquiler;
import Alquiler.GestorAlquileres;
import Alquiler.GestorReservas;
import Alquiler.Reserva;
import Instalaciones.Sedes;
import Interfaz.InterfazPrincipal;
import Inventario.Carro;
import Inventario.InventarioCarros;
import Tarifas.Categorias;
import Tarifas.Conductor;
import Usuarios.Usuarios;


public class RentACar 
{
	private InventarioCarros inventario;
	private Sedes sedes;
	private GestorReservas gestorReservas;
	private GestorAlquileres gestorAlquileres;
	private Categorias categorias;  //tiene a las categorias y sus precios
	private Usuarios usuarios;
	
	public RentACar()
	{
		this.inventario = new InventarioCarros();
		this.sedes = new Sedes();
		this.gestorReservas = new GestorReservas();
		this.gestorAlquileres = new GestorAlquileres();
		this.categorias = new Categorias();
		this.usuarios = new Usuarios();
		
	}
	
	public int autenticar(String usuario, String contraseña)
	{
		usuarios.verificar(usuario, contraseña);
		/*
		 * la funcion mira el parametro tipo de usuario que queda tras autenticar,
		 * para generar una respuesta.
		 */
		return usuarios.getTipoUsuario();
	}
	
	public void agregarCarro(String placa, String marca, int modelo, String transmision, String categoría, String sede)
	{
		inventario.agregarCarro(placa, marca, modelo, transmision, categoría, sede);
	}
	
	public void cargarInformacion()
	{
		inventario.cargarCarrosDesdeCarpeta();
		sedes.cargarSedesMapa();
		gestorReservas.cargarReservasDesdeCSV();
		gestorAlquileres.cargarAlquileresDesdeCarpeta();
		categorias.cargarCategoríasDesdeCSV();
		usuarios.cargarUsuarios();
	}
	
	//metodos para el CLIENTE
	/**
	 * Devuelve en una lista de strings las categorías que existan.
	 * @return
	 */
	public String[] darCategorias()
	{
		return categorias.darCategorias();
	}
	
	public String[] darSedes()
	{
		return sedes.darSedes();
	}
	
	public void reservarCarro(String placa, LocalDate diaInicio, LocalDate diaFin)
	{
		inventario.reservarCarro(placa, diaInicio, diaFin);
	}
	public InventarioCarros getInventario()
	{
		return inventario;
	}
	
	public Sedes getSedes()
	{
		return sedes;
	}
	
	public GestorReservas getGestorReservas()
	{
		return gestorReservas;
	}
	
	public GestorAlquileres getGestorAlquileres()
	{
		return gestorAlquileres;
	}
	
	public Categorias getCategorias()
	{
		return categorias;
	}
	
	public Usuarios getUsuarios()
	{
		return usuarios;
	}
	

}

