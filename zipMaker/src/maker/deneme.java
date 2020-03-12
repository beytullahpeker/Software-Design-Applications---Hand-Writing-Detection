package maker;  

import javax.imageio.ImageIO;
import javax.swing.*; 

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class deneme extends JFrame {  
	
	private ArrayList<String> a; 
    private String filename;  
    private JButton browseExcel, browseDir;
	private static JButton send;
    private JFileChooser chooser;
    private DrawingPanel dp;
    private JLabel label1, label2;
	private static JLabel label3;
    
    deneme() throws IOException{
    	
    	String imageResource4 = "excel.png";
        Image myImage4 = ImageIO.read(getClass().getResourceAsStream(imageResource4));
    	ImageIcon imageIcon = new ImageIcon(myImage4);
    	Image image = imageIcon.getImage(); // transform it 
    	Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
    	imageIcon = new ImageIcon(newimg);
    	browseExcel = new JButton(imageIcon); 
        browseExcel.setBorder(BorderFactory.createEmptyBorder());
        browseExcel.setContentAreaFilled(false);
        browseExcel.setBounds(354,53,50,50);
        
        
        String imageResource3 = "directory.png";
        Image myImage3 = ImageIO.read(getClass().getResourceAsStream(imageResource3));
        ImageIcon imageIcon2 = new ImageIcon(myImage3);
        Image image2 = imageIcon2.getImage(); // transform it 
    	Image newimg2 = image2.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
    	imageIcon2 = new ImageIcon(newimg2);
        browseDir = new JButton(imageIcon2);
        browseDir.setBorder(BorderFactory.createEmptyBorder());
        browseDir.setContentAreaFilled(false);
        browseDir.setBounds(354, 160, 50, 50); 
        
        label1 = new JLabel();
        label1.setText("Choose an excel file : ");
		label1.setSize(234,50);
		label1.setLocation(44,53); 
		label1.setForeground(Color.BLACK); 
		Font labelFont = label1.getFont();
		String labelText = label1.getText();  
		label1.setFont(new Font(labelFont.getName(), Font.BOLD, 16));
		 
		
        label2 = new JLabel();
        label2.setText("Choose an image directory : ");
        label2.setSize(234,50);
        label2.setLocation(44,160); 
        label2.setForeground(Color.BLACK);
        Font labelFont2 = label1.getFont();
		String labelText2 = label1.getText(); 
		label2.setFont(new Font(labelFont2.getName(), Font.BOLD, 16));
        
		String imageResource2 = "start.png";
        Image myImage2 = ImageIO.read(getClass().getResourceAsStream(imageResource2));
        ImageIcon imageIcon3 = new ImageIcon(myImage2);
        Image image3 = imageIcon3.getImage(); // transform it 
    	Image newimg3 = image3.getScaledInstance(150, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
    	imageIcon3 = new ImageIcon(newimg3);
    	 
        send = new JButton(imageIcon3);
        send.setBorder(BorderFactory.createEmptyBorder());
        send.setContentAreaFilled(false);
        send.setBounds(160,248,170,50); 
        
        
        
        dp = new DrawingPanel(); 
        
        this.setSize(500, 400);
        
        String imageResource = "bg3.jpg";
        Image myImage = ImageIO.read(getClass().getResourceAsStream(imageResource));  
        ImageIcon bg = new ImageIcon(myImage);
        Image ibg = bg.getImage();
        Image newbg = ibg.getScaledInstance( this.getWidth(), this.getHeight(),  java.awt.Image.SCALE_SMOOTH);
         
        this.setContentPane(new JLabel(new ImageIcon(newbg)));
        
        
        this.add(browseExcel); 
        this.add(browseDir);
        this.add(label1);
        this.add(label2);
        this.add(send);
        this.setTitle("IMAGE ZIP MAKER");
        this.add(dp); 
        this.setLocationRelativeTo(null); 
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        browseDir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser(); 
			    chooser.setCurrentDirectory(new java.io.File("./"));
			    chooser.setDialogTitle("Choose");
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    //
			    // disable the "All files" option.
			    //
			    chooser.setAcceptAllFileFilterUsed(false);
			    //    
			    if (chooser.showOpenDialog(browseDir) == JFileChooser.APPROVE_OPTION) {
			      System.out.println("getCurrentDirectory(): "  +  chooser.getCurrentDirectory());
			      System.out.println("getSelectedFile() : "  +  chooser.getSelectedFile()); 
			      try {
					dogrula(chooser.getSelectedFile() , a);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			      }
			    else {
			      System.out.println("No Selection ");
			      } 
			}
		}); 
        
        browseExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	chooser=new JFileChooser();
            	chooser.setCurrentDirectory(new java.io.File("./"));
            	chooser.setDialogTitle("Open Image");
                if (chooser.showOpenDialog(browseExcel) == JFileChooser.APPROVE_OPTION) { 
                    System.out.println("getCurrentDirectory(): "  +  chooser.getCurrentDirectory());
  			      	System.out.println("getSelectedFile() : "  +  chooser.getSelectedFile());
  			      	String dir = chooser.getName(chooser.getCurrentDirectory());
  			      	String name = chooser.getName(chooser.getSelectedFile());
                    try {
						a = readExcel(chooser.getSelectedFile(), "Sayfa1"); 
						  
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
            }
        }); 
    } 
     
    public static void rar(ArrayList<String> img ,File path) throws IOException { 
    	System.out.println("Ýçeride rar");
    	System.out.println("Path : " +path); 
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");  
    	LocalDateTime now = LocalDateTime.now();  
  	  
    	java.util.List<String> srcFiles =img;
    	System.out.println("srcFiles" + srcFiles);
        FileOutputStream fos = new FileOutputStream(path + dtf.format(now)+".zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        
         
         
        for (String srcFile : srcFiles) {
        	System.out.println("Tek tek yaz " +srcFile);
        	 
        	
            File fileToZip = new File(path + "\\" + srcFile);
            System.out.println("1"); 
            System.out.println(fileToZip);
            FileInputStream fis = new FileInputStream(fileToZip);
            System.out.println("2");
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            System.out.println("3");
            zipOut.putNextEntry(zipEntry);
            System.out.println("4");
            byte[] bytes = new byte[1024];
            int length;
            while((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }
        zipOut.close();
        fos.close();
        JOptionPane.showMessageDialog(null, "Zip dosyanýz oluþturuldu.");  	  
    } 
    
    public static void dogrula(File path, ArrayList<String> a) throws IOException {
    	System.out.println("Ýçeride dogrula");
    	System.out.println("Doðrula Path : " +path);
     
  	     final String[] EXTENSIONS = new String[]{
  	        "gif", "png", "bmp" , "jpg", "jpeg"
  	    };
  	    final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
  	        public boolean accept(final File dir, final String name) {
  	            for (final String ext : EXTENSIONS) {
  	                if (name.endsWith("." + ext)) {
  	                    return (true);
  	                }
  	            }
  	            return (false);
  	        }
  	    };  
  	    
        ArrayList<String> arr = new ArrayList<String>();
        ArrayList<String> common = new ArrayList<String>(); 
        
        if (path.isDirectory()) { // make sure it's a directory
            for (final File f : path.listFiles(IMAGE_FILTER)) {
                BufferedImage img = null;
                    img = ImageIO.read(f);
                    System.out.println(f.getName());
                    String parentDirName = f.getName();
                     
                   arr.add(parentDirName);  
                   System.out.println("parentDirName : "+parentDirName);
            }
            for(String s: arr)
         	   System.out.println(s);
            
           for(int i=0; i<arr.size(); i++){
        	  
        	   for(int j=0; j<a.size(); j++){
        		   
	        	   if(arr.get(i).equals(a.get(j)) || arr.get(i).substring(0, arr.get(i).lastIndexOf('.')).equals(a.get(j))){
	        		   common.add(arr.get(i)); 
	        	   } 
        	   }  
           }
           for(String s: common)
        	   System.out.println("Common: "+s); 
           
           send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					rar(common, path); 
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			} 
           });
                  
        }
        else{
        	System.out.println("path bir directory deðil");
        }
    } 
    
    public ArrayList<String> readExcel(File fileName,String sheetName) throws IOException{  
    	
    	ArrayList<String> excelBilgileri = new ArrayList<String>();
    	
        FileInputStream inputStream = new FileInputStream(fileName); 
        Workbook guru99Workbook = null;
        
      System.out.println("File name:" +fileName);
      String NAME = fileName.getName();
      
      String fileExtensionName = NAME.substring(NAME.indexOf("."));
      if(fileExtensionName.equals(".xlsx")){ 
    	    guru99Workbook = new XSSFWorkbook(inputStream); 
      } 
      else if(fileExtensionName.equals(".xls")){ 
    	    guru99Workbook = new HSSFWorkbook(inputStream); 
      } 
	    Sheet sheet = guru99Workbook.getSheet(sheetName);  
	    
	    int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum(); 
	    for (int i = 0; i < rowCount+1; i++) { 
	        Row row = sheet.getRow(i); 
	        for (int j = 0; j < 1; j++) { 
	            System.out.print(row.getCell(j).getStringCellValue());
	            excelBilgileri.add(row.getCell(j).getStringCellValue()); 
	        	} 
	        System.out.println();
	    	}
	    
	      System.out.println("excel bilgileri"+excelBilgileri);
	      
	    return excelBilgileri; 
	    }
       
    class DrawingPanel extends JPanel{
        public void paintComponent(Graphics g){
            super.paintComponent(g); 
        }
    }
    
    public static void main(String[] args) throws IOException{
            new deneme();  
    } 
}