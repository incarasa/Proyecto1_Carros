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

public class InterfazEmpleadosAdmin extends JFrame{
	
		private String title = "Administrador | Sedes";
		
		private Color backgroundColor = new Color(33,33,33);
		
		private List<JPanel> panels = new ArrayList<>();
	  
	
		public InterfazEmpleadosAdmin() {
			
			// properties

			setTitle(title);
			setSize(1000, 700);
			getContentPane().setBackground(backgroundColor);
			setResizable(false);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			// panels
			setLayout(null);
			
			JLabel sedeLabel = new JLabel("Empleados");
			sedeLabel.setFont(new Font("Arial", Font.BOLD, 40));
			sedeLabel.setForeground(Color.WHITE);
			sedeLabel.setBounds(48, 80, 890, 50);
			add(sedeLabel);
			
			
			JPanel pagina1 = crearPaginaConsultar(this, backgroundColor, Color.WHITE);
			JPanel pagina2 = crearPaginaAgregar(this, backgroundColor, Color.WHITE);
		
			
			pagina1.setVisible(true);
			pagina2.setVisible(false);
			
			panels.add(pagina1);
			panels.add(pagina2);
			
			setVisible(true);
		}
		

		private JPanel crearPanelCentral(JLabel subtitle, JFrame currentFrame, Color backgroundColor, Color textColor, List<Component> elementosFormulario1) {
			
			JPanel pnlCentral = new JPanel();
			pnlCentral.setBackground(backgroundColor);
			pnlCentral.setPreferredSize(new Dimension(890,10));
			pnlCentral.setLayout(new FlowLayout(FlowLayout.LEADING, 6, 12));
			pnlCentral.setBounds(34, 130, 890, 440);
		
			// subtitle
			pnlCentral.add(subtitle);
			
		    PanelFormulario pnlFormulario = new PanelFormulario(backgroundColor, textColor, elementosFormulario1, 876, 270);
		    
		    pnlCentral.add(pnlFormulario);
		    currentFrame.add(pnlCentral, BorderLayout.CENTER);
		    
		    return pnlCentral;
		}
		
		
		private JPanel crearDisplayCentral(JLabel subtitle, JFrame currentFrame, Color backgroundColor, Color textColor, List<Component> elementosFormulario1) {
			
			JPanel pnlCentral = new JPanel();
			pnlCentral.setBackground(backgroundColor);
			pnlCentral.setPreferredSize(new Dimension(890,10));
			pnlCentral.setLayout(new FlowLayout(FlowLayout.LEADING, 6, 12));
			pnlCentral.setBounds(34, 130, 890, 440);
		
			// subtitle
			pnlCentral.add(subtitle);
		    currentFrame.add(pnlCentral, BorderLayout.CENTER);
		    return pnlCentral;
		}
		
		
		private JPanel crearPaginaConsultar(JFrame currentFrame, Color backgroundColor, Color textColor) {
			
			List<Component> lst = new ArrayList<>();
			
			String txtSubtitle = "  Empleados disponibles" ;
			JLabel subtitle = new JLabel(txtSubtitle);
			subtitle.setFont(new Font("Arial", Font.BOLD, 16));
			subtitle.setForeground(textColor);
			subtitle.setBounds(48, 0, 890, 45);
			
			JPanel pnlDisplay1 = crearDisplayCentral(subtitle, currentFrame, backgroundColor, textColor, lst);
			crearPanelBotones(pnlDisplay1, currentFrame, "Volver", "Agregar");
			
			return pnlDisplay1;
		}
		
		
		private JPanel crearPaginaAgregar(JFrame currentFrame, Color backgroundColor, Color textColor) {
						
			List<Component> lst = new ArrayList<>();
			
			String txtSubtitle = "  Información del empleado" ;
			JLabel subtitle = new JLabel(txtSubtitle);
			subtitle.setFont(new Font("Arial", Font.BOLD, 16));
			subtitle.setForeground(Color.WHITE);
			subtitle.setBounds(48, 0, 890, 60);
			   
			lst.add(new TextField("Nombre", 350, 50, textColor, backgroundColor));
			lst.add(new TextField("Documento de identidad", 350, 50, textColor, backgroundColor));
			lst.add(new TextField("Nombre de la Sede de ubicación", 350, 50, textColor, backgroundColor));
			
			JPanel pnlCentral1 = crearPanelCentral(subtitle, currentFrame, backgroundColor, textColor, lst);
			crearPanelBotones(pnlCentral1, currentFrame, "Cancelar", "Guardar");
			
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
		    
		    if (btnDer.equals("Agregar")) {
		        JButton btnRegistrarse = crearBotonAgregar(currentFrame);
		        panelBotones.add(btnRegistrarse);
		    }
		    
		    if (btnDer.equals("Guardar")) {
		        JButton btnRegistrarse = crearBotonGuardar(currentFrame);
		        panelBotones.add(btnRegistrarse);
		    }
		    
			containerPanel.add(panelBotones);			
		}

		private JButton crearBotonAgregar(JFrame currentFrame) {
			
			JButton btn = new JButton();
			Color buttonColor = new Color(42, 42, 42);
			Color borderColor = new Color(73, 73, 73);
			Color textColor = Color.WHITE;
			
			btn.setText("Agregar Empleado");
			btn.setPreferredSize(new Dimension(150, 40));
			btn.setFont(new Font("Arial", Font.PLAIN, 14));
			btn.setBackground(buttonColor);
			btn.setForeground(textColor);
			btn.setBorder(BorderFactory.createLineBorder(borderColor));
			
			btn.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	paginaVisible(panels, 0, 1);
		        	
		        		;}
			}); 
			
			btn.setVisible(true);
		    return btn;
		}
		
		private JButton crearBotonCancelar(JFrame currentFrame) {
			
			JButton btn = new JButton();
			Color buttonColor = new Color(42, 42, 42);
			Color borderColor = new Color(73, 73, 73);
			Color textColor = Color.WHITE;
			
			btn.setText("Cancelar");
			btn.setPreferredSize(new Dimension(150, 40));
			btn.setFont(new Font("Arial", Font.PLAIN, 14));
			btn.setBackground(buttonColor);
			btn.setForeground(textColor);
			btn.setBorder(BorderFactory.createLineBorder(borderColor));
			
			btn.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
	        		paginaVisible(panels, 1, 0);}
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
		
		
		private JButton crearBotonVolver(JFrame currentFrame) {
			
			JButton btn = new JButton();
			Color buttonColor = new Color(42, 42, 42);
			Color borderColor = new Color(73, 73, 73);
			Color textColor = Color.WHITE;
			
			btn.setText("Volver");
			btn.setPreferredSize(new Dimension(150, 40));
			btn.setFont(new Font("Arial", Font.PLAIN, 14));
			btn.setBackground(buttonColor);
			btn.setForeground(textColor);
			btn.setBorder(BorderFactory.createLineBorder(borderColor));
			
			btn.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	new InterfazAdmin();
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
			new InterfazEmpleadosAdmin();
		}

		
		
	

	
}
