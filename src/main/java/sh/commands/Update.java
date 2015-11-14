package sh.commands;

import org.xml.sax.SAXException;
import sh.dao.StudDao;
import sh.exception.BadXmlFileException;
import sh.students.Student;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Update extends Command {

    private int index;
    private Student stud;

    public Update(Student stud, int index, StudDao sdao) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException {
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
