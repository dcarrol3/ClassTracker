package classTracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


// Class that opens file
public class OpenFile {
	
	public static void openFile(){
		
		ArrayList<SchoolClass> list;
		File ex = new File(SaveFile.getFilePath());
		if(ex.exists()){
			
			try{
				 FileInputStream file = new FileInputStream(SaveFile.getFilePath());
				 ObjectInputStream objIn = new ObjectInputStream(file);
				 list = (ArrayList) objIn.readObject();
				 objIn.close();
				 file.close();
				 
				 decodeList(list);
				 
			} catch (Exception e){
				e.printStackTrace();
			}
		
		}
		
	}
	
	// PLaces objects where they need to be
	private static void decodeList(ArrayList<SchoolClass> list){
		
		ArrayList<Assignment> assigns;
		
		// Add class
		for(SchoolClass c : list){
			if(!(c.isRemoved())){
				MainWindow.canvas.addClass(c);
			}
		}
	}
	
}