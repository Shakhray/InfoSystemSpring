package dao;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import patterns.StudentPattern;

import exception.BadXmlFileException;
import exception.NothingToDeleteException;
import exception.StudentNotFoundException;

import students.Student;

public class StudDaoXml implements StudDao{
	private String fileName = "src/main/java/database/students.xml"; //TODO
	private StudentPattern studpattern = new StudentPattern();
	
	public void writeBody(String fileName){
		String separator = System.getProperty("line.separator");
		RandomAccessFile raf=null;
		try{
			raf = new RandomAccessFile(fileName,"rw");
			raf.writeBytes("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"+separator+"<students>"+separator+"<\\students>");
		}
		catch (IOException e){
			System.err.println(e.getMessage());
		}
		finally{
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void deleteFile(String name){
		File f1 = new File(name);
		try{
			if(!f1.delete()) throw new IOException();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void renameFile(String name, String rename){
		File f1 = new File(name);
		File f2 = new File(rename);
		try{
			if(!f1.renameTo(f2)) throw new IOException();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void write(Student stud) throws IOException{
		String separator = System.getProperty("line.separator");
		String endBody = "</students>";
		
		String surname = stud.getSurname(), name = stud.getName(), secondName = stud.getSecondName();
		String studentID = stud.getStudentID(), groupNumber = stud.getGroupNumber();
		String dateOfTransfer = stud.getDateOfTransfer();
		String faculty = stud.getFaculty();
		
		RandomAccessFile raf=null;
		try{
			raf = new RandomAccessFile(fileName,"rw");
			raf.seek(raf.length()-endBody.length()-1);
			raf.writeBytes("\t"+"<student>"+separator);
			raf.writeBytes("\t\t"+"<surname>"+surname+"</surname>"+separator);
			raf.writeBytes("\t\t"+"<name>"+name+"</name>"+separator);
			raf.writeBytes("\t\t"+"<secondName>"+secondName+"</secondName>"+separator);
			raf.writeBytes("\t\t"+"<faculty>"+faculty+"</faculty>"+separator);
			raf.writeBytes("\t\t"+"<groupNumber>"+groupNumber+"</groupNumber>"+separator);
			raf.writeBytes("\t\t"+"<studentID>"+studentID+"</studentID>"+separator);
			raf.writeBytes("\t\t"+"<dateOfTransfer>"+dateOfTransfer+"</dateOfTransfer>"+separator);
			raf.writeBytes("\t"+"</student>"+separator);
			raf.writeBytes(endBody);
			  
		}
		catch (IOException e){
			System.err.println(e.getMessage());
		}
		finally{
			raf.close();
		}
	}
	private Collection<Student> readFromXml() throws BadXmlFileException, SAXException, IOException, ParserConfigurationException{
		Collection<Student> students = new ArrayList<Student>();
		String surname=null, name=null, secondName=null;
		String studentID=null, groupNumber=null;
		String dateOfTransfer = null;
		String faculty=null;

		File xmlFile = new File(fileName);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);
        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName(document.getDocumentElement().getChildNodes().item(1).getNodeName());
        for(int tmp = 0; tmp < nodeList.getLength(); tmp++){
        	Node node = nodeList.item(tmp);
	    	if(node.getNodeType() == Node.ELEMENT_NODE){
	    		Element element = (Element)node;
	    		surname = element.getElementsByTagName("surname").item(0).getChildNodes().item(0).getNodeValue();
	    		name = element.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();
	    		secondName = element.getElementsByTagName("secondName").item(0).getChildNodes().item(0).getNodeValue();
	    		faculty = element.getElementsByTagName("faculty").item(0).getChildNodes().item(0).getNodeValue();
	    		groupNumber = element.getElementsByTagName("groupNumber").item(0).getChildNodes().item(0).getNodeValue();
	    		studentID = element.getElementsByTagName("studentID").item(0).getChildNodes().item(0).getNodeValue();
	    		dateOfTransfer = element.getElementsByTagName("dateOfTransfer").item(0).getChildNodes().item(0).getNodeValue();
	    		if (studpattern.matcher(surname, name, secondName, faculty, groupNumber, studentID, dateOfTransfer))
	    			students.add(new Student(surname, name, secondName, faculty, groupNumber, studentID, dateOfTransfer));
	    		else throw new BadXmlFileException();
	    	}
	    }
        return students;
	}

	public Student findByIndex(int index) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException, StudentNotFoundException{
		Collection<Student> studs = readAll();
		Student findstud = null;
		if (index<studs.size()){
			int i=0;
			
			for(Student st : studs){
				if (i==index) {findstud = st; break;}
				i++;
			}
		}
		else throw new StudentNotFoundException();
		return findstud;
	}
	public Collection<Student> read(String[] fields) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException{
		
		Collection<Student> foundstudents = new ArrayList<Student>();
		Collection<Student> students = readFromXml();
		boolean b = false;
		for(Student stud : students){
			for (String field : fields)
			if (field.equals(stud.getSurname())    	||   field.equals(stud.getName())		||	field.equals(stud.getSecondName())||
				field.equals(stud.getGroupNumber())	||   field.equals(stud.getStudentID())	||	field.equals(stud.getDateOfTransfer()) ||
				field.equals(stud.getFaculty()))
				b=true;
			if (b) foundstudents.add(stud);
			b=false;
		}
	    return foundstudents;
	}
	public Student read(Student stud) throws BadXmlFileException, SAXException, IOException, ParserConfigurationException{
		Student foundstudent = null;
		Collection<Student> students = readFromXml();
		for(Student stud1 : students)
			if (stud1.equals(stud)) {
				foundstudent = stud1;
				break;
			}
		return foundstudent;
	}
	public Collection<Student> readAll() throws BadXmlFileException, SAXException, IOException, ParserConfigurationException{
		return readFromXml();
	}
	public void copyFile() throws BadXmlFileException, SAXException, IOException, ParserConfigurationException{
		Collection<Student> setOfStudents = readFromXml();
		fileName = new String("src/database/students2.xml");
		boolean b = true;
		for(Student stud : readFromXml()){
			for(Student st: setOfStudents)
				if (st.equals(stud)) { b = false; break;}
			if (b) setOfStudents.add(stud);
			b = true;
		}
		fileName = new String("src/database/temp.xml");
		writeBody(fileName);
		for(Student stud1 : setOfStudents)
			write(stud1);
		rewriteFile();
	}
	public void delete (Student stud) throws IOException, BadXmlFileException, SAXException, ParserConfigurationException, NothingToDeleteException{
		Integer index=null;
		int i=0;
		Collection<Student> students = readFromXml();
		for(Student stud1 : students){
			if (stud1.equals(stud)) index=i;
			i++;
		}
		if (index!=null){
			fileName = new String("src/database/temp.xml");
			i=0;
			writeBody(fileName);
			for(Student stud1 : students){
				if (index!=i) write(stud1);
				i++;
			}
			rewriteFile();
		}
		else throw new NothingToDeleteException(); 
	}
	public void update(Student stud, int index) throws IOException, BadXmlFileException, SAXException, ParserConfigurationException{
		int i=0;
		Collection<Student> students = readFromXml();
		Collection<Student> students2 = new ArrayList<Student>();
		for(Student stud1 : students){
			if (i==index) students2.add(stud);
				else students2.add(stud1);
			i++;
		}
		fileName = new String("src/database/temp.xml");
		writeBody(fileName);
		for(Student stud1 : students2)
			write(stud1);
		rewriteFile();
	}
	public void undo(Collection<Student> students) throws IOException{
		fileName = new String("src/database/temp.xml");
		writeBody(fileName);
		for(Student stud1 : students)
			write(stud1);
		rewriteFile();
	}
	private void rewriteFile(){
		String rename = new String("src/database/students.xml"); 
		deleteFile(rename);
		renameFile(fileName, rename);
		fileName = new String("src/database/students.xml"); 
	}
}
