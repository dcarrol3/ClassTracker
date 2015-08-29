package classTracker;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class MainWindow {
	
	private JFrame frame;
	JLabel status;
	JMenuBar menuBar;
	static Canvas canvas;
	
	// Getter for screen width
	public static int getScreenWorkingWidth() {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}
	// Getter for screen height
	public static int getScreenWorkingHeight() {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}
	
	public double getWindowX(){
		return frame.getLocationOnScreen().getX();
	}
	
	public double getWindowY(){
		return frame.getLocationOnScreen().getY();
	}

	
	
	public MainWindow() {
		
		// Shut down hook
		Runtime.getRuntime().addShutdownHook(
		  new Thread() {
		    @Override 
		    public void run() { 
		      SaveFile.checkForDeleted();
		      System.out.println("bye");
		    }
		});
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Class Tracker");
		// Sets window bounds and positions in center of screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		// Adds menu bar (#1)
		menuInt();
		// Adds center peice
		centerInt();
		// Adds status bar
		statusBar();
		
		frame.pack();
		frame.setVisible(true);
		
		load();
		
	}
	
	
	private void centerInt() {
		canvas = new Canvas();
		frame.add(canvas);
		
	}
	// Produces the status bar
	private void statusBar() {
		
		status = new JLabel("status");
		frame.add(status, BorderLayout.SOUTH);
		
	}
	// Initializes the menu bar and it's objects
	private void menuInt(){
		
		menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		// Edit
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		editInt(mnEdit);
		
		
	}
	// Initializes the file menu
	private void fileInt(JMenu mnFile){
		
	}
	
	// Initializes the edit menu
	private void editInt(JMenu mnEdit){
		
		// Add Class...
		JMenuItem addClass = new JMenuItem(new AbstractAction("Add Class..."){
			@Override
			public void actionPerformed(ActionEvent e) {
				// Action when pressed
				new AddClass();
			};
		});
		mnEdit.add(addClass);
		
		// Remove Class...
		JMenuItem removeClass = new JMenuItem(new AbstractAction("Remove Class..."){
			@Override
			public void actionPerformed(ActionEvent e) {
				// Action when pressed
				new RemoveClass();
			};
		});
		mnEdit.add(removeClass);
	}
	
	private boolean exitCheck(){
		boolean bool = false;
		int result = JOptionPane.showConfirmDialog(frame,"Would you like to save the current file before exiting?","Exit?",JOptionPane.YES_NO_CANCEL_OPTION);
        switch(result){
            case JOptionPane.YES_OPTION:
                bool = true;
                new SaveFile(); // Save current file
                break;
            case JOptionPane.NO_OPTION:
                bool = true; // Just exit
                break;
            case JOptionPane.CLOSED_OPTION:
                bool = false; // Do not exit
                break;
            case JOptionPane.CANCEL_OPTION:
                bool = false; // Do not exit
                break;
        }
        return bool;
	}
	
	private void load(){
		OpenFile.openFile();
	}
}
