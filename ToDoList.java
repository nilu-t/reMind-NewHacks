package tester;
//packages for swing/gui
import javax.swing.*;

import java.awt.event.*;
import java.awt.Container;
import java.awt.Component;
//packages for file io
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class ToDoList extends JPanel{
 final private String header = "To-Do List";
 private JFrame frame;
 private JPanel itemListPanel = new JPanel();
 private JPanel buttonPanel = new JPanel();

 public ToDoList(JFrame frame) {
     // setting this frame
     this.frame = frame;

     // setting the layout for the whole panel
     BoxLayout listBoxLayoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
     this.setLayout(listBoxLayoutManager);
     // setting the layout for the panel of list of items
     BoxLayout itemsBoxLayoutManager = new BoxLayout(itemListPanel, BoxLayout.Y_AXIS);
     itemListPanel.setLayout(itemsBoxLayoutManager);


     // adding all the labels
     addALabel(header, this);
     readFileContent();                  // adding the items of list as labels

     // defining the add item button
     JButton addButton = new JButton("Add item");
     addButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
            addItem();
        }
     });

     // defining the remove item button
     JButton removeButton = new JButton("Remove item");
     removeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            removeItem();
        }
     });
     // making the item list a scrollable panel
     JScrollPane scrollPane = new JScrollPane(itemListPanel);
     // adding the scrollable item panel
     this.add(scrollPane);
     // adding the buttons to the button panel
     buttonPanel.add(addButton);
     buttonPanel.add(removeButton);
     // adding the button panel to the ToDoList panel
     this.add(buttonPanel);


 }

 private void readFileContent() {
     ArrayList<String> lines = new ArrayList<String>();
     try {
         Scanner fileInput = new Scanner( new File("toDoList.txt"));
         // reading through each line in file
         while (fileInput.hasNext()) {
             // adding line to list
             lines.add(fileInput.nextLine());
         }
         fileInput.close();
     } catch (IOException ioe) {
         ;
     }
     // iterates through each line of string and creates labels for each
     for (String line : lines) {
         addALabel(line, itemListPanel);
     }
 }

 public void writeList(String line) {
     try {
         FileWriter appendFile = new FileWriter("toDoList.txt", true);
         PrintWriter fileOutput = new PrintWriter(appendFile);
         // appends the line to the file
         fileOutput.println(line);
         fileOutput.close();
     } catch (IOException ioe) {
         ;
     }
 }

 public void addALabel(String text, Container container) {
     JLabel label = new JLabel(text);                    // creating new label
     label.setAlignmentX(Component.CENTER_ALIGNMENT);    // centering
     container.add(label);                               // adding label to container
 }

 public void addItem() {
     // opens up a dialog window, prompts user for item name, and adds it as a label
     String name = JOptionPane.showInputDialog(frame, "Enter to-do item:", "Adding To-Do List Item", JOptionPane.INFORMATION_MESSAGE);
     this.writeList(name);           // writing their input to a file
     // refreshing the list display
     itemListPanel.removeAll();
     readFileContent();
     this.frame.setVisible(true);
 }

 public void removeItem() {
     int itemNum;
     String message = "Enter item number to delete:";
     ArrayList<String> lines = new ArrayList<String>();
     boolean run = true;
     // reading the file for all of the lines
     try {
         Scanner fileInput = new Scanner( new File("toDoList.txt"));
         // reading through each line in file
         while (fileInput.hasNext()) {
             // adding line to list
             lines.add(fileInput.nextLine());
         }
         fileInput.close();
     } catch (IOException ioe) {
         ;
     }
     // if file is empty
     if (lines.size() == 0) {
         run = false;
         JOptionPane.showMessageDialog(frame, "There are no items to delete.");
     }
     // error checking for item number
     while (run) {   // will loop until they enter a valid index number
         try {
             itemNum = Integer.parseInt(JOptionPane.showInputDialog(frame, message, "Removing To-Do List Item", JOptionPane.INFORMATION_MESSAGE));
             if (itemNum <= lines.size() && itemNum >= 1) {  // if they entered a valid index for the array
                 lines.remove(itemNum-1);
                 break;
             }
             message = "Enter item number in range to delete:";
         } catch (NumberFormatException nfe) {   // if they don't enter a number
             message = "Enter valid item number to delete";;
         }
     }

     // rewriting the new file after removing item
     try {
         PrintWriter fileOutput = new PrintWriter("toDoList.txt");
         // writing all the lines back
         for (String line : lines) {
             fileOutput.println(line);
         }
         fileOutput.close();
         // refreshing the list display
         itemListPanel.removeAll();
         readFileContent();
         this.frame.setVisible(true);
     } catch (IOException ioe) {
         
     };

 }
}