package model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.EnumMap;
import java.util.Map;



public class Sede
{
	private String nombre;
	private String direccion;
	private Map<DayOfWeek , HorarioAtencion> horarios;
	

	public Sede(String nombre, String direccion)
	{
		this.setNombre(nombre);
		this.setDireccion(direccion);
		this.horarios = new EnumMap<>(DayOfWeek.class);
	}
	

	public void agregarHorario(DayOfWeek dia, LocalTime horaApertura, LocalTime horaCierre)
	{
		
		HorarioAtencion horario = new HorarioAtencion(horaApertura, horaCierre);
		horarios.put(dia, horario);

	}
	
	public boolean estaAbierta(DayOfWeek dia, LocalTime horaCliente)
	{
		
		HorarioAtencion horario = horarios.get(dia);
		return horario != null && horario.estaEnHorario(horaCliente);
	}


	public String getNombre()
	{
		return nombre;
	}


	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}


	public String getDireccion()
	{
		return direccion;
	}


	public void setDireccion(String direccion)
	{
		this.direccion = direccion;
	}
}

