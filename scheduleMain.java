package tester;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class scheduleMain extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	{
	Scanner input = new Scanner(System.in);

int courseIndex = -1;
Course[] list = new Course[0];
CourseList courseList = new CourseList(list);

JFrame frame = new JFrame();
frame.getContentPane().setBackground(Color.BLACK);
frame.setBounds(50, 50, 1200, 675);
//frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
frame.getContentPane().setLayout(null);

JPanel panel_1 = new JPanel();
panel_1.setLayout(null);
panel_1.setBackground(Color.DARK_GRAY);
panel_1.setBounds(10, 11, 883, 614);
frame.getContentPane().add(panel_1);

JLabel lblTodoList_1 = new JLabel("Courses");
lblTodoList_1.setHorizontalAlignment(SwingConstants.CENTER);
lblTodoList_1.setForeground(Color.WHITE);
lblTodoList_1.setFont(new Font("Segoe Print", Font.BOLD, 15));
lblTodoList_1.setBounds(10, 11, 863, 14);
panel_1.add(lblTodoList_1);

JLabel lblAddItem_1 = new JLabel("Add Course");
lblAddItem_1.setForeground(Color.WHITE);
lblAddItem_1.setFont(new Font("Tahoma", Font.BOLD, 14));
lblAddItem_1.setBounds(196, 554, 92, 22);
panel_1.add(lblAddItem_1);

JTextPane textField = new JTextPane();
textField.setFont(new Font("MV Boli", Font.BOLD, 15));
textField.setEditable(false);
textField.setBackground(Color.LIGHT_GRAY);
textField.setBounds(10, 36, 863, 507);
panel_1.add(textField);


JButton button_1 = new JButton("+");
button_1.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
Course course = createCourse();
courseList.addCourse(course);
writeFile(courseList);
String str = readFile();
textField.setText(str);
}
});
button_1.setBounds(284, 556, 41, 22);
panel_1.add(button_1);

JLabel lblAddItem_1_1 = new JLabel("Add Assignment");
lblAddItem_1_1.setForeground(Color.WHITE);
lblAddItem_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
lblAddItem_1_1.setBounds(528, 550, 114, 22);
panel_1.add(lblAddItem_1_1);

JButton button_1_1 = new JButton("+");
button_1_1.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {

Assignment assignment = createAssignment(courseList);

for(int i = 0; i < courseList.courses.length; i++) {
if(assignment.courseName == courseList.courses[i]) {
courseList.courses[i].addAssignment(assignment);
}
}
writeFile(courseList);
String str = readFile();
textField.setText(str);
}
});
button_1_1.setBounds(652, 552, 41, 22);
panel_1.add(button_1_1);

JLabel lblAddItem_1_2 = new JLabel("Remove Course");
lblAddItem_1_2.setForeground(Color.WHITE);
lblAddItem_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
lblAddItem_1_2.setBounds(160, 579, 114, 22);
panel_1.add(lblAddItem_1_2);

JButton button_1_2 = new JButton("-");
button_1_2.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
removeCourse(courseList);
writeFile(courseList);
String str = readFile();
textField.setText(str);
}
});
button_1_2.setBounds(284, 581, 41, 22);
panel_1.add(button_1_2);

JLabel lblAddItem_1_1_5 = new JLabel("Remove Assignment");
lblAddItem_1_1_5.setForeground(Color.WHITE);
lblAddItem_1_1_5.setFont(new Font("Tahoma", Font.BOLD, 14));
lblAddItem_1_1_5.setBounds(500, 579, 142, 22);
panel_1.add(lblAddItem_1_1_5);

JButton button_1_1_2 = new JButton("-");
button_1_1_2.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
removeAssignment(courseList);
writeFile(courseList);
String str = readFile();
textField.setText(str);
}
});
button_1_1_2.setBounds(652, 579, 41, 22);
panel_1.add(button_1_1_2);

   frame.add(panel_1);
   frame.setVisible(true);
   
   String str = readFile();
textField.setText(str);
}

public static void writeFile(CourseList courseList) {
try {
BufferedWriter bw = new BufferedWriter(
new FileWriter("/Users/Nilu/eclipse-workspace/ReMind/src/courses.txt"));
for(int i =0; i < courseList.courses.length; i++) {
bw.write(courseList.courses[i].toString());
bw.newLine();
for(int j = 0; j< courseList.courses[i].assignment.length; j++) {
if(courseList.courses[i].assignment[j] != null) {
bw.write("\t"+courseList.courses[i].assignment[j]+"\t\t\t"+courseList.courses[i].assignment[j].dueDate);
bw.newLine();
}
}
}
bw.close();

}
catch(Exception ex){
return;
}
}

