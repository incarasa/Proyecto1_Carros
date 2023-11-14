package Interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelAlquiler extends JPanel implements ActionListener
{
	private JLabel labTitulo = new JLabel("Alquilar Vehiculo");
	private JLabel labCedula = new JLabel("Ingrese la cedula del cliente");
	private JTextField txtCedulaCliente = new JTextField();
	private JButton btnConsultar = new JButton("Consultar");
	private JLabel labEstado = new JLabel("");
	
	private VentanaAlquilar ventanaAlquilar;
	
	public PanelAlquiler(VentanaAlquilar ventanaAlquilar)
	{
		this.ventanaAlquilar = ventanaAlquilar;
		
		setLayout(new GridLayout(22,1));
		setPreferredSize(new Dimension(500,20));
		setBackground(Color.WHITE);
		labTitulo.setFont(new Font("Sans-serif", Font.BOLD, 20));
		
		labEstado.setForeground(new Color(215, 0, 64));
		labEstado.setFont(new Font("Sans-serif", Font.BOLD, 20));
		
		
		//actionlisteners
		btnConsultar.addActionListener(this);
		
		//añadir elementos
		add(labTitulo);
		add(new JLabel(""));
		add(labCedula);
		add(txtCedulaCliente);
		add(btnConsultar);
		add(labEstado);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btnConsultar) 
		{
			String cedula = txtCedulaCliente.getText();
			if(cedula.equals(""))
			{
				setEstado("Debe ingresar la cédula del cliente");
			}
			else if (!cedula.matches("\\d+")) //tiene cosas que no sean numeros
			{
				setEstado("Cedula inválida ingrese solo números");
			}
			else
			{
				consultarReserva(cedula);
			}
		}
		
	}
	
	public void setEstado(String estado)
	{
		labEstado.setText(estado);
	}
	
	public void consultarReserva(String documento)
	{
		ventanaAlquilar.consultarReserva(documento);
	}
}
