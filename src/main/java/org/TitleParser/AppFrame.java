package org.TitleParser;

/**
 * Creates the application window for the user and displays new title
 */
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppFrame extends MethodHandler {

  //Window Variables
  static JTextField title = new JTextField();
  static JTextArea description = new JTextArea();
  static String prodTitle, descr;
  static String[] prodTitleArr, descrArr;

  static JLabel output;

  
  public void createWindow() {
	  final int FRAME_WIDTH = 500;
	  final int FRAME_HEIGHT = 500;

	  JLabel labelTitle = new JLabel("Product Title");

	  title.setBounds(93,121,89,23);
	  title.setColumns(15);

	  description.setColumns(35);
	  description.setRows(15);
	    
	  JButton button = new JButton("Parse Title");
	  
	  button.addActionListener(new ActionListener () {
		  
		  public void actionPerformed(ActionEvent arg0) {
			  
			  //Split title and description into individual words
			  prodTitle = title.getText();
			  prodTitleArr = prodTitle.split(" ");

			  descr = description.getText();
			  descrArr = descr.split(" ");

			  //Search database for known titles
			  MethodHandler.searchDatabase();
		  }
	    });

	  //Add items to panel
	  JPanel panel = new JPanel();
	  panel.add(labelTitle);
	  panel.add(title);
	  panel.add (description);
	  panel.add(button);

	  //Add panel to frame and set size
	  JFrame frame = new JFrame();
	  frame.add(panel);
	  frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	  frame.setTitle("Title Parser");
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  
	  //Set frame to the centre of the screen
	  Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	  frame.setLocation(dim.width/2 - FRAME_WIDTH/2, dim.height/2 - FRAME_HEIGHT/2);
	  
	  frame.setVisible(true);
  }
}