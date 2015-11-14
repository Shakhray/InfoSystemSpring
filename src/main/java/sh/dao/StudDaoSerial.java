package sh.dao;

import org.xml.sax.SAXException;
import sh.database.DataBase;
import sh.exception.BadXmlFileException;
import sh.exception.CopyNotSupportException;
import sh.io.ReadWriteSerial;
import sh.students.Student;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class StudDaoSerial implements StudDao {
    private DataBase studs = null;

    public StudDaoSerial() {
        studs = new DataBase();
        studs.addDefault();

        ReadWriteSerial rw = new ReadWriteSerial();
        try {
            studs = (DataBase) rw.readFromFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try {
            rw.writeToFite(studs);
            studs = (DataBase) rw.readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void write(Student stud) {
        studs.addStudent(stud);
        ReadWriteSerial rw = new ReadWriteSerial();
        try {
            rw.writeToFite(studs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Collection<Student> readAll() {
        return studs.getStudents();
    }

    public void delete(Student stud) {
        studs.remove(stud);
        ReadWriteSerial rw = new ReadWriteSerial();
        try {
            rw.writeToFite(studs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Student stud, int index) {
        DataBase newstud = new DataBase();
        int i = 0;
        for (Student st : studs.getStudents()) {
            if (i == index) newstud.addStudent(stud);
            else newstud.addStudent(st);
            i++;
        }
        studs = newstud;
        ReadWriteSerial rw = new ReadWriteSerial();
        try {
            rw.writeToFite(studs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void undo(Collection<Student> students) {

        DataBase newstud = new DataBase();
        for (Student st : students) {
            newstud.addStudent(st);
        }
        studs = newstud;
        ReadWriteSerial rw = new ReadWriteSerial();
        try {
            rw.writeToFite(newstud);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Student findByIndex(int index) {
        Collection<Student> studs = readAll();
        int i = 0;
        Student findstud = null;
        for (Student st : studs) {
            if (i == index) {
                findstud = st;
                break;
            }
            i++;
        }
        return findstud;
    }

    public Collection<Student> read(String[] fields) {
        Collection<Student> foundstudents = new ArrayList<Student>();
        Collection<Student> students = readAll();
        boolean b = false;
        for (Student stud : students) {
            for (String field : fields)
                if (field.equals(stud.getSurname()) || field.equals(stud.getName()) || field.equals(stud.getSecondName()) ||
                        field.equals(stud.getGroupNumber()) || field.equals(stud.getStudentID()) || field.equals(stud.getDateOfTransfer()) ||
                        field.equals(stud.getFaculty()))
                    b = true;
            if (b) foundstudents.add(stud);
            b = false;
        }
        return foundstudents;
    }

    public void copyFile() throws CopyNotSupportException {
        throw new CopyNotSupportException();
    }

    public Student read(Student stud) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException {
        Student foundstudent = null;
        Collection<Student> students = readAll();
        for (Student stud1 : students)
            if (stud1.equals(stud)) {
                foundstudent = stud1;
                break;
            }
        return foundstudent;
    }
}
