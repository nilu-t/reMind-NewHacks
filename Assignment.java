package tester;

import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

public class Assignment{

String name;
Course courseName;
String dueDate;
int year;
int month;
int day;
int hour;
int minute;
String difficultyLevel;

public Assignment(String name, Course courseName) {
this.courseName = courseName;
this.name = name;
}

public Assignment(String name, Course courseName, String dueDate) {
this.courseName = courseName;
this.name = name;
this.dueDate = dueDate;
}

public Assignment(String name, Course courseName, String dueDate, String difficulty) {
this.courseName = courseName;
this.name = name;
this.dueDate = dueDate;
this.difficultyLevel = difficulty;
}

public void timeRemaining() {

LocalDateTime now = LocalDateTime.now();
   System.out.println("Before formatting: " + now);
   DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

   String formattedDate = now.format(myFormatObj);
   System.out.println("After formatting: " + formattedDate);
}

public void setYear(int year) {
this.year = year;
}

public void setMonth(int month) {
this.month = month;
}

public void setDay(int day) {
this.day = day;
}

public void setHour(int hour) {
this.hour = hour;
}

public void setMins(int mins) {
this.minute = mins;
}

public String toString() {
return name;
}

}



