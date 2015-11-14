package commands;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import dao.StudDao;
import exception.BadXmlFileException;
import exception.CopyNotSupportException;

public class CopyFile extends Command{
	
	public CopyFile(StudDao sdao) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException{
		super(sdao);
	}
	public void execute() throws BadXmlFileException, SAXException, ParserConfigurationException, CopyNotSupportException {
		try {
			super.sdao.copyFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void undo() {
		super.undo();
	}
}
