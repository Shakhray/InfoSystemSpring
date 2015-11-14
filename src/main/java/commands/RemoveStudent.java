package commands;

import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import dao.StudDao;
import exception.BadXmlFileException;
import exception.NothingToDeleteException;

import students.Student;

public class RemoveStudent extends Command{
	
	private int index;
	public RemoveStudent(int index, StudDao sdao) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException{
		super(sdao);
		this.index=index;
	}

	public void execute() throws BadXmlFileException, SAXException, ParserConfigurationException, IOException, NothingToDeleteException{
		Collection<Student> studs = super.sdao.readAll();
		int i=0;
		for(Student stud : studs){
			if (i==index) {
				try {
					super.sdao.delete(stud);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
			i++;
		}
	}
	public void undo(){
		super.undo();
	}
}
