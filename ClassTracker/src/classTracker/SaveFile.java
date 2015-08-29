package classTracker;

import java.io.File;
import java.io.IOException;
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

	public static String getDirectory() {
	    return System.getProperty("user.home") + File.separator + ".classtracker";
	}
	
	// Save to XML file
	public static void saveFile(SchoolClass c){
		try {
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc; // DOM
			Element rootElement; // Root
			
			// Create new xml, else load old one
			if(!(new File(getDirectory()).exists())){
				doc = docBuilder.newDocument(); // Create new document
				rootElement = doc.createElement("root"); // Create new root
				doc.appendChild(rootElement);
			}
			else{
				doc = docBuilder.parse(new File(getDirectory())); // Create new document
				rootElement = doc.getDocumentElement(); // Get root element
			}
			
			
			// Create new class node?
			if((doc.getElementsByTagName(c.getClassName()).getLength()) == 0){
				Element classElement = doc.createElement(c.getClassName());
				rootElement.appendChild(classElement);
			}
			

			writeToFile(doc);

		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Save assignment
	public static void saveFile(SchoolClass c, Assignment a){
		
		try{
		
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc; // DOM
			doc = docBuilder.parse(new File(getDirectory())); // Create new document
			
			
			// Create new assignment node?
			if((doc.getElementsByTagName(a.getAssignment()).getLength()) == 0){
				Element class1 = (Element) doc.getElementsByTagName(c.getClassName()).item(0); // Retrieve current class
				class1.appendChild(doc.createElement(a.getAssignment())); // Add assignment to class
			}
		
			writeToFile(doc);
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
	}
	}
	
	// Finalizes file
	private static void writeToFile(Document doc){
		try{
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer;
			
			transformer = transformerFactory.newTransformer();
			
			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(getDirectory()));
	
			
			transformer.transform(source, result);
			
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		System.out.println("File saved!");

	}
	// Check if content has been deleted and do so in XML
	public static void checkForDeleted(){
		
		try{
		
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc; // DOM
			doc = docBuilder.parse(new File(getDirectory())); // Create new document
			
			// Check for deleted objects and remove them from XML
			ArrayList<SchoolClass> classes = MainWindow.canvas.getClasses();
			for(SchoolClass c : classes){
				
				// Assignments within the class
				ArrayList<Assignment> assigns = c.getAssignments();
				for(Assignment a : assigns){
					if(a.isRemoved()){
						Element remA = (Element) doc.getElementsByTagName(a.getAssignment()).item(0);
						remA.getParentNode().removeChild(remA);
					}
				}
				
				// Class itself
				if(c.isRemoved()){
					Element rem = (Element) doc.getElementsByTagName(c.getClassName()).item(0);
					rem.getParentNode().removeChild(rem);
				}
			}
			
			// Write changes to the file
			writeToFile(doc);
			
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
	

