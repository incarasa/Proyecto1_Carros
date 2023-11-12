package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@SuppressWarnings("serial")
public class PanelFormulario extends JPanel {

    /**
	 * 
	 */

	public PanelFormulario(Color backgroundColor, Color textColor, List<Component> elements, int w, int h) {
        setLayout(new FlowLayout(FlowLayout.LEADING, 12, 15));
        setBackground(backgroundColor);
        setPreferredSize(new Dimension(w, h));

        for (Component element : elements) {
            add(element);
        }
    }
}