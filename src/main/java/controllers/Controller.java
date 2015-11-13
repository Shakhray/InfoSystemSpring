package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import commands.Add;
import commands.Command;
import commands.CopyFile;
import commands.RemoveGroup;
import commands.RemoveStudent;
import commands.RenameGroup;
import commands.Update;

import students.Student;

import exception.BadXmlFileException;
import exception.CopyNotSupportException;
import exception.NothingToDeleteException;
import exception.StudentNotFoundException;
import groups.Group;

import model.Model;

public class Controller {
	private Stack<Command> commands;
	private Model model;

	public void setCommandStack(Stack<Command> commands) {
		this.commands = commands;
	}

	public void setModel(String databasekey) throws IOException, ClassNotFoundException{
		model = new Model(databasekey);
	}
	
	public void add(String[] arr) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException, CopyNotSupportException, NothingToDeleteException{
		Student stud = new Student(arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],arr[6]);
		Command add = new Add(stud,model.getStudDao());
		add.execute();
		commands.push(add);
	}
	public void update(String[] studField1, int index) throws IOException, BadXmlFileException, SAXException, ParserConfigurationException, CopyNotSupportException, NothingToDeleteException, StudentNotFoundException{
		String[] studField = studField1;
		Student stud1 = model.getStudDao().findByIndex(index);
		for (int i=0; i<=6; i++)
			if ("".equals(studField[i]) || studField[i] == null) studField[i]=stud1.getFieldByIndex(i);
		Student stud = new Student(studField[0],studField[1],studField[2],studField[3],studField[4],studField[5],studField[6]);
		Command update = new Update(stud,index,model.getStudDao());
		update.execute();
		commands.push(update);
	}
	public void delete(int index) throws IOException, BadXmlFileException, SAXException, ParserConfigurationException, CopyNotSupportException, NothingToDeleteException{
		Command remove = new RemoveStudent(index, model.getStudDao());
		remove.execute();
		commands.push(remove);
	}
	
	public Collection<Student> find(String[] fields) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException{
		Collection<Student> students = new ArrayList<Student>();
		students = model.getStudDao().read(fields);
		return students;
	}
	public Student find(Student stud) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException{
		return model.getStudDao().read(stud);
	}
	public Collection<Student> allStudents() throws BadXmlFileException, SAXException, IOException, ParserConfigurationException{
		return model.getStudDao().readAll();
	}
	public Collection<Group> allGroups() throws BadXmlFileException, SAXException, IOException, ParserConfigurationException{
		return model.getGroupDao().readAll();
	}
	public void undo(){
		if (!commands.isEmpty()) 
			commands.pop().undo();
	}
	public void deleteGroup(String number, String faculty) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException, CopyNotSupportException, NothingToDeleteException{
		Command remove = new RemoveGroup(number,faculty,model.getGroupDao());
		remove.execute();
		commands.push(remove);
	}
	public void copyFile() throws BadXmlFileException, SAXException, IOException, ParserConfigurationException, CopyNotSupportException, NothingToDeleteException{
		Command remove = new CopyFile(model.getStudDao());
		remove.execute();
		commands.push(remove);
	}
	public void renameGroup(String faculty, String group, String renamedgroup) throws BadXmlFileException, SAXException, ParserConfigurationException, IOException, CopyNotSupportException, NothingToDeleteException{
		Command rename = new RenameGroup(faculty, group, renamedgroup, model.getGroupDao());
		rename.execute();
		commands.push(rename);
	}
}
