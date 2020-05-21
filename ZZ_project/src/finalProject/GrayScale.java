package finalProject; 

import java.awt.Color; 
import java.awt.Dimension;
import java.awt.Font; 
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO; 
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton; 
import javax.swing.JFileChooser; 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel; 
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GrayScale extends JFrame {
	
	private JPanel mainPanel; 
	private JButton browse;
	private JButton transform;
	private JTextField tx;
	
	private JLabel inputLabel;
    private JLabel outputLabel;
    private JLabel buttonLabel;
    private JLabel colorimageLabel;
    private JLabel bwimagepanel; 
    
    BufferedImage img;
	     
	public GrayScale() {
		 
		setMainPanel();
        setLeftSide(); 
        setCenterArea();
        setRightSide();
        
        
		this.setVisible(true);
		this.setSize(new Dimension(1260, 500));
		this.setLocationRelativeTo(null);
		//this.setResizable(false);
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

        colorimageLabel = new JLabel();
        
        panel.add(inputLabel);
        panel.add(colorimageLabel);

        mainPanel.add(panel);
    }
	
	private void setCenterArea(){
		
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


        centerPanel.add(new JLabel("Buraya da digital", SwingConstants.CENTER), gbc); 
        tx = new JTextField("        A        ");
        tx.setSize(100, 100);
        centerPanel.add(tx, gbc);
         

	browse.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e)  {
					// How big should the pixelations be?
					final int PIX_SIZE = 10;
					
	
					JFileChooser file = new JFileChooser();
					file.setCurrentDirectory(new File(System.getProperty("user.home")));
					//filter the files
					FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
					file.addChoosableFileFilter(filter);
					int result = file.showSaveDialog(null);
					//if the user click on save in Jfilechooser
					
					if(result == JFileChooser.APPROVE_OPTION){
						File selectedFile = file.getSelectedFile();
						ImageIcon coloredimage;
						
						
						try {
							
							ImageIcon colorimagecolor =new ImageIcon(ImageIO.read(selectedFile));
							Image colimg = colorimagecolor.getImage();
							colorimageLabel.setIcon(ResizeImage(colimg));
							
							 
							
							
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} 
						
						
						
						// Read the file as an Image
						try {
							img = ImageIO.read(selectedFile);
							
	
						// Get the raster data (array of pixels)
						Raster src = img.getData();
	
						// Create an identically-sized output raster
						WritableRaster dest = src.createCompatibleWritableRaster();
	
						// Loop through every PIX_SIZE pixels, in both x and y directions
						for(int y = 0; y < src.getHeight(); y += PIX_SIZE) {
						    for(int x = 0; x < src.getWidth(); x += PIX_SIZE) {
	
						        // Copy the pixel
						        double[] pixel = new double[3];
						        pixel = src.getPixel(x, y, pixel);
	
						        // "Paste" the pixel onto the surrounding PIX_SIZE by PIX_SIZE neighbors
						        // Also make sure that our loop never goes outside the bounds of the image
						        for(int yd = y; (yd < y + PIX_SIZE) && (yd < dest.getHeight()); yd++) {
						            for(int xd = x; (xd < x + PIX_SIZE) && (xd < dest.getWidth()); xd++) {
						                dest.setPixel(xd, yd, pixel);
						            }
						        }
						    }
						} 
						// Save the raster back to the Image
						img.setData(dest); 
	
						 
						
						
						
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					else if(result == JFileChooser.CANCEL_OPTION){
						System.out.println("No File Select");
						} 
				} 
			});
	
	transform.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			BufferedImage mybwimage = new BufferedImage(
					img.getWidth(),
					img.getHeight(),
                    BufferedImage.TYPE_BYTE_BINARY);
			
			Graphics2D graphic = mybwimage.createGraphics();
            graphic.drawImage(img, 0, 0, Color.WHITE, null);
            graphic.dispose();
			ImageIcon blackandwhiteimage = new ImageIcon(mybwimage);
			Image bw = blackandwhiteimage.getImage();
			bwimagepanel.setIcon(ResizeImage(bw));
		}
		
	});
         
        mainPanel.add(centerPanel);
    }
	
	
	private void setRightSide() {
        JPanel panel = new JPanel(); 
        panel.setPreferredSize(new Dimension(410, 450));
        
        outputLabel = new JLabel("OUTPUT:");
        outputLabel.setFont(new Font("Arial", 20, 30));
        
        bwimagepanel = new JLabel();
  
        panel.add(outputLabel); 
        panel.add(bwimagepanel);
        
        mainPanel.add(panel);
    }
	
	
	public ImageIcon ResizeImage(Image img)
    { 
        Image newImg = img.getScaledInstance(400,400, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
	
 
	public static void main(String [] args){
		new GrayScale();
	}
}
