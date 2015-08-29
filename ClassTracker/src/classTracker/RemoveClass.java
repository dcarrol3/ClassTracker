package classTracker;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;

// Removes a class
public class RemoveClass {
	
	JFrame frame = new JFrame("Remove a Class");
	JPanel mainPanel = new JPanel();
	JComboBox combo = new JComboBox();
	JButton button = new JButton("Remove");
	
	public RemoveClass(){
	
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		// Fill spaces
		frame.add(new JPanel(), BorderLayout.NORTH);
		frame.add(new JPanel(), BorderLayout.SOUTH);
		frame.add(new JPanel(), BorderLayout.EAST);
		frame.add(new JPanel(), BorderLayout.WEST);
		
		// Main panel
		mainPanel.setLayout(new FlowLayout(0, 20, 10));
		frame.add(mainPanel, BorderLayout.CENTER);
		
		// Combo Box
		// Fill with current class names
		for(SchoolClass c : MainWindow.canvas.getClasses()){
			if(!(c.isRemoved()))
				combo.addItem(c);
		}
		
		mainPanel.add(combo);
		
		// Button
		button.addActionListener(new RemoveListener());
		mainPanel.add(button);
		
		frame.pack();
	}
	
	// Handles removing a class - ADD TRY/CATCH
	private void removeClass(){
		SchoolClass c = (SchoolClass) combo.getSelectedItem();
		c.delAssignments(); // Remove all of it's assignments
		// Kill class
		c.setVisible(false);
		c.setIsRemoved(true);
		
		// Repack
		frame.repaint();
		SwingUtilities.getWindowAncestor(MainWindow.canvas).pack();
	}
	
	// Listener for add button
	class RemoveListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			removeClass();
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)); // Closes the window
			
		}
	}
	
}
