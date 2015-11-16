package sh.dao;

import org.xml.sax.SAXException;
import sh.exception.BadXmlFileException;
import sh.exception.NothingToDeleteException;
import sh.groups.Group;
import sh.students.Student;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Created by AShakhray on 16.11.2015.
 */
public class GroupDaoDB implements GroupDao {
    @Override
    public StudDao getStudDao() {
        return null;
    }

    @Override
    public void setStudDao(StudDao sdao) {

    }

    @Override
    public Collection<Group> readAll() throws BadXmlFileException, SAXException, IOException, ParserConfigurationException {
        return null;
    }

    @Override
    public void delete(String number, String faculty) throws IOException, BadXmlFileException, SAXException, ParserConfigurationException, NothingToDeleteException {

    }

    @Override
    public void update(String faculty, String group, String renamedgroup) throws BadXmlFileException, SAXException, ParserConfigurationException, IOException {

    }
}
