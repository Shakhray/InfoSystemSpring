package dao;

import exception.BadXmlFileException;
import exception.NothingToDeleteException;
import groups.Group;

import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public interface GroupDao {
	public StudDao getStudDao();
	public Collection<Group> readAll() throws BadXmlFileException, SAXException, IOException, ParserConfigurationException;
	public void delete(String number, String faculty) throws IOException, BadXmlFileException, SAXException, ParserConfigurationException, NothingToDeleteException;
	public void update(String faculty, String group, String renamedgroup) throws BadXmlFileException, SAXException, ParserConfigurationException, IOException;
	public void setStudDao(StudDao sdao);
}
