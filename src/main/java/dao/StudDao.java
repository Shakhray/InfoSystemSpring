package dao;

import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import exception.BadXmlFileException;
import exception.CopyNotSupportException;
import exception.NothingToDeleteException;
import exception.StudentNotFoundException;

import students.Student;

public interface StudDao {
	public void write(Student stud) throws IOException;
	public Collection<Student> read(String[] fields) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException;
	public Student read(Student stud) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException;
	public Collection<Student> readAll() throws BadXmlFileException, SAXException, IOException, ParserConfigurationException;
	public void delete(Student stud) throws IOException, BadXmlFileException, SAXException, ParserConfigurationException, NothingToDeleteException;
	public void update(Student stud, int key) throws IOException, BadXmlFileException, SAXException, ParserConfigurationException;
	public void undo(Collection<Student> students) throws IOException;
	public Student findByIndex(int index) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException, StudentNotFoundException;
	public void copyFile() throws BadXmlFileException, SAXException, IOException, ParserConfigurationException, CopyNotSupportException;
}
