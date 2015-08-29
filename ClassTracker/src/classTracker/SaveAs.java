package classTracker;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


// Class that opens Save as window
public class SaveAs {
	
	JFileChooser fc; // File chooser
	JFrame frame = new JFrame();
	
	public SaveAs(){
		
		fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(frame);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			
			fc.getCurrentDirectory();
			
			// Overwrite window
			boolean bool = overwrite();
			if(bool == true){
				new SaveFile(); // Contiune and save the file
			}
			else{
				new SaveAs(); // Re-launch Save As
			}
			
		}
		
		
	}
	// If file exists, this will overwrite it.
	private boolean overwrite() {
		File file = fc.getSelectedFile();
		boolean bool= false;
		if(file.exists()){
			int result = JOptionPane.showConfirmDialog(frame,"The selected file already exists, overwrite?","Overwrite",JOptionPane.YES_NO_CANCEL_OPTION);
            switch(result){
	            case JOptionPane.YES_OPTION:
	                bool = true;
	                break;
	            case JOptionPane.NO_OPTION:
	                bool = false;
	                break;
	            case JOptionPane.CLOSED_OPTION:
	                bool = false;
	                break;
	            case JOptionPane.CANCEL_OPTION:
	                bool = false;
	                break;
            }
		}
		return bool;
	}
	
}
