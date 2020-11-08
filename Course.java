package tester;

public class Course {

String name;
int index;
Assignment[] assignment = new Assignment[0];

public Course(String name, int index){
    this.name = name;
    this.index = index;
}

public void addAssignment(Assignment assignment) {
    Assignment[] newList = new Assignment[this.assignment.length + 1];
    for (int i = 0; i < this.assignment.length; i++) {
    newList[i] = this.assignment[i];
    }
    int length = newList.length;
    newList[length-1] = assignment; //Assigns new item to the last element of newItemArray
    this.assignment = newList;
}

public void removeAssignment(Assignment assignment){
    int ind = -1;
	Assignment[] newList = new Assignment[this.assignment.length - 1];
	for (int i = 0; i < this.assignment.length; i++) {
	if(this.assignment[i] == assignment) {
	ind = i;
    }
}
for (int i = 0, k = 0; i < this.assignment.length; i++) {
	if(i == ind) {
	continue;
	}
    newList[k++] = this.assignment[i];
}
    this.assignment = newList;
}

public String toString() {
    return name;
}


}
