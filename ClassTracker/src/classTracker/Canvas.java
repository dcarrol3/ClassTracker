package classTracker;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Canvas extends JPanel {
	
	JLabel label1;
	ArrayList<SchoolClass> classes = new ArrayList<SchoolClass>();
	JPanel mainPanel;
	
	public Canvas(){
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setAlignmentY(LEFT_ALIGNMENT); // Left justify content in panel
		mainPanel.setAlignmentX(TOP_ALIGNMENT); // Justify in top of the panel
		super.add(mainPanel);
	}
	
	public ArrayList<SchoolClass> getClasses(){
		return classes;
	}
	
	// Add class to canvas and list
	SchoolClass addClass(String name){
		SchoolClass c = new SchoolClass(name);
		c.setAlignmentY(LEFT_ALIGNMENT); // Left justify content in panel
		c.setAlignmentX(TOP_ALIGNMENT); // Justify in top of the panel
		classes.add(c);
		mainPanel.add(c);
		// Repaint and refreash gui
		this.repaint();
		SwingUtilities.getWindowAncestor(this).pack();
		
		// Save the file
		if(OpenFile.doneLoading)
			SaveFile.saveFile(c);
		
		return c;
	}
	
	void clean(){}
}
