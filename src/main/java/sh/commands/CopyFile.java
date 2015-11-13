package sh.commands;

import org.xml.sax.SAXException;
import sh.dao.StudDao;
import sh.exception.BadXmlFileException;
import sh.exception.CopyNotSupportException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class CopyFile extends Command {

    public CopyFile(StudDao sdao) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException {
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
