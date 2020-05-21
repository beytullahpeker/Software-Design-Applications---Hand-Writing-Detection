package imageOperations;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ChooseImage {
	
	private File selectedFile;
	ChooseImage(){
		
		JFileChooser file = new JFileChooser();
		file.setCurrentDirectory(new File(System.getProperty("user.home")));
		//filter the files
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
		file.addChoosableFileFilter(filter);
		int result = file.showSaveDialog(null);
		//if the user click on save in Jfilechooser
		
		if(result == JFileChooser.APPROVE_OPTION){
			selectedFile = file.getSelectedFile();
		}
		else if(result == JFileChooser.CANCEL_OPTION){
			System.out.println("No File Select");
			}
		else { System.out.println("NULL"); }
	}
	
	public File gsf(File a){ //getSelectedFile
		this.selectedFile=a;
		return selectedFile;
	}

}
