package sh.controllers;

import org.xml.sax.SAXException;
import sh.commands.*;
import sh.exception.BadXmlFileException;
import sh.exception.CopyNotSupportException;
import sh.exception.NothingToDeleteException;
import sh.exception.StudentNotFoundException;
import sh.groups.Group;
import sh.model.Model;
import sh.students.Student;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collection;
import java.util.Stack;


public class Controller {

    private Stack<Command> commands = new Stack<Command>();
    private Model model;

    public void setModel(Model model) {
        this.model = model;
    }

    public void setModel(String databasekey) throws IOException, ClassNotFoundException {
        model = new Model(databasekey);
    }

    public void add(String[] arr) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException, CopyNotSupportException, NothingToDeleteException {
        Student stud = new Student(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]);
        Command add = new Add(stud, model.getStudDao());
        add.execute();
        commands.push(add);
    }

    public void update(String[] studField1, int index) throws IOException, BadXmlFileException, SAXException, ParserConfigurationException, CopyNotSupportException, NothingToDeleteException, StudentNotFoundException {
        Student stud1 = model.getStudDao().findByIndex(index);
        for (int i = 0; i <= 6; i++)
            if ("".equals(studField1[i]) || studField1[i] == null) studField1[i] = stud1.getFieldByIndex(i);
        Student stud = new Student(studField1[0], studField1[1], studField1[2], studField1[3], studField1[4], studField1[5], studField1[6]);
        Command update = new Update(stud, index, model.getStudDao());
        update.execute();
        commands.push(update);
    }

    public void delete(int index) throws IOException, BadXmlFileException, SAXException, ParserConfigurationException, CopyNotSupportException, NothingToDeleteException {
        Command remove = new RemoveStudent(index, model.getStudDao());
        remove.execute();
        commands.push(remove);
    }

    public Collection<Student> find(String[] fields) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException {
        return model.getStudDao().read(fields);
    }

    public Student find(Student stud) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException {
        return model.getStudDao().read(stud);
    }

    public Collection<Student> allStudents() throws BadXmlFileException, SAXException, IOException, ParserConfigurationException {
        return model.getStudDao().readAll();
    }

    public Collection<Group> allGroups() throws BadXmlFileException, SAXException, IOException, ParserConfigurationException {
        return model.getGroupDao().readAll();
    }

    public void undo() {
        if (!commands.isEmpty())
            commands.pop().undo();
    }

    public void deleteGroup(String number, String faculty) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException, CopyNotSupportException, NothingToDeleteException {
        Command remove = new RemoveGroup(number, faculty, model.getGroupDao());
        remove.execute();
        commands.push(remove);
    }

    public void copyFile() throws BadXmlFileException, SAXException, IOException, ParserConfigurationException, CopyNotSupportException, NothingToDeleteException {
        Command remove = new CopyFile(model.getStudDao());
        remove.execute();
        commands.push(remove);
    }

    public void renameGroup(String faculty, String group, String renamedgroup) throws BadXmlFileException, SAXException, ParserConfigurationException, IOException, CopyNotSupportException, NothingToDeleteException {
        Command rename = new RenameGroup(faculty, group, renamedgroup, model.getGroupDao());
        rename.execute();
        commands.push(rename);
    }
}
