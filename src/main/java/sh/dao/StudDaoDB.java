package sh.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.xml.sax.SAXException;
import sh.exception.BadXmlFileException;
import sh.exception.CopyNotSupportException;
import sh.exception.NothingToDeleteException;
import sh.exception.StudentNotFoundException;
import sh.students.Student;
import sh.students.StudentMapper;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by AShakhray on 16.11.2015.
 */
public class StudDaoDB implements StudDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void write(Student stud) throws IOException {

    }

    @Override
    public Collection<Student> read(String[] fields) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException {
        return null;
    }

    @Override
    public Student read(Student stud) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException {
        return null;
    }

    @Override
    public Collection<Student> readAll() throws BadXmlFileException, SAXException, IOException, ParserConfigurationException {
        String getAllQuery = "select * from students";
        System.out.println("jdbcTemplate is null : " + (jdbcTemplate == null));
        return jdbcTemplate.query(getAllQuery, new StudentMapper());
    }

    @Override
    public void delete(Student stud) throws IOException, BadXmlFileException, SAXException, ParserConfigurationException, NothingToDeleteException {

    }

    @Override
    public void update(Student stud, int key) throws IOException, BadXmlFileException, SAXException, ParserConfigurationException {

    }

    @Override
    public void undo(Collection<Student> students) throws IOException {

    }

    @Override
    public Student findByIndex(int index) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException, StudentNotFoundException {
        return null;
    }

    @Override
    public void copyFile() throws BadXmlFileException, SAXException, IOException, ParserConfigurationException, CopyNotSupportException {

    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
