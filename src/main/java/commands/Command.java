package commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import students.Student;

import dao.GroupDao;
import dao.StudDao;

import exception.BadXmlFileException;
import exception.CopyNotSupportException;
import exception.NothingToDeleteException;
/**
 * 
 * @author Александр
 *	Commands of working with students database
 */
abstract public class Command {
	protected StudDao sdao;
	protected GroupDao gdao;
	private Collection<Student> backup;
	public Command(StudDao sdao) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException{
		this.sdao = sdao;
		backup = new ArrayList<Student>();
		backup();
	}
	public Command(GroupDao gdao) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException{
		this.sdao = gdao.getStudDao();
		this.gdao = gdao;
		backup = new ArrayList<Student>();
		backup();
	}
	/**
	 * execute command
	 * @throws BadXmlFileException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws CopyNotSupportException 
	 * @throws NothingToDeleteException 
	 */
	private void backup() throws BadXmlFileException, SAXException, IOException, ParserConfigurationException{
		for (Student st : sdao.readAll())
			backup.add(st);
	}
	abstract public void execute() throws BadXmlFileException, SAXException, ParserConfigurationException, IOException, CopyNotSupportException, NothingToDeleteException;
	
	/**
	 * undo of command
	 */
	public void undo() {
		try {
			sdao.undo(backup);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
