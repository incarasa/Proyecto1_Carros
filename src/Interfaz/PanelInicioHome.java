package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelInicioHome implements PanelInicio {
	
	PanelInicioHome(JFrame currentFrame) {
		
		currentFrame.add(crearPanelSuperior(currentFrame),BorderLayout.CENTER);
		currentFrame.add(crearPanelInferior(currentFrame),BorderLayout.SOUTH);	
	}

	@Override
	public JPanel crearPanelSuperior(JFrame currentFrame) {
		
		JPanel pnlSuperior = new JPanel();
		pnlSuperior.setBackground(new Color(240,240,240));
		pnlSuperior.setPreferredSize(new Dimension(1000,500));
		pnlSuperior.setLayout(null);
		
		JLabel bienvenidoLabel = new JLabel("¡Bienvenido!");
	    bienvenidoLabel.setFont(new Font("Arial", Font.BOLD, 32));
	    bienvenidoLabel.setBounds(90, 80, 500, 40);
	    
	    JLabel invitacionLabel = new JLabel("Alquila tu auto y descubre el mundo con nosotros.");
	    invitacionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
	    invitacionLabel.setBounds(90, 120, 500, 40);
	
	    pnlSuperior.add(bienvenidoLabel);
	    pnlSuperior.add(invitacionLabel);
	    crearPanelBotones(pnlSuperior, currentFrame);
	    
	    return pnlSuperior;
	}
	
	
	@Override
	public JPanel crearPanelInferior(JFrame currentFrame) {
		JPanel pnlInferior = new JPanel();
		pnlInferior.setBackground(Color.white);
		pnlInferior.setPreferredSize(new Dimension(1000,300));	
		return pnlInferior;
	}
	
	
	@Override
	public void crearPanelBotones(JPanel containerPanel, JFrame currentFrame) {
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.LEADING,6,12));
		panelBotones.setBounds(84, 160, 500, 64);
		
		JButton btnIniciarSesion = crearBotonIniciarSesión(currentFrame);
		JButton btnRegistrarse = crearBotonRegistrarse(currentFrame);
		
		panelBotones.add(btnIniciarSesion);
		panelBotones.add(btnRegistrarse);
		containerPanel.add(panelBotones);
		
	}
	
	
	private JButton crearBotonIniciarSesión(JFrame currentFrame) {
		
		JButton btn = new JButton();
		Color buttonColor = new Color(228, 228, 228);
		Color borderColor = new Color(205, 205, 205);
		Color textColor = Color.BLACK;
		
		btn.setText("Iniciar Sesión");
		btn.setPreferredSize(new Dimension(150, 40));
		btn.setFont(new Font("Arial", Font.PLAIN, 14));
		btn.setBackground(buttonColor);
		btn.setForeground(textColor);
		btn.setBorder(BorderFactory.createLineBorder(borderColor));
		
		btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new InterfazLog1n("Iniciar sesión");
                currentFrame.dispose();}
        });
		
        btn.setVisible(true);
        return btn;
	}
	
	
	private JButton crearBotonRegistrarse(JFrame currentFrame) {
		
		JButton btn = new JButton();
		Color buttonColor = new Color(228, 228, 228);
		Color borderColor = new Color(205, 205, 205);
		Color textColor = Color.BLACK;
		
		btn.setText("Registrarse");
		btn.setPreferredSize(new Dimension(150, 40));
		btn.setFont(new Font("Arial", Font.PLAIN, 14));
		btn.setBackground(buttonColor);
		btn.setForeground(textColor);
		btn.setBorder(BorderFactory.createLineBorder(borderColor));
		
		btn.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            new InterfazRegistrarse();
	            currentFrame.dispose();}
        }); 
        
        btn.setVisible(true);
        return btn;
	}
	
}