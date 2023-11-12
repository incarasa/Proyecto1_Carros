package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TextField extends JPanel {

	public TextField(String titulo, int w, int h, Color textColor, Color backgroundColor) {
		
		
		this.setLayout(new FlowLayout(FlowLayout.LEADING,0,12));
		this.setPreferredSize(new Dimension(w,(h+10)));
		this.setBackground(null);
		
        JLabel tfLabel = new JLabel(titulo);
        tfLabel.setForeground(textColor);
        tfLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        tfLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 3, 0));
        
        JTextField tfBox = new JTextField();
        tfBox.setSize(w, h);
        tfBox.setForeground(textColor);
        tfBox.setFont(new Font("Arial", Font.PLAIN, 14));
        tfBox.setBackground(backgroundColor);
       
        setLayout(new BorderLayout());

        this.add(tfLabel, BorderLayout.NORTH);
		this.add(tfBox, BorderLayout.CENTER);
        
        this.setVisible(true);
		
	}
}
