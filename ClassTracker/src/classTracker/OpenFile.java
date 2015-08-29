package classTracker;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


// Class that opens XML file
public class OpenFile {
	
	static boolean doneLoading = false; // if done loading or not
	
	public static void openFile(){
		
		try{
		
		File fXmlFile = new File(SaveFile.getDirectory());
		if(fXmlFile.exists()){
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();
			
			
			Node childNode = doc.getDocumentElement().getFirstChild();
			
			// Get class nodes
			while(childNode != null){
				if (childNode.getNodeType() == Node.ELEMENT_NODE) {
					SchoolClass c = MainWindow.canvas.addClass(childNode.getNodeName()); // Make new class with this name
					// Get assignments for current class node
					if(childNode.hasChildNodes()){	
						Node assignNode = childNode.getFirstChild();
						while(assignNode != null){
							c.addAssignment(assignNode.getNodeName(), ""); // FIX THIS???
							assignNode = assignNode.getNextSibling();
						}
					}
				}
				childNode = childNode.getNextSibling();
			}
			
	
			
		}
		
		// Finished loading
		doneLoading = true;
		System.out.println("Loaded");
		
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
}