public static String readFile() {
try {
BufferedReader br = new BufferedReader(new FileReader("/Users/Nilu/eclipse-workspace/ReMind/src/courses.txt"));
String s;
String line = "";
while((s = br.readLine()) != null){
line = line+"\n"+s;
}
return line;
}

catch(Exception ex){
return "error";
}
}

public static int returnLineNum(CourseList courseList, String searchItem) {
try {
BufferedReader br = new BufferedReader(new FileReader("/Users/Nilu/eclipse-workspace/ReMind/src/courses.txt"));
String s;
int lineNum = 0;
int i = 1;
while((s = br.readLine()) != null){
if (s.contains(searchItem)) {
lineNum = i;
}
i++;
}
return lineNum;
}

catch(Exception ex){
return 0;
}
}

public static int courseIndex = -1;
public static Course createCourse() {
JTextField courseName = new JTextField();
int in = JOptionPane.showConfirmDialog(null,courseName,"Add Course",JOptionPane.OK_CANCEL_OPTION);

Course newCourse = new Course("",0);

if(in == JOptionPane.OK_OPTION) {
String courseNam = courseName.getText();
newCourse = new Course(courseNam,++courseIndex);
courseIndex++;
}
return newCourse;
}

public static Assignment createAssignment(CourseList courseList) {

JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "hh:mm aa");
timeSpinner.setEditor(timeEditor);
timeSpinner.setValue(new Date());

JSpinner dateSpinner = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.MONTH));
JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
dateSpinner.setEditor(dateEditor);
dateSpinner.setValue(new Date());
   
   JComboBox courseSelect = new JComboBox(courseList.courses);
   JTextField assignmentName = new JTextField();
   String[] difficultyLevels = {"Easy: 1-2 hours","Medium: 4-6 hours","Hard: 8-10 hours","Impossible: 12+ hours"};
   JComboBox difficulty = new JComboBox(difficultyLevels);
   
   Object [] fields = {
           "Course", courseSelect,
           "Assignment", assignmentName,
           "Due Date", dateSpinner,
           "Time", timeSpinner,
           "Difficulty", difficulty
       };
   
   Assignment newAssignment = new Assignment(null,null);
   
   int in = JOptionPane.showConfirmDialog(null,fields,"Add Assignment",JOptionPane.OK_CANCEL_OPTION);
   if(in == JOptionPane.OK_OPTION) {
    Course courseNam = (Course) courseSelect.getSelectedItem();
    String assignNam = assignmentName.getText();

    DateFormat df = new SimpleDateFormat("EEE/dd/MM/yyyy");
   String dueDate = df.format(dateSpinner.getValue());
   
   DateFormat tf = new SimpleDateFormat("hh:mm aa");
    String dueTime = tf.format(timeSpinner.getValue());
    String due = dueDate + " @ "+dueTime;
    String difficultyLev = (String) difficulty.getSelectedItem();
   
    newAssignment = new Assignment(assignNam,courseNam,due,difficultyLev);
   
   }
   return newAssignment;
 
}

public static void removeCourse(CourseList courseList) {
if(courseList.courses.length>0) {
JComboBox courseSelect = new JComboBox(courseList.courses);
int in = JOptionPane.showConfirmDialog(null,courseSelect,"Remove Course",JOptionPane.OK_CANCEL_OPTION);
if(in == JOptionPane.OK_OPTION) {
Course courseNam = (Course) courseSelect.getSelectedItem();
courseList.removeCourse(courseNam);
}
else {int input = JOptionPane.showConfirmDialog(null,null,"Invalid Response",JOptionPane.OK_CANCEL_OPTION);
}
}
}

public static void removeAssignment(CourseList courseList) {
if(courseList.courses.length>0) {
	JComboBox courseSelect = new JComboBox(courseList.courses);
	int in = JOptionPane.showConfirmDialog(null,courseSelect,"Select Assignment Course",JOptionPane.OK_CANCEL_OPTION);
if(in == JOptionPane.OK_OPTION) {
	Course courseNam = (Course) courseSelect.getSelectedItem();
if(courseNam.assignment.length > 0) {
	JComboBox assignmentSelect = new JComboBox(courseNam.assignment);
	int input = JOptionPane.showConfirmDialog(null,assignmentSelect,"Remove Assignment",JOptionPane.OK_CANCEL_OPTION);
if(input == JOptionPane.OK_OPTION) {
    Assignment assign = (Assignment) assignmentSelect.getSelectedItem();
if(assign.courseName == courseNam) {
    courseNam.removeAssignment(assign);
    }
   }
}
else { int input = JOptionPane.showConfirmDialog(null,null,"Invalid Response",JOptionPane.OK_CANCEL_OPTION);
    }
  }
}
 else {int input = JOptionPane.showConfirmDialog(null,null,"Invalid Response",JOptionPane.OK_CANCEL_OPTION);
}


}

}
