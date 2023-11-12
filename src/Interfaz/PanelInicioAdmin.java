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

public class PanelInicioAdmin implements PanelInicio {
	
	PanelInicioAdmin(JFrame currentFrame) {
		
		currentFrame.add(crearPanelSuperior(currentFrame),BorderLayout.CENTER);
		currentFrame.add(crearPanelInferior(currentFrame),BorderLayout.SOUTH);	
	}

	@Override
	public JPanel crearPanelSuperior(JFrame currentFrame) {
		
		JPanel pnlSuperior = new JPanel();
		pnlSuperior.setBackground(new Color(33,33,33));
		pnlSuperior.setPreferredSize(new Dimension(1000,500));
		pnlSuperior.setLayout(null);
		
		JLabel bienvenidoLabel = new JLabel("Hola, [nombreAdmin]");
	    bienvenidoLabel.setFont(new Font("Arial", Font.BOLD, 32));
	    bienvenidoLabel.setForeground(Color.WHITE);
	    bienvenidoLabel.setBounds(90, 100, 500, 40);
	    
	    JLabel invitacionLabel = new JLabel("¿Qué deseas consultar?");
	    invitacionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
	    invitacionLabel.setForeground(Color.WHITE);
	    invitacionLabel.setBounds(90, 140, 500, 40);
	
	    pnlSuperior.add(bienvenidoLabel);
	    pnlSuperior.add(invitacionLabel);
	    crearPanelBotones(pnlSuperior, currentFrame);
	    
	    return pnlSuperior;
	}
	
	
	@Override
	public JPanel crearPanelInferior(JFrame currentFrame) {
		JPanel pnlInferior = new JPanel();
		pnlInferior.setBackground(new Color(40,40,40));
		pnlInferior.setPreferredSize(new Dimension(1000,300));	
		return pnlInferior;
	}
	
	
	@Override
	public void crearPanelBotones(JPanel containerPanel, JFrame currentFrame) {
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.LEADING,6,12));
		panelBotones.setBounds(84, 180, 700, 134);
		panelBotones.setBackground(null);
		
		JButton btnConsultarSedes = crearBotonSedes(currentFrame);
		JButton btnConsultarEmpleados = crearBotonEmpleados(currentFrame);
		JButton btnConsultarVehiculos = crearBotonVehiculos(currentFrame);
		
		panelBotones.add(btnConsultarSedes);
		panelBotones.add(btnConsultarEmpleados);
		panelBotones.add(btnConsultarVehiculos);
		containerPanel.add(panelBotones);
		
	}
	
	
	private JButton crearBotonSedes(JFrame currentFrame) {
		
		JButton btn = new JButton();
		Color buttonColor = new Color(42, 42, 42);
		Color borderColor = new Color(73, 73, 73);
		Color textColor = Color.WHITE;
		
		btn.setText("Sedes");
		btn.setPreferredSize(new Dimension(220, 110));
		btn.setFont(new Font("Arial", Font.PLAIN, 14));
		btn.setBackground(buttonColor);
		btn.setForeground(textColor);
		btn.setBorder(BorderFactory.createLineBorder(borderColor));
		
		btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new InterfazSedesAdmin();
                currentFrame.dispose();;}
        });
		
        btn.setVisible(true);
        return btn;
	}
	
	
	private JButton crearBotonEmpleados(JFrame currentFrame) {
		
		JButton btn = new JButton();
		Color buttonColor = new Color(42, 42, 42);
		Color borderColor = new Color(73, 73, 73);
		Color textColor = Color.WHITE;
		
		btn.setText("Empleados");
		btn.setPreferredSize(new Dimension(220, 110));
		btn.setFont(new Font("Arial", Font.PLAIN, 14));
		btn.setBackground(buttonColor);
		btn.setForeground(textColor);
		btn.setBorder(BorderFactory.createLineBorder(borderColor));
		
		btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicked: Empleados");;}
        });
		
        btn.setVisible(true);
        return btn;
	}
	
	private JButton crearBotonVehiculos(JFrame currentFrame) {
		
		JButton btn = new JButton();
		Color buttonColor = new Color(42, 42, 42);
		Color borderColor = new Color(73, 73, 73);
		Color textColor = Color.WHITE;
		
		btn.setText("Vehículos");
		btn.setPreferredSize(new Dimension(220, 110));
		btn.setFont(new Font("Arial", Font.PLAIN, 14));
		btn.setBackground(buttonColor);
		btn.setForeground(textColor);
		btn.setBorder(BorderFactory.createLineBorder(borderColor));
		
		btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicked: Vehículos");;}
        });
		
        btn.setVisible(true);
        return btn;
	}
	
}