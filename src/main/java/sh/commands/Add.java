package sh.commands;

import org.xml.sax.SAXException;
import sh.dao.StudDao;
import sh.exception.BadXmlFileException;
import sh.students.Student;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * @author ���������
 */
public class Add extends Command {

    private Student stud;

    public Add(Student stud, StudDao sdao) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException {
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
