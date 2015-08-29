package classTracker;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class New {

	private JFrame frame;	
	private JPanel contentPane;
	private JTextField txtDdd;
	private JTextField widthText;
	private JTextField heightText;
	private JButton cancel;
	private JButton okay;
	public final int windowHeight = 450;
	public final int windowWidth = 250;
	private double mainWindowX;
	private double mainWindowY;
	private JPanel fileNamePane;
	private JPanel windowSizePane;
	

	
	public New(double x, double y) {
		mainWindowX = x;
		mainWindowY = y;
		windowSetup();
	}
	
	private void windowSetup(){
		
		frame = new JFrame("New File");
		frame.setLayout(new BorderLayout());
		
		// Panes
		contentPane = new JPanel(new BorderLayout());
		fileNamePane = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Left align with flow layout, north of content
		windowSizePane = new JPanel(new GridLayout(3,3)); // Center of content
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.add(contentPane, BorderLayout.CENTER);
		
		// Label for file name
		JLabel lblNewLabel = new JLabel("File Name:");
		contentPane.add(fileNamePane, BorderLayout.NORTH); // Pane for file name area
		fileNamePane.add(lblNewLabel);
		
		// Text field for file name
		txtDdd = new JTextField();
		txtDdd.setText("Untitled");
		fileNamePane.add(txtDdd);
		txtDdd.setColumns(10);
		
		// Size selections for new file
		contentPane.add(windowSizePane, BorderLayout.CENTER);
		JLabel sizeLabel = new JLabel("Set Canvas Size:");
		windowSizePane.add(sizeLabel);
		windowSizePane.add(new JPanel()); // Make empty space
		// Width
		JLabel widthLabel = new JLabel("Width");
		windowSizePane.add(widthLabel);
		// Width Text
		widthText = new JTextField();
		widthText.setText("1200");
		windowSizePane.add(widthText);
		// Height
		JLabel heightLabel = new JLabel("Height");
		windowSizePane.add(heightLabel);
		// Height Text
		heightText = new JTextField();
		heightText.setText("780");
		windowSizePane.add(heightText);
		
		buttons();
		
		
		frame.setVisible(true);
		frame.pack();
	}
	
	// Generates buttons for window
	private void buttons() {
		
		JPanel southPanel = new JPanel();
		frame.add(southPanel, BorderLayout.SOUTH);
		cancel = new JButton("Cancel");
		southPanel.add(cancel);
		cancel.addActionListener(new CancelListener());
		okay = new JButton("OK");
		southPanel.add(okay);
		okay.addActionListener(new OKListener());
		
	}
	// Listerner for cancel button
	class CancelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)); // Closes the window
		}
		
	}
	// Listener for okay button
	class OKListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
