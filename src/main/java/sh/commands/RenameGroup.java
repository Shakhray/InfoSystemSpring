package sh.commands;

import org.xml.sax.SAXException;
import sh.dao.GroupDao;
import sh.exception.BadXmlFileException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class RenameGroup extends Command {

    private String faculty, group, renamedgroup;

    public RenameGroup(String faculty, String group, String renamrdgroup, GroupDao gdao) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException {
        super(gdao);
        this.faculty = faculty;
        this.group = group;
        this.renamedgroup = renamrdgroup;
    }

    public void execute() throws BadXmlFileException, SAXException, ParserConfigurationException {
        try {
            super.gdao.update(faculty, group, renamedgroup);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void undo() {
        super.undo();
    }

}
