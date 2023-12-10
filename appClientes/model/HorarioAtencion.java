package model;

import java.time.LocalTime;

public class HorarioAtencion 
{
	private LocalTime horaApertura;
	private LocalTime horaCierre;
	
	public HorarioAtencion(LocalTime horaApertura, LocalTime horaCierre)
	{
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
	}
	
	public boolean estaEnHorario(LocalTime horaCliente)
	{
		return !horaCliente.isBefore(horaApertura) && !horaCliente.isAfter(horaCierre);
	}
	
	
	//getters setters
	public LocalTime getHoraApertura() {
		return horaApertura;
	}

	public LocalTime getHoraCierre() {
		return horaCierre;
	}
	
}
