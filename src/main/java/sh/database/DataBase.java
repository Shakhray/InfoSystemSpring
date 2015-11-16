package sh.database;

import sh.groups.Group;
import sh.students.Student;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class DataBase implements Serializable {
    private static final long serialVersionUID = 1L;
    private LinkedList<Student> students = new LinkedList<Student>();
    private LinkedList<Group> groups = new LinkedList<Group>();

    public LinkedList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student stud) {
        students.add(stud);
    }

    /*public void addStudentsToGroups(){
        Group group = new Group();
        for(Student stud: sh.students){

        }
    }*/
    public void addDefault() {
        addStudent(new Student("Shakhray", "Alexander", "Sergeevitch", "mex-mat", "414", "20096305", "20.05.1992"));
        addStudent(new Student("Odinokov", "Nikolay", "Nikolaevitch", "mex-mat", "414", "20096201", "29.03.1992"));
        addStudent(new Student("Volkov", "Dmitriy", "Vadimovitch", "mex-mat", "411", "20096655", "26.06.1992"));
    }

    public void remove(Student stud) {
        students.remove(stud);

    }

    @Override
    public DataBase clone() throws CloneNotSupportedException {
        super.clone();
        DataBase clonedDB = new DataBase();
        List<Student> students = getStudents();
        for (Student stud : students)
            clonedDB.addStudent(stud);
        return clonedDB;
    }

    public Collection<Group> getGroups() {
        return groups;
    }
}