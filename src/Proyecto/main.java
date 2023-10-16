package Proyecto;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Instalaciones.Sede;
import Instalaciones.Sedes;

public class main {

	public static void main(String[] args) 
	{
		Sedes sedes = new Sedes();
		sedes.cargarListaArchivos();
		sedes.cargarSedesMapa();
		Map <String , Sede> mapa = sedes.getMapaSedes();
		
		Sede normandia = mapa.get("Normandia");
		
		List<String> lista = sedes.getListaArchivos();
		
		for (String elemento : lista) 
		{
			
			
            System.out.println(elemento);}
		
		Set <String> lista2 = mapa.keySet();
		
		for (String llave : lista2)
		{
			System.out.println(llave);
		}
		
		
		System.out.println(normandia.getDireccion());
		
		sedes.definirHorarioSede("Normandia", DayOfWeek.MONDAY, LocalTime.of(9, 0), 
				LocalTime.of(18, 0));
		
		if(normandia.estaAbierta(DayOfWeek.MONDAY, LocalTime.of(10, 0))) {
			System.out.println("ABIERTOOOO");
		}
		
		

	}

}
