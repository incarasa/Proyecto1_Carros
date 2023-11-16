package Interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelAdminPrincipalBotones extends JPanel implements ActionListener
{
	private JButton btnCrearAdmin = new JButton("Crear administrador");
	private JButton btnEliminarAdmin = new JButton("Eliminar administrador");
	private JButton btnCrearSede = new JButton("Crear sede");
	private JButton btnEliminarSede = new JButton("Eliminar sede");
	
	
	private InterfazAdminPrincipal interfazAdminPrincipal;
	
	public PanelAdminPrincipalBotones(InterfazAdminPrincipal interfazAdminPrincipal)
	{
		setPreferredSize(new Dimension(100,50));
		
		this.interfazAdminPrincipal = interfazAdminPrincipal;
		setLayout(new GridLayout(1,5));
		
		btnCrearAdmin.setEnabled(false);
		btnEliminarAdmin.setEnabled(false);
		btnCrearSede.setEnabled(false);
		btnEliminarSede.setEnabled(false);
		
		//actionlisteners
		btnCrearAdmin.addActionListener(this);
		btnEliminarAdmin.addActionListener(this);
		btnCrearSede.addActionListener(this);
		btnEliminarSede.addActionListener(this);
		
		add(btnCrearAdmin);
		add(btnEliminarAdmin);
		add(btnCrearSede);
		add(btnEliminarSede);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btnCrearAdmin) 
		{
			interfazAdminPrincipal.mostrarCrearAdmin(true);
			btnCrearAdmin.setEnabled(false);
		}
		else if (e.getSource() == btnEliminarAdmin)
		{
			interfazAdminPrincipal.eliminarAdmin();
			btnEliminarAdmin.setEnabled(false);
		}
		else if(e.getSource() == btnCrearSede)
		{
			interfazAdminPrincipal.mostrarCrearSede(true);
			btnCrearSede.setEnabled(false);
		}
		else if(e.getSource() == btnEliminarSede)
		{
			interfazAdminPrincipal.eliminarSede();
			btnCrearSede.setEnabled(false);
		}
	}
	
	public void activarBtnCrearAdmin(boolean estado)
	{
		btnCrearAdmin.setEnabled(estado);
	}
	
	public void activarBtnEliminarAdmin(boolean estado)
	{
		btnEliminarAdmin.setEnabled(estado);
	}
	
	public void activarBtnCrearSede(boolean estado)
	{
		btnCrearSede.setEnabled(estado);
	}
	public void activarBtnEliminarSede(boolean estado)
	{
		btnEliminarSede.setEnabled(estado);
	}
	
}
