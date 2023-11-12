package Interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;

public interface PanelInicio {
	
	public JPanel crearPanelSuperior(JFrame currentFrame);
	
	public JPanel crearPanelInferior(JFrame currentFrame);

	public void crearPanelBotones(JPanel containerPanel, JFrame currentInterfaz);
}
