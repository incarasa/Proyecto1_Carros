package Interfaz;
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


@SuppressWarnings("serial")
public class InterfazInicio extends JFrame { 

	private PanelInicioHome panelPrincipal;
			
	public InterfazInicio() {
		
		// properties
		setTitle("Rent-A-Car");
		setSize(1000,700);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// header
		JPanel header = new JPanel();
		header.setBackground(Color.black);
		header.setPreferredSize(new Dimension(1000,40));
		this.add(header,BorderLayout.NORTH);
		
		// panels
		this.setPanelPrincipal(new PanelInicioHome(this));
		setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		try {
            UIManager.setLookAndFeel(new FlatLightLaf()); 
        } 
		catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
		new InterfazInicio();
	}

	public PanelInicioHome getPanelPrincipal() {
		return panelPrincipal;
	}

	public void setPanelPrincipal(PanelInicioHome panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
	}
	
}
