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

public class InterfazRegistrarse extends JFrame{
	
		private String title = "Registrarse";
		
		private Color backgroundColor = Color.WHITE;
		
		private int currentPage;
		
		private int previousPage;
		
		private List<JPanel> panels = new ArrayList<>();
	  
	
		public InterfazRegistrarse() {
			
			// properties

			setTitle(title);
			setSize(1000, 700);
			getContentPane().setBackground(backgroundColor);
			setResizable(false);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			// panels
			setLayout(null);
			
			JLabel registrarseLabel = new JLabel("Registrarse");
			registrarseLabel.setFont(new Font("Arial", Font.BOLD, 40));
			registrarseLabel.setBounds(48, 80, 890, 50);
			add(registrarseLabel);
			
			// subtitle
			JLabel subtitle = new JLabel("Datos personales");
			subtitle.setFont(new Font("Arial", Font.BOLD, 16));
			subtitle.setBounds(48, 130, 890, 45);;
			this.add(subtitle);
						
			
			JPanel pagina1 = crearPagina1(this, Color.WHITE, Color.BLACK);
			JPanel pagina2 = crearPagina2(this, Color.WHITE, Color.BLACK);
			JPanel pagina3 = crearPagina3(this, Color.WHITE, Color.BLACK);
			
			pagina1.setVisible(true);
			pagina2.setVisible(false);
			pagina3.setVisible(false);
			
			panels.add(pagina1);
			panels.add(pagina2);
			panels.add(pagina3);
			
			
			
			setVisible(true);
		}
		
		private void setCurrentPage(int i) {
			this.currentPage = i;
		}

		private void setPreviousPage() {
		
			if (currentPage == 0 ) {
			previousPage = 0;
			}
			else {
			previousPage = currentPage-1;
			}
		}
		
		private int getCurrentPage() {
		    return currentPage;
		}

		private JPanel crearPanelCentral(JFrame currentFrame, Color backgroundColor, Color textColor, List<Component> elementosFormulario1) {
			
			JPanel pnlCentral = new JPanel();
			pnlCentral.setBackground(backgroundColor);
			pnlCentral.setPreferredSize(new Dimension(890,10));
			pnlCentral.setLayout(new FlowLayout(FlowLayout.LEADING, 6, 12));
			pnlCentral.setBounds(34, 162, 890, 440);
			
			
		    PanelFormulario pnlFormulario = new PanelFormulario(backgroundColor, textColor, elementosFormulario1, 876, 270);
		    
		    pnlCentral.add(pnlFormulario);
		    currentFrame.add(pnlCentral, BorderLayout.CENTER);
		    
		    return pnlCentral;
		}
		
		
		private JPanel crearPagina1(JFrame currentFrame, Color backgroundColor, Color textColor) {
						
			List<Component> lst = new ArrayList<>();
			   
			lst.add(new TextField("Nombre Completo", 350, 50, textColor, backgroundColor));
			lst.add(new TextField("Correo Electrónico", 350, 50, textColor, backgroundColor));
			lst.add(new TextField("Fecha de Nacimiento (DD-MM-YYYY)", 350, 50, textColor, backgroundColor));
			lst.add(new TextField("Número de teléfono", 350, 50, textColor, backgroundColor));
			lst.add(new TextField("Documento de Identidad", 350, 50, textColor, backgroundColor));
			lst.add(new TextField("Contraseña", 350, 50, textColor, backgroundColor));
			
			JPanel pnlCentral1 = crearPanelCentral(currentFrame, backgroundColor, textColor, lst);
			crearPanelBotones(pnlCentral1, currentFrame, "Cancelar", "Continuar");
			
			return pnlCentral1;
		}
		
		
		private JPanel crearPagina2(JFrame currentFrame, Color backgroundColor, Color textColor) {
			
			List<Component> lst = new ArrayList<>();
			   
			lst.add(new TextField("Número de Licencia", 350, 50, textColor, backgroundColor));
			lst.add(new TextField("País de Expedición", 350, 50, textColor, backgroundColor));
			lst.add(new TextField("Fecha de Vencimiento de la Licencia (DD-MM-YYYY)", 350, 50, textColor, backgroundColor)); 
			
			JPanel pnlCentral1 = crearPanelCentral(currentFrame, backgroundColor, textColor, lst);
			crearPanelBotones(pnlCentral1, currentFrame, "Volver", "Continuar");
			
			return pnlCentral1;
		}
		
		
		private JPanel crearPagina3(JFrame currentFrame, Color backgroundColor, Color textColor) {
			
			List<Component> lst = new ArrayList<>();
			   
			lst.add(new TextField("Nombre del titular", 350, 50, textColor, backgroundColor));
			lst.add(new TextField("Número de la tarjeta", 350, 50, textColor, backgroundColor));
			lst.add(new TextField("Fecha de vencimiento (DD-MM-YYYY)", 350, 50, textColor, backgroundColor)); 
			lst.add(new TextField("CVV", 100, 50, textColor, backgroundColor)); 
			
			JPanel pnlCentral1 = crearPanelCentral(currentFrame, backgroundColor, textColor, lst);
			crearPanelBotones(pnlCentral1, currentFrame, "Volver", "Guardar");
			
			return pnlCentral1;
		}
		
