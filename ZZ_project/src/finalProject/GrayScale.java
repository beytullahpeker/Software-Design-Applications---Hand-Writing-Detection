package finalProject;
  

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GrayScale extends JFrame {
	
	private JPanel mainPanel;
	private DrawingPanel drawingPanel, drawingPanel2;
	private JButton browse;
	private JButton transform;
	
	private JLabel inputLabel;
    private JLabel outputLabel;
    private JLabel buttonLabel;
	
	public GrayScale() {
		 
		setMainPanel();
        setLeftSide(); 
        setCenterArea();
        setRightSide();
        
        
		this.setVisible(true);
		this.setSize(new Dimension(1260, 500));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	 
	private void setMainPanel() {
        mainPanel = new JPanel(); 
        setContentPane(mainPanel);
    }
	
	private void setLeftSide() {
        JPanel panel = new JPanel(); 
        panel.setPreferredSize(new Dimension(410, 450));
        
        inputLabel = new JLabel("INPUT:");
        inputLabel.setFont(new Font("Arial", 20, 30)); 

        drawingPanel = new DrawingPanel(400, 400, 50);
        
        panel.add(inputLabel);
        panel.add(drawingPanel);

        mainPanel.add(panel);
    }
	
	private void setCenterArea() {
		
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setPreferredSize(new Dimension(200, 450));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER; 
        
        centerPanel.add(new JLabel("Choose an image:", SwingConstants.CENTER), gbc);
        browse = new JButton("Browse");
        centerPanel.add(browse, gbc);
        
        centerPanel.add(Box.createVerticalStrut(20), gbc);
                
        centerPanel.add(new JLabel("Make it grayscale:", SwingConstants.CENTER), gbc); 
        transform = new JButton("Transform");
        centerPanel.add(transform, gbc);
        
        centerPanel.add(Box.createVerticalStrut(20), gbc);

        browse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File(System.getProperty("user.home")));
				//filter the files
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
				file.addChoosableFileFilter(filter);
				int result = file.showSaveDialog(null);
				//if the user click on save in Jfilechooser
				if(result == JFileChooser.APPROVE_OPTION){
					File selectedFile = file.getSelectedFile();
					String path = selectedFile.getAbsolutePath();
					inputLabel.setIcon(ResizeImage(path, inputLabel));
				}
				else if(result == JFileChooser.CANCEL_OPTION){
					System.out.println("No File Select");
					}
				
			}
		});

        mainPanel.add(centerPanel);
    }
	
	private void setRightSide() {
        JPanel panel = new JPanel(); 
        panel.setPreferredSize(new Dimension(410, 450));
        
        outputLabel = new JLabel("OUTPUT:");
        outputLabel.setFont(new Font("Arial", 20, 30));
        
        drawingPanel2 = new DrawingPanel(400, 400, 50);
  
        panel.add(outputLabel); 
        panel.add(drawingPanel2);
        
        mainPanel.add(panel);
    }
	
	public ImageIcon ResizeImage(String ImagePath, JLabel a)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(a.getWidth(), a.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
	 
	class DrawingPanel extends CustomPanel{
		public DrawingPanel(int w, int h, int count) {
	        super(w, h, count);
  
	    }
        public void paintComponent(Graphics g){
            super.paintComponent(g); 
        }
    }
	public static void main(String [] args){
		new GrayScale();
	}
}
