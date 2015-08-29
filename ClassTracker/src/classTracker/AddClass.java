package classTracker;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classTracker.New.CancelListener;

// Adds a class
public class AddClass {
	
	private JFrame frame; // Main frame
	private JLabel label; // Label for class
	private JPanel center; // Center panel
	JTextField textBox; // Class name text box
	private JPanel panel3; // Panel for last grid
	private JButton addButton; // Adds a class
	private JButton cancelButton; // Cancels action
	
	public AddClass(){
		frame = new JFrame("Add a Class");
		frame.setLayout(new BorderLayout(5, 5));
		frame.setVisible(true);
		center = new JPanel(new GridLayout(3, 1, 0, 5));
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
		label = new JLabel("Class Name:");
		label.setHorizontalAlignment(JLabel.LEFT);
		center.add(label);
		
		// Grid #2
		textBox = new JTextField();
		center.add(textBox);
		
		// Grid #3
		panel3 = new JPanel(new GridLayout(1, 4));
		center.add(panel3);
		addButton = new JButton("Add");
		cancelButton = new JButton("Cancel");
		panel3.add(new JPanel()); // Fill space
		panel3.add(new JPanel()); // Fill space
		panel3.add(cancelButton);
		panel3.add(addButton);
		cancelButton.addActionListener(new CancelListener());
		addButton.addActionListener(new AddListener());
	}
	
	// Checks if class is valid
	private boolean isValidClass(){
		boolean b = true;
		for(SchoolClass c : MainWindow.canvas.getClasses()){
			if(c.getClassName().equals(textBox.getText())){
				b = false;
			}
		}
		
		return b;
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
			
			if(isValidClass()){
				MainWindow.canvas.addClass(textBox.getText());
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)); // Closes the window
			}
			
		}
	}
}
