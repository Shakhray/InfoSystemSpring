package sh.dao;

import org.xml.sax.SAXException;
import sh.exception.BadXmlFileException;
import sh.exception.NothingToDeleteException;
import sh.exception.StudentNotFoundException;
import sh.patterns.StudentPattern;
import sh.students.Student;
import sh.students.Students;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class StudDaoXml implements StudDao {
    private String fileName = "src/main/java/sh/database/students.xml"; //TODO
    private StudentPattern studpattern;

    public void setStudpattern(StudentPattern studpattern) {
        this.studpattern = studpattern;
    }

    public void writeBody(String fileName) {
        String separator = System.getProperty("line.separator");
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(fileName, "rw");
            raf.writeBytes("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" + separator + "<students>" + separator + "<\\students>");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (raf != null) {
                    raf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteFile(String name) {
        File f1 = new File(name);
        try {
            if (!f1.delete()) throw new IOException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void renameFile(String name, String rename) {
        File f1 = new File(name);
        File f2 = new File(rename);
        try {
            if (!f1.renameTo(f2)) throw new IOException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(Student stud) throws IOException {
        String separator = System.getProperty("line.separator");
        String endBody = "<students>";

        String surname = stud.getSurname(), name = stud.getName(), secondName = stud.getSecondName();
        String studentID = stud.getStudentID(), groupNumber = stud.getGroupNumber();
        String dateOfTransfer = stud.getDateOfTransfer();
        String faculty = stud.getFaculty();

        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(fileName, "rw");
            raf.seek(raf.length() - endBody.length() - 1);
            raf.writeBytes("\t" + "<student>" + separator);
            raf.writeBytes("\t\t" + "<surname>" + surname + "</surname>" + separator);
            raf.writeBytes("\t\t" + "<name>" + name + "</name>" + separator);
            raf.writeBytes("\t\t" + "<secondName>" + secondName + "</secondName>" + separator);
            raf.writeBytes("\t\t" + "<faculty>" + faculty + "</faculty>" + separator);
            raf.writeBytes("\t\t" + "<groupNumber>" + groupNumber + "</groupNumber>" + separator);
            raf.writeBytes("\t\t" + "<studentID>" + studentID + "</studentID>" + separator);
            raf.writeBytes("\t\t" + "<dateOfTransfer>" + dateOfTransfer + "</dateOfTransfer>" + separator);
            raf.writeBytes("\t" + "</student>" + separator);
            raf.writeBytes(endBody);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            if (raf != null) {
                raf.close();
            }
        }
    }

    private List<Student> readFromXml() throws BadXmlFileException, SAXException, IOException, ParserConfigurationException {
        //List<Student> students = new ArrayList<>();
        String surname, name, secondName;
        String studentID, groupNumber;
        String dateOfTransfer;
        String faculty;

        File xmlFile = new File(fileName);

        //InputStream xmlStream = new FileInputStream(xmlFile);
        Students studContainer = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Students.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            studContainer = (Students) unmarshaller.unmarshal(xmlFile);
            System.out.println("studContainer == null : " + (studContainer == null));
            System.out.println("studContainer.students : " + studContainer.getStudents().toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
//
//        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//        Document document = documentBuilder.parse(xmlFile);
//        document.getDocumentElement().normalize();
//        NodeList nodeList = document.getElementsByTagName(document.getDocumentElement().getChildNodes().item(1).getNodeName());
//        studpattern = new StudentPattern();
//        for (int tmp = 0; tmp < nodeList.getLength(); tmp++) {
//            Node node = nodeList.item(tmp);
//            if (node.getNodeType() == Node.ELEMENT_NODE) {
//                Element element = (Element) node;
//                surname = element.getElementsByTagName("surname").item(0).getChildNodes().item(0).getNodeValue();
//                name = element.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();
//                secondName = element.getElementsByTagName("secondName").item(0).getChildNodes().item(0).getNodeValue();
//                faculty = element.getElementsByTagName("faculty").item(0).getChildNodes().item(0).getNodeValue();
//                groupNumber = element.getElementsByTagName("groupNumber").item(0).getChildNodes().item(0).getNodeValue();
//                studentID = element.getElementsByTagName("studentID").item(0).getChildNodes().item(0).getNodeValue();
//                dateOfTransfer = element.getElementsByTagName("dateOfTransfer").item(0).getChildNodes().item(0).getNodeValue();
//                if (studpattern.matcher(surname, name, secondName, faculty, groupNumber, studentID, dateOfTransfer))
//                    students.add(new Student(surname, name, secondName, faculty, groupNumber, studentID, dateOfTransfer));
//                else throw new BadXmlFileException();
//            }
//        }
        return studContainer == null ? new LinkedList<Student>() : studContainer.getStudents();
    }

    public Student findByIndex(int index) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException, StudentNotFoundException {
        return readAll().get(index);
    }

    public Collection<Student> read(String[] fields) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException {
        Collection<Student> foundstudents = new ArrayList<>();
        Collection<Student> students = readFromXml();
        boolean b = false;
        for (Student stud : students) {
            for (String field : fields)
                if (field.equals(stud.getSurname()) || field.equals(stud.getName()) || field.equals(stud.getSecondName()) ||
                        field.equals(stud.getGroupNumber()) || field.equals(stud.getStudentID()) || field.equals(stud.getDateOfTransfer()) ||
                        field.equals(stud.getFaculty()))
                    b = true;
            if (b) foundstudents.add(stud);
            b = false;
        }
        return foundstudents;
    }

    public Student read(Student stud) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException {
        Collection<Student> students = readFromXml();
        for (Student stud1 : students)
            if (stud1.equals(stud)) {
                return stud1;
            }
        return null;
    }

    public List<Student> readAll() throws BadXmlFileException, SAXException, IOException, ParserConfigurationException {
        return readFromXml();
    }

    public void copyFile() throws BadXmlFileException, SAXException, IOException, ParserConfigurationException {
        Collection<Student> setOfStudents = readFromXml();
        fileName = "src/sh/database/students2.xml";
        boolean b = true;
        for (Student stud : readFromXml()) {
            for (Student st : setOfStudents)
                if (st.equals(stud)) {
                    b = false;
                    break;
                }
            if (b) setOfStudents.add(stud);
            b = true;
        }
        fileName = "src/sh/database/temp.xml";
        writeBody(fileName);
        for (Student stud1 : setOfStudents)
            write(stud1);
        rewriteFile();
    }

    public void delete(Student stud) throws IOException, BadXmlFileException, SAXException, ParserConfigurationException, NothingToDeleteException {
        Integer index = null;
        int i = 0;
        Collection<Student> students = readFromXml();
        for (Student stud1 : students) {
            if (stud1.equals(stud)) index = i;
            i++;
        }
        if (index != null) {
            fileName = "src/sh/database/temp.xml";
            i = 0;
            writeBody(fileName);
            for (Student stud1 : students) {
                if (index != i) write(stud1);
                i++;
            }
            rewriteFile();
        } else throw new NothingToDeleteException();
    }

    public void update(Student stud, int index) throws IOException, BadXmlFileException, SAXException, ParserConfigurationException {
        int i = 0;
        Collection<Student> students = readFromXml();
        Collection<Student> students2 = new ArrayList<>();
        for (Student stud1 : students) {
            if (i == index) students2.add(stud);
            else students2.add(stud1);
            i++;
        }
        fileName = "src/sh/database/temp.xml";
        writeBody(fileName);
        for (Student stud1 : students2)
            write(stud1);
        rewriteFile();
    }

    public void undo(Collection<Student> students) throws IOException {
        fileName = "src/sh/database/temp.xml";
        writeBody(fileName);
        for (Student stud1 : students)
            write(stud1);
        rewriteFile();
    }

    private void rewriteFile() {
        String rename = "src/sh/database/students.xml";
        deleteFile(rename);
        renameFile(fileName, rename);
        fileName = "src/sh/database/students.xml";
    }
}
