package finalProject;
  
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

public class CustomPanel extends JPanel { 
	 
    private int width;
    private int height;
    private int count;
    private Color customizedColor = null;

    public CustomPanel(int w, int h, int count) {
        super();
        this.width = w;
        this.height = h;
        this.count = count;

        setPreferredSize(new Dimension(w, h));
        setBackground(Color.WHITE);
 
    }

}
