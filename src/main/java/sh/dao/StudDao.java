package sh.dao;

import org.xml.sax.SAXException;
import sh.exception.BadXmlFileException;
import sh.exception.CopyNotSupportException;
import sh.exception.NothingToDeleteException;
import sh.exception.StudentNotFoundException;
import sh.students.Student;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collection;

public interface StudDao {
    void write(Student stud) throws IOException;

    Collection<Student> read(String[] fields) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException;

    Student read(Student stud) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException;

    Collection<Student> readAll() throws BadXmlFileException, SAXException, IOException, ParserConfigurationException;

    void delete(Student stud) throws IOException, BadXmlFileException, SAXException, ParserConfigurationException, NothingToDeleteException;

    void update(Student stud, int key) throws IOException, BadXmlFileException, SAXException, ParserConfigurationException;

    void undo(Collection<Student> students) throws IOException;

    Student findByIndex(int index) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException, StudentNotFoundException;

    void copyFile() throws BadXmlFileException, SAXException, IOException, ParserConfigurationException, CopyNotSupportException;
}
