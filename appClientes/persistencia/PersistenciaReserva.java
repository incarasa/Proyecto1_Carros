package persistencia;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import model.Reserva;


public class PersistenciaReserva
{
	public String toCSV(Reserva reserva)
	{
		return reserva.getHoraLlegada() + "," + reserva.getDiaInicio() + ","
				+ reserva.getDiaFin() + "," + reserva.getDocumentoCliente()
				+ "," + reserva.getPrecio() + "," + reserva.getCategoria() + ","
				+ reserva.getPlacaVehiculo();
	}
	public void actualizarCSV(Map<String, List<Reserva>> mapaReservas,
			String nombreArchivo)


	{
		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter(nombreArchivo)))
		{
			for (Map.Entry<String, List<Reserva>> entry : mapaReservas
					.entrySet())
			{
				List<Reserva> listaReservas = entry.getValue();
				for (Reserva reserva : listaReservas)
				{
					writer.write(toCSV(reserva));
					writer.newLine();
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public Reserva fromCSV(String lineaCSV)

	{
		String[] partes = lineaCSV.split(",");
		if (partes.length != 7)
		{
			throw new IllegalArgumentException("Formato CSV no válido");
		}

		String[] horaLlegadaSTR = partes[0].split(":");
		String[] diaInicioSTR = partes[1].split("-");
		String[] diaFinSTR = partes[2].split("-");

		LocalTime horaLlegada = LocalTime.of(
				Integer.parseInt(horaLlegadaSTR[0]),
				Integer.parseInt(horaLlegadaSTR[1]));

		LocalDate diaInicio = LocalDate.of(Integer.parseInt(diaInicioSTR[0]),
				Integer.parseInt(diaInicioSTR[1]),
				Integer.parseInt(diaInicioSTR[2]));
		LocalDate diaFin = LocalDate.of(Integer.parseInt(diaFinSTR[0]),
				Integer.parseInt(diaFinSTR[1]), Integer.parseInt(diaFinSTR[2]));

		String documentoCliente = partes[3];
		double precio = Double.parseDouble(partes[4]);
		String categoria = partes[5];
		String placaVehiculo = partes[6];

		Reserva reserva = new Reserva(horaLlegada, diaInicio, diaFin,
				documentoCliente, precio, categoria, placaVehiculo);
		return reserva;
	}

}
