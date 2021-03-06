package classTracker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// Saves the file
public final class SaveFile {

	public static String getFilePath() {
	    return System.getProperty("user.home") + File.separator + ".classTracker.ser";
	}
	
	// Save to XML file
	public static void saveFile(){
		try {
			
			FileOutputStream file = new FileOutputStream(getFilePath());
			ObjectOutputStream obOut = new ObjectOutputStream(file);
			obOut.writeObject(MainWindow.canvas.getClasses());
			obOut.close();
			file.flush();
			file.close();
		 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
	

