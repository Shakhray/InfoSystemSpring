package sh.students;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by AShakhray on 17.11.2015.
 */
@XmlType(name = "students")
@XmlRootElement
public class Students {
    private List<Student> students = new LinkedList<>();

    @XmlElementWrapper(name = "students")
    //@XmlElement(name = "student")
    public List<Student> getStudents() {
        return students;
    }

    //@XmlElementWrapper
    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
