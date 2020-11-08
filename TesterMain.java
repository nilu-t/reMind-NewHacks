package tester;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TesterMain {

	public static void main(String[] args) {
	//This includes the main method and everything
	 
	 JFrame mainFrame = new JFrame("reMind");
	 
	 JButton initiateSchedule = new JButton(new ImageIcon("/Users/Nilu/eclipse-workspace/ReMind/src/schooltimetables.JPG"));
	 initiateSchedule.setIcon(new ImageIcon(new ImageIcon("/Users/Nilu/eclipse-workspace/ReMind/src/schooltimetables.JPG").getImage().getScaledInstance(650, 650,java.awt.Image.SCALE_DEFAULT)));
	
	 ToDoList todolist = new ToDoList(mainFrame);
     
     // The title of the program is reMind
     JLabel reMind = new JLabel("reMind "); 
     
     //the motivation class
     Motivation quote = new Motivation();
     
     
	 //all panels
	 JPanel panel = new JPanel();
	 JPanel panelTwo = new JPanel();
	 JPanel panelThree = new JPanel();
	 JPanel panelFour  = new JPanel(); 

     reMind.setFont(new java.awt.Font("Arial", Font.ITALIC, 105));
     reMind.setOpaque(false);
	 reMind.setForeground(Color.BLACK);
	 reMind.setVisible(true);  
		

	 panelThree.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
	 panelThree.setPreferredSize(new Dimension(500,100));
	 panelThree.setBackground(new Color(42,250,250)); 
	 

	 panelTwo.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));

	 panelTwo.setBackground(new Color(42,250,250)); 
	 panelTwo.setPreferredSize(new Dimension(1025,100));
	 

	 panelFour.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
	 panelFour.setPreferredSize(new Dimension(1000,100));
	 panelFour.setBackground(new Color(0,233,204)); 
	 
	 //panel add the components
	 panelThree.add(todolist);
	 panelFour.add(initiateSchedule);
	 panelTwo.add(reMind);
	 panelFour.add(quote); 
	 
	 //action listener for the temporary fish exchange button 
	 initiateSchedule.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e) {
        	  if(e.getSource()==initiateSchedule) {

        	       scheduleMain courseSchedule = new scheduleMain();
        	  }
          }
	 });
	 
	 //add the panels to the mainFrame
	 panelTwo.setVisible(true);
	 panelThree.setVisible(true);
	 panelFour.setVisible(true);
	 
     //borders for all the panels
     panelTwo.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
     panelThree.setBorder(BorderFactory.createLineBorder(new Color (0,0,0)));
     panelFour.setBorder(BorderFactory.createLineBorder(new Color (0,0,0)));
     
	 //add the panels to the frame
	 mainFrame.add(panelTwo, BorderLayout.SOUTH);
	 mainFrame.add(panelThree, BorderLayout.EAST); 
	 mainFrame.add(panelFour,BorderLayout.WEST); 

	 mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	 mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	 mainFrame.setUndecorated(false);
	 mainFrame.setResizable(false);
	 mainFrame.setVisible(true);	
	 mainFrame.pack();
	 mainFrame.repaint();
	 mainFrame.setVisible(true);
	 
	 
	}

}