		public void crearPanelBotones(JPanel containerPanel, JFrame currentFrame,String btnIzq, String btnDer) {
			
			JPanel panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER,6,12));
			panelBotones.setPreferredSize(new Dimension(330, 64));
			panelBotones.setBackground(null);
			
			if (btnIzq.equals("Cancelar")) {
		        JButton btnIniciarSesion = crearBotonCancelar(currentFrame);
		        panelBotones.add(btnIniciarSesion);
		    }

		    if (btnIzq.equals("Volver")) {
		        JButton btnRegistrarse = crearBotonVolver(currentFrame);
		        panelBotones.add(btnRegistrarse);
		    }
		    
		    if (btnDer.equals("Continuar")) {
		        JButton btnRegistrarse = crearBotonContinuar(currentFrame);
		        panelBotones.add(btnRegistrarse);
		    }
		    
		    if (btnDer.equals("Guardar")) {
		        JButton btnRegistrarse = crearBotonGuardar(currentFrame);
		        panelBotones.add(btnRegistrarse);
		    }
		    
			containerPanel.add(panelBotones);			
		}

		private JButton crearBotonContinuar(JFrame currentFrame) {
			
			JButton btn = new JButton();
			Color buttonColor = Color.BLACK;
			Color borderColor = Color.BLACK;
			Color textColor = Color.WHITE;
			
			btn.setText("Continuar");
			btn.setPreferredSize(new Dimension(150, 40));
			btn.setFont(new Font("Arial", Font.PLAIN, 14));
			btn.setBackground(buttonColor);
			btn.setForeground(textColor);
			btn.setBorder(BorderFactory.createLineBorder(borderColor));
			
			btn.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	currentPage = getCurrentPage();
		        	setCurrentPage(currentPage + 1);
		        	setPreviousPage();
		        	paginaVisible(panels, previousPage, currentPage);
		        	
		        		;}
			}); 
			
			btn.setVisible(true);
		    return btn;
		}
		
		private JButton crearBotonVolver(JFrame currentFrame) {
			
			JButton btn = new JButton();
			Color buttonColor = new Color(228, 228, 228);
			Color borderColor = new Color(205, 205, 205);
			Color textColor = Color.BLACK;
			
			btn.setText("Volver");
			btn.setPreferredSize(new Dimension(150, 40));
			btn.setFont(new Font("Arial", Font.PLAIN, 14));
			btn.setBackground(buttonColor);
			btn.setForeground(textColor);
			btn.setBorder(BorderFactory.createLineBorder(borderColor));
			
			btn.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	currentPage = getCurrentPage();
		        	setCurrentPage(currentPage - 1);
	        		paginaVisible(panels, previousPage, currentPage);
	        		;}
			}); 
			
			btn.setVisible(true);
		    return btn;
		}
		
		
		private JButton crearBotonGuardar(JFrame currentFrame) {
			
			JButton btn = new JButton();
			Color buttonColor = new Color(0, 113, 227);
			Color borderColor = new Color(0, 113, 227);
			Color textColor = Color.WHITE;
			
			btn.setText("Guardar");
			btn.setPreferredSize(new Dimension(150, 40));
			btn.setFont(new Font("Arial", Font.PLAIN, 14));
			btn.setBackground(buttonColor);
			btn.setForeground(textColor);
			btn.setBorder(BorderFactory.createLineBorder(borderColor));
			
			btn.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	//disposes and jumps to nextJFrame
		        	currentFrame.dispose();
	        		;}
			}); 
			
			btn.setVisible(true);
		    return btn;
		}
		
		
		private JButton crearBotonCancelar(JFrame currentFrame) {
			
			JButton btn = new JButton();
			Color buttonColor = new Color(228, 228, 228);
			Color borderColor = new Color(205, 205, 205);
			Color textColor = Color.BLACK;
			
			btn.setText("Cancelar");
			btn.setPreferredSize(new Dimension(150, 40));
			btn.setFont(new Font("Arial", Font.PLAIN, 14));
			btn.setBackground(buttonColor);
			btn.setForeground(textColor);
			btn.setBorder(BorderFactory.createLineBorder(borderColor));
			
			btn.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	new InterfazInicio();
		            currentFrame.dispose();}
			}); 
			
			btn.setVisible(true);
		    return btn;
		}
		
		
		private void paginaVisible(List<JPanel> panels, int previousPage, int currentPage) {
			
			if (previousPage == currentPage){
				
			}
			 panels.get(previousPage).setVisible(true);
			 panels.get(currentPage).setVisible(false);
			
			if (previousPage > 0 && currentPage <= (panels.size())){
				
			}
			 panels.get(previousPage).setVisible(false);
			 panels.get(currentPage).setVisible(true);
		}
			
		
		public static void main(String[] args) 
		{
			try {
	            UIManager.setLookAndFeel(new FlatLightLaf()); 
	        } 
			catch (UnsupportedLookAndFeelException e) {
	            e.printStackTrace();
	        }
			new InterfazRegistrarse();
		}

		
		
	

	
}
