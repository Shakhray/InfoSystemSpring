package commands;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import dao.StudDao;
import exception.BadXmlFileException;

import students.Student;
/**
 * 
 * @author Александр
 *
 */
public class Add extends Command{

	private Student stud;
	
	public Add(Student stud, StudDao sdao) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException{
		super(sdao);
		this.stud = stud;		
	}
	
	public void execute() {
		try {
			super.sdao.write(stud);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void undo() {
		super.undo();
	}
	
}
