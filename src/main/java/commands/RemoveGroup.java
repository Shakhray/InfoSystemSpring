package commands;

import java.io.IOException;
import java.util.Collection;
import java.util.ConcurrentModificationException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import students.Student;
import dao.GroupDao;
import exception.BadXmlFileException;
import exception.CopyNotSupportException;
import exception.NothingToDeleteException;

public class RemoveGroup extends Command{
	
	private String number, faculty;
	
	public RemoveGroup(String number, String faculty, GroupDao gdao) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException{
		super(gdao);
		this.number = number;
		this.faculty = faculty;
	}
	public void execute() throws BadXmlFileException, SAXException,	ParserConfigurationException, IOException, CopyNotSupportException,	NothingToDeleteException {
		Collection<Student> students = super.sdao.readAll();
		try{
		for(Student stud : students)
			if (stud.getGroupNumber().equals(number) && stud.getFaculty().equals(faculty)){
				try {
					super.sdao.delete(stud);
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}catch(ConcurrentModificationException e){
			e.printStackTrace();
		}
	}
	public void undo(){
		super.undo();
	}
}
