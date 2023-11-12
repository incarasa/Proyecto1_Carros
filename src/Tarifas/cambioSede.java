package Tarifas;

public class cambioSede 
{
	private int tarifaCambioSede = 20000;
	
	public cambioSede()
	{
		this.tarifaCambioSede = 20000;
	}
	
	// METODOS
	
	public void cambiarTarifa(int tarifaNueva)
	{
		this.tarifaCambioSede = tarifaNueva;
	}
	
	public int getTarifa()
	{
		return tarifaCambioSede;
	}
}
