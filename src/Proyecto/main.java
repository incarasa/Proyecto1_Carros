package Proyecto;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Instalaciones.Sede;
import Instalaciones.Sedes;
import Inventario.Carro;
import Inventario.InventarioCarros;
import Usuarios.Cliente;
import Usuarios.Usuarios;

public class main {

	public static void main(String[] args) 
	{
		Sedes sedes = new Sedes();

		
		sedes.cargarListaArchivos();
		sedes.cargarSedesMapa();
		Map <String , Sede> mapa = sedes.getMapaSedes();
		
		Sede normandia = mapa.get("Normandía");
		
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
		
		sedes.definirHorarioSede("Normandía", DayOfWeek.MONDAY, LocalTime.of(9, 0), 
				LocalTime.of(18, 0));
		
		if(normandia.estaAbierta(DayOfWeek.MONDAY, LocalTime.of(10, 0))) {
			System.out.println("ABIERTOOOO");
		}
		
		System.out.println("PRUEBAS INVENTARIO");
		
		InventarioCarros inventario = new InventarioCarros();
		inventario.agregarCarro("MCU788", "KIA", 2013, "MECANICA", "A", "NORMANDÍA");
		inventario.agregarCarro("HJM755", "FORD", 2020, "AUTOMATICA", "B", "MODELIA");
		inventario.cargarCarrosDesdeCSV();
		inventario.agregarCarro("LZQ422", "CHEVROLET", 2020, "AUTOMATICA", "C", "MODELIA");
		Map<String, Carro> mapa_carros = inventario.getInventario();
		Carro carro1 = mapa_carros.get("MCU788");
		System.out.println(carro1.getMarca());
		
		//PRUEBAS SOBRE USUARIOS
		Usuarios usuarios = new Usuarios();
		usuarios.cargarClientesDesdeCSV();
		usuarios.cargarEmpleadosDesdeCSV();
		usuarios.crearCliente("incarasa","12345","cliente","Raul Insuasty","1000271188",
				"3142715800", "rasanicw@gmail.com","01/08/2004","3102177829",
				"Colombia","10/12/2030", "31203444403330", "042", "04/26");
		
		usuarios.crearEmpleado("empleado1", "12345", "empleado", "Alberto Rojas", "Normandía");
		usuarios.crearEmpleado("empleado2", "12345", "empleado", "Pablo Beltrán", "Modelia");
		usuarios.crearAdminSede("admin1", "12345", "admin", "Rodrigo Pérez", "Modelia");
		usuarios.crearAdminSede("admin2", "12345", "admin", "Santiago Cárdenas", "Normandía");
		
		
		
	}

}
