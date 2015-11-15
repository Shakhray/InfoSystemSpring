package sh.viewers;

import sh.groups.Group;
import sh.students.Student;

import java.util.Collection;

public class Viewer {
    public <E> void printCollection(Collection<E> collect) {
        int index = 0;
        for (E element : collect) {
            if (isGroup(element))
                printGroup((Group) element, ++index);
            else
                printStudent((Student) element, ++index);
        }
    }

    private <E> boolean isGroup(E element) {
        return Group.class.equals(element.getClass());
    }

    private void printGroup(Group group, int index) {
        println("\t" + index + ". " + group.getFaculty() + " " + group.getGroup());
    }

    private void printStudent(Student student, int index) {
        println("\t" + index + ". " + student.getSurname() + " " + student.getName() + " " +
                student.getSecondName() + " " + student.getFaculty() + " " +
                student.getGroupNumber() + " " + student.getStudentID() + " " +
                student.getDateOfTransfer());
    }

    public String masToString(String[] mas) {
        StringBuilder s = new StringBuilder();
        for (String st : mas)
            s.append(st).append(" ");
        return s.toString();
    }

    public void print(Student stud) {
        println("\t" + stud.getSurname() + " " + stud.getName() + " " + stud.getSecondName() + " " + stud.getFaculty() + " " + stud.getGroupNumber() + " " + stud.getStudentID() + " " + stud.getDateOfTransfer());
    }

    public void comandLine() {
        print("\nInfoSys> ");
    }

    public void update(int i) {
        switch (i) {
            case 0:
                print("number of field: ");
                break;
            case 1:
                print("surname: ");
                break;
            case 2:
                print("name: ");
                break;
            case 3:
                print("secondname: ");
                break;
            case 4:
                print("faculty: ");
                break;
            case 5:
                print("group number: ");
                break;
            case 6:
                print("id: ");
                break;
            case 7:
                print("date of transfer: ");
                break;
        }
    }

    public void delete() {
        print("index of deleting field: ");
    }

    public void changeGroup(int i) {
        switch (i) {
            case 0:
                print("faculty: ");
                break;
            case 1:
                print("sh.groups number: ");
                break;
            case 2:
                print("new number: ");
                break;
        }
    }

    public void xmlFileError() {
        println("bad format of xml file");
    }

    public void badStudentFormat() {
        println("bad fields of student. Please, enter correct information about student.");
    }

    public void badCommand() {
        println("Bad command. Enter command 'help' to open list of programs command.");
    }

    public void notFound(String[] str) {
        println(masToString(str) + "not found");
    }

    public void copyNotSupport() {
        println("copy not support");
    }

    public void welcome() {
        println("Welcom to InfoSys.\n");
    }

    public void xmlOrSerial() {
        println("Enter 'xml' that work with xml sh.database or 'serial' that work with serealizations dayabase.");
        println("Registr of sh.commands does not matter.");
    }


    public void helpMessage() {
        println("You can enter 'help' to open list of programs command.");
    }

    public void help() {
        println("STUDENTS - print list of all sh.students.");
        println("ADD - add new sh.students to sh.database. Enter ADD and enter information about new student.");
        println("UPDATE - update fields of student in sh.database. Enter UPDATE, enter number of student in sh.database, then enter fields of student. If entering field is empty, field has no change.");
        println("DELETE - delete student of sh.database. Enter DELETE and enter number of student in sh.database.");
        println("EXIT - exit from program.");
        println("FIND - find sh.students and sh.groups. Enter FIND and string for searching.");
        println("UNDO - undo of operation.");
        println("GROUPS - print list of sh.groups.");
        println("RENAMEGROUP - rename group. Enter RENAMEGROUP, faculty, group number and new group number.");
        println("COPY - copy from \"sh.students.xml\" to \"students2.xml\".");
        println("REMOVEGROUP - remove group. Enter RENAMEGROUP, faculty, group number.");
        println("HELP - open help");
    }

    public void programError() {
        println("program error");
    }

    public void nothingToDelete() {
        println("nothing to delete");
    }

    public void studentNotFound() {
        println("Student not found. Please, enter correct index.");
    }

    private void println(String str) {
        System.out.println(str);
    }

    private void print(String str) {
        System.out.print(str);
    }
}
