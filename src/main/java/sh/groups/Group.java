package sh.groups;

import sh.students.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Group implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Student> students;
    private String faculty;
    private String groupNumber;

    public Group() {
        students = new ArrayList<Student>();
    }

    public Group(String faculty, String groupNumber) {
        students = new ArrayList<Student>();
        this.faculty = faculty;
        this.groupNumber = groupNumber;
    }

    public void addStudent(Student stud) {
        students.add(stud);
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public String getGroup() {
        return groupNumber;
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }
}
