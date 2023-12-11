package controller;

import model.Usuario;

public class ControladorUsuario
{
	private Usuario usuario;
	public Usuario registrarUsuario(String usuario2, String contraseña,
			String tipoUsuario, String nombre, String numeroDocumento,
			String telefono, String correo, String fecha_nacimiento,
			String numeroLicencia, String paisExpedición,
			String fechaVencimientoLicencia, String numeroTarjeta,
			String codigoSeguridad, String fechaVencimientoTarjeta)
	{
		usuario = new Usuario(usuario2, contraseña, tipoUsuario, nombre,
				numeroDocumento, telefono, correo, fecha_nacimiento,
				numeroLicencia, paisExpedición, fechaVencimientoLicencia,
				numeroTarjeta, codigoSeguridad, fechaVencimientoTarjeta);
		return usuario;

	}
}
