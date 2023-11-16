package Interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Usuarios.Administrador_Sede;

public class PanelAdministradorBotones extends JPanel implements ActionListener
{
		private JButton btnCrearEmpleado = new JButton("Crear empleado");
		private JButton btnEliminarEmpleado = new JButton("Eliminar empleado");
		
		private InterfazAdministrador interfazAdministrador;
		
		public PanelAdministradorBotones(InterfazAdministrador interfazAdministrador)
		{
			setPreferredSize(new Dimension(100,50));
			
			this.interfazAdministrador = interfazAdministrador;
			setLayout(new GridLayout(1,3));
			
			btnCrearEmpleado.setEnabled(false);
			btnEliminarEmpleado.setEnabled(false);
			
			//actionlisteners
			btnCrearEmpleado.addActionListener(this);
			btnEliminarEmpleado.addActionListener(this);
			
			add(btnCrearEmpleado);
			add(btnEliminarEmpleado);
			
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource()==btnCrearEmpleado) 
			{
				interfazAdministrador.mostrarCrearEmpleado(true);
				btnCrearEmpleado.setEnabled(false);
			}
			else if (e.getSource() == btnEliminarEmpleado)
			{
				interfazAdministrador.eliminarEmpleado();
				btnEliminarEmpleado.setEnabled(false);
			}
		}
		
		public void activarBtnCrearEmpleado(boolean estado)
		{
			btnCrearEmpleado.setEnabled(estado);
		}
		
		public void activarBtnEliminarEmpleado(boolean estado)
		{
			btnEliminarEmpleado.setEnabled(estado);
		}
}
