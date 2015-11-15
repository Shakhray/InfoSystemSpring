package sh.students;

import java.io.Serializable;


public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    private String surname, name, secondName;
    private String studentID, groupNumber;
    private String dateOfTransfer;

    private String faculty;

    public Student(String surname, String name, String secondName,
                   String faculty, String groupNumber, String studentID, String dateOfTransfer) {
        this.surname = surname;
        this.name = name;
        this.secondName = secondName;
        this.groupNumber = groupNumber;
        this.studentID = studentID;
        this.dateOfTransfer = dateOfTransfer;
        this.faculty = faculty;
    }

    public String getSurname() {
        return surname;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getName() {
        return name;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getDateOfTransfer() {
        return dateOfTransfer;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Student)) {
            return false;
        } else {
            Student stud = (Student) obj;
            return stud.getSurname().equals(surname) &&
                    stud.getName().equals(name) &&
                    stud.getSecondName().equals(secondName) &&
                    stud.getGroupNumber().equals(groupNumber) &&
                    stud.getStudentID().equals(studentID) &&
                    stud.getDateOfTransfer().equals(dateOfTransfer) &&
                    stud.getFaculty().equals(faculty);
        }
    }

    public String getFaculty() {
        return faculty;
    }

    public String getFieldByIndex(int indexOfField) throws Exception {
        switch (indexOfField) {
            case 0:
                return getSurname();
            case 1:
                return getName();
            case 2:
                return getSecondName();
            case 3:
                return getFaculty();
            case 4:
                return getGroupNumber();
            case 5:
                return getStudentID();
            case 6:
                return getDateOfTransfer();
            default:
                throw new Exception();
        }
    }
}
