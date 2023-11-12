package Interfaz;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


@SuppressWarnings("serial")
public class InterfazAdmin extends JFrame { 

	private PanelInicioAdmin panelAdmin;
			
	public InterfazAdmin() {
		
		// properties
		setTitle("Administrador Principal");
		setSize(1000,700);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// panels
		this.setPanelPrincipal(new PanelInicioAdmin(this));
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
		new InterfazAdmin();
	}

	public PanelInicioAdmin getPanelPrincipal() {
		return panelAdmin;
	}

	public void setPanelPrincipal(PanelInicioAdmin panelAdmin) {
		this.panelAdmin = panelAdmin;
	}
	
}
