package sh.viewers;

import sh.groups.Group;
import sh.students.Student;

import java.util.Collection;

public class Viewer {
    public <E> void printCollection(Collection<E> collect) {
        int i = 1;
        for (E e : collect) {
            if ("Group".equals(e.getClass().getSimpleName()))
                System.out.println("\t" + i + ". " + ((Group) e).getFaculty() + " " + ((Group) e).getGroup());
            else
                System.out.println("\t" + i + ". " + ((Student) e).getSurname() + " " + ((Student) e).getName() + " " + ((Student) e).getSecondName() + " " + ((Student) e).getFaculty() + " " + ((Student) e).getGroupNumber() + " " + ((Student) e).getStudentID() + " " + ((Student) e).getDateOfTransfer());
            i++;
        }
    }

    public String masToString(String[] mas) {
        StringBuilder s = new StringBuilder();
        for (String st : mas)
            s.append(st).append(" ");
        return s.toString();
    }

    public void print(Student stud) {
        System.out.println("\t" + stud.getSurname() + " " + stud.getName() + " " + stud.getSecondName() + " " + stud.getFaculty() + " " + stud.getGroupNumber() + " " + stud.getStudentID() + " " + stud.getDateOfTransfer());
    }

    public void comandLine() {
        System.out.print("\nInfoSys> ");
    }

    public void update(int i) {
        switch (i) {
            case 0: {
                System.out.print("number of field: ");
                break;
            }
            case 1: {
                System.out.print("surname: ");
                break;
            }
            case 2: {
                System.out.print("name: ");
                break;
            }
            case 3: {
                System.out.print("secondname: ");
                break;
            }
            case 4: {
                System.out.print("faculty: ");
                break;
            }
            case 5: {
                System.out.print("group number: ");
                break;
            }
            case 6: {
                System.out.print("id: ");
                break;
            }
            case 7: {
                System.out.print("date of transfer: ");
                break;
            }
        }
    }

    public void delete() {
        System.out.print("index of deleting field: ");
    }

    public void changeGroup(int i) {
        switch (i) {
            case 0: {
                System.out.print("faculty: ");
                break;
            }
            case 1: {
                System.out.print("sh.groups number: ");
                break;
            }
            case 2: {
                System.out.print("new number: ");
                break;
            }
        }
    }

    public void xmlFileError() {
        System.out.println("bad format of xml file");
    }

    public void badStudentFormat() {
        System.out.println("bad fields of student. Please, enter correct information about student.");
    }

    public void badCommand() {
        System.out.println("Bad command. Enter command 'help' to open list of programs command.");
    }

    public void notFound(String[] str) {
        System.out.println(masToString(str) + "not found");
    }

    public void copyNotSupport() {
        System.out.println("copy not support");
    }

    public void welcome() {
        System.out.println("Welcom to InfoSys.\n");
    }

    public void xmlOrSerial() {
        System.out.println("Enter 'xml' that work with xml sh.database or 'serial' that work with serealizations dayabase.");
        System.out.println("Registr of sh.commands does not matter.");
    }


    public void helpMessage() {
        System.out.println("You can enter 'help' to open list of programs command.");
    }

    public void help() {
        System.out.println("STUDENTS - print list of all sh.students.");
        System.out.println("ADD - add new sh.students to sh.database. Enter ADD and enter information about new student.");
        System.out.println("UPDATE - update fields of student in sh.database. Enter UPDATE, enter number of student in sh.database, then enter fields of student. If entering field is empty, field has no change.");
        System.out.println("DELETE - delete student of sh.database. Enter DELETE and enter number of student in sh.database.");
        System.out.println("EXIT - exit from program.");
        System.out.println("FIND - find sh.students and sh.groups. Enter FIND and string for searching.");
        System.out.println("UNDO - undo of operation.");
        System.out.println("GROUPS - print list of sh.groups.");
        System.out.println("RENAMEGROUP - rename group. Enter RENAMEGROUP, faculty, group number and new group number.");
        System.out.println("COPY - copy from \"sh.students.xml\" to \"students2.xml\".");
        System.out.println("REMOVEGROUP - remove group. Enter RENAMEGROUP, faculty, group number.");
        System.out.println("HELP - open help");
    }

    public void programError() {
        System.out.println("program error");
    }

    public void nothingToDelete() {
        System.out.println("nothing to delete");
    }

    public void studentNotFound() {
        System.out.println("Student not found. Please, enter correct index.");
    }
}
