package tester;

public class CourseList{
	Course[] courses = new Course[0];
	
	public CourseList(Course[] courses) {
		this.courses = courses;
	}
	
	public void addCourse(Course course) {
		Course[] newCourseList = new Course[this.courses.length + 1];
		for (int i = 0; i < this.courses.length; i++) {
			newCourseList[i] = this.courses[i];
		}
		int length = newCourseList.length;
		newCourseList[length-1] = course;
		this.courses = newCourseList;
	}
	
	public void removeCourse(Course course) {
		Course[] newCourseList = new Course[this.courses.length - 1];
		for (int i = 0,k = 0; i < this.courses.length; i++) {
			if(i == course.index) {
				continue;
			}
			newCourseList[k++] = this.courses[i];
		}
		this.courses = newCourseList;
	}
	
	public String toString() {
		return courses.toString();
	}

}
