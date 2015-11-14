package commands;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import dao.StudDao;
import exception.BadXmlFileException;
import students.Student;

public class Update extends Command{
	
	private int index;
	private Student stud;
	
	public Update(Student stud, int index, StudDao sdao) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException{
		super(sdao);
		this.index = index;
		this.stud = stud;
	}
	public void execute() throws BadXmlFileException, SAXException, ParserConfigurationException {
		try {
			super.sdao.update(stud, index);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void undo() {
		super.undo();
	}

}
