package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;

import model.Sede;

public class PersistenciaSede
{
	public Sede cargarDesdeArchivo(File archivo) 
	{
        Sede sede = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) 
        {
            String nombre = reader.readLine();
            String direccion = reader.readLine();
            sede = new Sede(nombre , direccion);
            String linea;
            while ((linea = reader.readLine()) != null) 
            {
                String[] partes = linea.split(",");
                DayOfWeek dia = DayOfWeek.valueOf(partes[0]);
                LocalTime horaApertura = LocalTime.parse(partes[1]);
                LocalTime horaCierre = LocalTime.parse(partes[2]);
                sede.agregarHorario(dia, horaApertura, horaCierre);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return sede;
    }
}
