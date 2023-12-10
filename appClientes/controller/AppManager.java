package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import model.*;
import persistencia.Persistencia;
import view.VentanaPrincipal;

public class AppManager
{
	private VentanaPrincipal ventanaPrincipal;
	private ControladorUsuario sistemaUsuarios;
	private ControladorReservas sistemaReservas;
	private Persistencia sistemaPersistencia;
	private HashMap<String, Vehiculo> inventario;
	private HashMap<String, Sede> sedes;
	private HashMap<String, Reserva> reservas;
	private HashMap<String, Usuario> usuarios;
	private Usuario usuario;
	
	public void iniciarAplicacion()
	{
		
	}
	public void registrarUsuario()
	{

	}
	public void comprobarUsuario()
	{

	}
	public void consultarDisponibilidad(String sede)
	{

	}
	public void crearReserva(ArrayList<String> datosReserva)
	{
		
		sistemaReservas.crearReserva(usuario, datosReserva);
	}
	public void cargarInformacion() throws FileNotFoundException, IOException
	{
		sistemaPersistencia = new Persistencia(this);
		sistemaPersistencia.cargarInformacion();
		
	}
	public HashMap<String, Vehiculo> getInventario()
	{
		return inventario;
	}
	public HashMap<String, Sede> getSedes()
	{
		return sedes;
	}
	public HashMap<String, Reserva> getReservas()
	{
		return reservas;
	}
	public HashMap<String, Usuario> getUsuarios()
	{
		return usuarios;
	}
	public Usuario getUsuario()
	{
		return usuario;
	}
	public void setInventario(HashMap<String, Vehiculo> inventario)
	{
		this.inventario = inventario;
	}
	public void setSedes(HashMap<String, Sede> sedes)
	{
		this.sedes = sedes;
	}
	public void setReservas(HashMap<String, Reserva> reservas)
	{
		this.reservas = reservas;
	}
	public void setUsuarios(HashMap<String, Usuario> usuarios)
	{
		this.usuarios = usuarios;
	}
	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}
	public void guardarInformacion()
	{

	}
}
