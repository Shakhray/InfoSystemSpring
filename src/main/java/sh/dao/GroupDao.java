package sh.dao;

import org.xml.sax.SAXException;
import sh.exception.BadXmlFileException;
import sh.exception.NothingToDeleteException;
import sh.groups.Group;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collection;

public interface GroupDao {
    StudDao getStudDao();

    void setStudDao(StudDao sdao);

    Collection<Group> readAll() throws BadXmlFileException, SAXException, IOException, ParserConfigurationException;

    void delete(String number, String faculty) throws IOException, BadXmlFileException, SAXException, ParserConfigurationException, NothingToDeleteException;

    void update(String faculty, String group, String renamedgroup) throws BadXmlFileException, SAXException, ParserConfigurationException, IOException;
}
