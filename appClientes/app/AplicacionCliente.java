package app;

import java.io.FileNotFoundException;
import java.io.IOException;

import controller.AppManager;

public class AplicacionCliente
{
	
	private static AppManager appManager;

	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		appManager = new AppManager();
		appManager.cargarInformacion();
		appManager.iniciarAplicacion();
		
	}

}
