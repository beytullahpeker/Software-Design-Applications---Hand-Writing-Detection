package imageOperations;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Pixelate {
	 
	public static void main(String[] args) throws IOException{
		// How big should the pixelations be?
		final int PIX_SIZE = 100;
		BufferedImage img;

		JFileChooser file = new JFileChooser();
		file.setCurrentDirectory(new File(System.getProperty("user.home")));
		//filter the files
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
		file.addChoosableFileFilter(filter);
		int result = file.showSaveDialog(null);
		//if the user click on save in Jfilechooser
		
		if(result == JFileChooser.APPROVE_OPTION){
			File selectedFile = file.getSelectedFile();
			  
			// Read the file as an Image
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
			
			BufferedImage mybwimage = new BufferedImage(
					img.getWidth(),
					img.getHeight(),
                    BufferedImage.TYPE_BYTE_BINARY);
			
			Graphics2D graphic = mybwimage.createGraphics();
            graphic.drawImage(img, 0, 0, Color.WHITE, null);
            graphic.dispose();

			// Write the new file
			ImageIO.write(mybwimage, "jpg", new File("bwimage.jpg"));  
			
		}
		else if(result == JFileChooser.CANCEL_OPTION){
			System.out.println("No File Select");
			}
		 
		

	}

}
