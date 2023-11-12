package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatLightLaf;

@SuppressWarnings("serial")

public class InterfazLog1n extends JFrame {

    public InterfazLog1n(String title) {
        setTitle(title);
        setSize(1000, 700);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        crearHeader();
        crearPanelCentral(Color.WHITE, Color.BLACK);
        
        setVisible(true);
    }

    
   private void crearHeader() {
	   JPanel header = new JPanel();
	   header.setBackground(Color.black);
	   header.setPreferredSize(new Dimension(1000, 40));
	   add(header, BorderLayout.NORTH);
   }    
   
   
   private void crearPanelCentral(Color backgroundColor, Color textColor) {
      
	   JPanel pnlCentral = new JPanel();
	   pnlCentral.setBackground(backgroundColor);
	   pnlCentral.setLayout(new FlowLayout(FlowLayout.CENTER, 6, 12));
	   pnlCentral.setBorder(BorderFactory.createEmptyBorder(120, 375, 80, 375));

       JLabel iniciarSesionLabel = new JLabel("Iniciar Sesión");
       iniciarSesionLabel.setFont(new Font("Arial", Font.BOLD, 32));

       JLabel registrarseLabel = new JLabel("¿No tienes cuenta?");
       registrarseLabel.setFont(new Font("Arial", Font.PLAIN, 12));

       pnlCentral.add(iniciarSesionLabel);
       
       List<Component> lstElements = elementosFormulario(textColor, backgroundColor);
       PanelFormulario pnlFormulario = new PanelFormulario(backgroundColor, textColor, lstElements, 380, 166);

       pnlCentral.add(pnlFormulario);
       crearPanelBotones(pnlCentral, this);
       add(pnlCentral, BorderLayout.CENTER);
   
   }
   
   
   private List<Component> elementosFormulario(Color textColor, Color backgroundColor) {
	   
	   List<Component> lst = new ArrayList<>();
	   
	   lst.add(new TextField("Usuario", 350, 50, textColor, backgroundColor));
	   lst.add(new TextField("Contraseña", 350, 50, textColor, backgroundColor));
	   
	   return lst;  
   }
   
   
   public void crearPanelBotones(JPanel containerPanel, JFrame currentFrame) {
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER,6,12));
		panelBotones.setPreferredSize(new Dimension(162, 116));
		panelBotones.setBackground(null);
		
		JButton btnIniciarSesion = crearBotonIniciarSesión(currentFrame);
		JButton btnRegistrarse = crearBotonCancelar(currentFrame);
		
		panelBotones.add(btnIniciarSesion);
		panelBotones.add(btnRegistrarse);
		containerPanel.add(panelBotones);
		
	}
	
	
	private JButton crearBotonIniciarSesión(JFrame currentFrame) {
		
		JButton btn = new JButton();
		Color buttonColor = new Color(0, 113, 227);
		Color borderColor = new Color(0, 113, 227);
		Color textColor = Color.WHITE;
		
		btn.setText("Iniciar Sesión");
		btn.setPreferredSize(new Dimension(150, 40));
		btn.setFont(new Font("Arial", Font.PLAIN, 14));
		btn.setBackground(buttonColor);
		btn.setForeground(textColor);
		btn.setBorder(BorderFactory.createLineBorder(borderColor));
		
		btn.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               //INICIARSESION
        	   System.out.println("Inicio de sesión exitoso");
               ;}
       });
		
       btn.setVisible(true);
       return btn;
	}
	
	
	private JButton crearBotonCancelar(JFrame currentFrame) {
		
		JButton btn = new JButton();
		Color buttonColor = Color.BLACK;
		Color borderColor = Color.BLACK;
		Color textColor = Color.WHITE;
		
		btn.setText("Cancelar");
		btn.setPreferredSize(new Dimension(150, 40));
		btn.setFont(new Font("Arial", Font.PLAIN, 14));
		btn.setBackground(buttonColor);
		btn.setForeground(textColor);
		btn.setBorder(BorderFactory.createLineBorder(borderColor));
		
		btn.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            //new InterfazRegistrarse();
	        	new InterfazInicio();
	            currentFrame.dispose();}
       }); 
       
       btn.setVisible(true);
       return btn;
	}
   
   public static void main(String[] args) {
       try {
           UIManager.setLookAndFeel(new FlatLightLaf());
       } catch (UnsupportedLookAndFeelException e) {
           e.printStackTrace();
       }
       new InterfazLog1n("Iniciar Sesión");
   }
}


