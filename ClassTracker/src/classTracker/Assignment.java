package classTracker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

// Handles assignment for a class
public class Assignment extends JPanel implements Serializable {
	private String assign = "";
	private JButton remove; // Button to remove this panel 
	private JLabel label;
	private boolean isRemoved; // If this assignment has been deleted or not
	
	public Assignment(String a, String date){
		super();
		assign = a + "" + date;
		label = new JLabel(assign);
		isRemoved = false;
		
		super.add(label);
		button();
	}
	
	public String getAssignment(){
		return assign;
	}
	// Is assignment removed?
	public boolean isRemoved(){
		return isRemoved;
	}
	
	public void setIsRemoved(boolean b) {
		isRemoved = b;
	}
	
	// Creates remove button
	private void button(){
		remove = new JButton("-");
		super.add(remove);
		remove.addActionListener(new RemListener());
		
	}
	
	private void removeMe(){
		this.setVisible(false);
		isRemoved = true;
		//refresh gui
		this.repaint();
		SwingUtilities.getWindowAncestor(this).pack();
	}
	
	// Listerner for - button
	class RemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
            removeMe();
		}
		
	}
}
