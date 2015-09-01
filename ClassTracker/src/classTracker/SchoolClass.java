package classTracker;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import classTracker.AddClass.AddListener;
import classTracker.AddClass.CancelListener;

// Stores assignments for class
public class SchoolClass extends JPanel{
	
	JLabel className; // Name of class
	String classNameStr; // String version
	ArrayList<Assignment> assignments = new ArrayList<Assignment>();
	JButton addButton; // Add an assignment
	JPanel assignPanel = new JPanel();
	JPanel classNamePanel = new JPanel();
	private boolean isRemoved; //Keeps track is class is removed or not
	
	public SchoolClass(String name){
		super.setLayout(new GridLayout(0, 2));
		classNameStr = name;
		className = new JLabel(name + ":");
		addButton = new JButton("+");
		addButton.addActionListener(new AddListener());
		assignPanel.setLayout(new BoxLayout(assignPanel, BoxLayout.PAGE_AXIS));
		assignPanel.setAlignmentY(RIGHT_ALIGNMENT); // Right justify in panel
		assignPanel.setAlignmentX(BOTTOM_ALIGNMENT); // Bottom justify
		super.add(classNamePanel);
		classNamePanel.add(className);
		classNamePanel.add(addButton);
		super.add(assignPanel);
		isRemoved = false;
	}
	
	// Return assignments
	public ArrayList<Assignment> getAssignments(){
		return assignments;
	}
	
	public void setClassName(String name){
		classNameStr = name;
	}
	
	public void setClassNameLabel(String name) {
		classNameStr = name;
		className.setText(name);
	}
	
	public String getClassName(){
		return classNameStr;
	}
	
	public boolean isRemoved(){
		return isRemoved;
	}
	
	public void setIsRemoved(boolean b){
		isRemoved = b;
	}
	
	// Add assignments
	public void addAssignment(String a, String d){
		Assignment assign = new Assignment(a, d);
		assignments.add(assign); // Add to list
		assignPanel.add(assign);
		refreshGUI();
		if(OpenFile.doneLoading)
			SaveFile.saveFile(this, assign);
	}
	// Deletes assignment and corrosponding panel
	void delAssignments(){
		for(Assignment assign : assignments){
			assign.setIsRemoved(true);
			assign.setVisible(false);
		}
	}
	
	void refreshGUI(){
		// Refresh gui
		this.repaint();
		SwingUtilities.getWindowAncestor(this).pack();
	}
	
	public String toString(){
		return classNameStr;
	}
	
	
	// Listener for add button
	class AddListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new AddAssignment();
			
		}
	}
	// Add assinment window
	class AddAssignment{
		private JFrame frame; // Main frame
		private JLabel label; // Label for assignment
		private JLabel labelDate; // Label for due date
		private JPanel center; // Center panel
		JTextField textBox; // Class name text box
		JTextField textBoxDate; // Class name text box
		private JPanel panel4; // Panel for last grid
		private JButton addButton; // Adds a class
		private JButton cancelButton; // Cancels action
		
		public AddAssignment(){
			frame = new JFrame("Add Assignment");
			frame.setLayout(new BorderLayout(5, 5));
			frame.setVisible(true);
			center = new JPanel(new GridLayout(5, 1, 0, 5));
			frame.add(center, BorderLayout.CENTER);
			
			init();
			
			// Fill spaces
			frame.add(new JPanel(), BorderLayout.NORTH);
			frame.add(new JPanel(), BorderLayout.SOUTH);
			frame.add(new JPanel(), BorderLayout.EAST);
			frame.add(new JPanel(), BorderLayout.WEST);
			
			frame.pack();
		}
		
		//  Add window objects
		private void init() {
			// Grid #1
			label = new JLabel("Assignment Name:");
			label.setHorizontalAlignment(JLabel.LEFT);
			center.add(label);
			
			// Grid #2
			textBox = new JTextField();
			center.add(textBox);
			
			// Grid #3
			labelDate = new JLabel("Date Due:");
			labelDate.setHorizontalAlignment(JLabel.LEFT);
			center.add(labelDate);
			
			// Grid #4
			textBoxDate = new JTextField();
			center.add(textBoxDate);
			
			// Grid #5
			panel4 = new JPanel(new GridLayout(1, 4));
			center.add(panel4);
			addButton = new JButton("Add");
			cancelButton = new JButton("Cancel");
			panel4.add(new JPanel()); // Fill space
			panel4.add(new JPanel()); // Fill space
			panel4.add(cancelButton);
			panel4.add(addButton);
			cancelButton.addActionListener(new CancelListener());
			addButton.addActionListener(new AddListener());
		}
		
		// Listerner for cancel button
		class CancelListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)); // Closes the window
			}
			
		}
		
		// Listener for add button
		class AddListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				addAssignment(textBox.getText(), textBoxDate.getText()); // Add assignment from text box
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)); // Closes the window
				
			}
		}
	}
}
