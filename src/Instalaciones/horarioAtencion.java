package Instalaciones;
import java.time.LocalTime;

public class horarioAtencion 
{
	//atributos
	private LocalTime horaApertura;
	private LocalTime horaCierre;
	
	//contructor
	public horarioAtencion(LocalTime horaApertura, LocalTime horaCierre)
	{
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
	}
	
	//metodos
	//esta función me dice si el horario de un cliente se encuentra dentro del
	//horario de la sede.
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
