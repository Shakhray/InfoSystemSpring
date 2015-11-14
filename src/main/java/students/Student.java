package students;

import java.io.Serializable;


public class Student  implements Serializable {

	private static final long serialVersionUID = 1L;
	private String surname, name, secondName;
	private String studentID, groupNumber;
	private String dateOfTransfer;
	
	private String faculty;
	
	public Student(String surname, String name, String secondName, String faculty, String groupNumber, String studentID, String dateOfTransfer){
		this.surname=surname;
		this.name=name;
		this.secondName=secondName;
		this.groupNumber=groupNumber;
		this.studentID=studentID;
		this.dateOfTransfer=dateOfTransfer;
		this.faculty = faculty;
	}
	public String getSurname(){
		return  surname;
	}
	public String getSecondName(){
		return secondName;
	}
	public String getName(){
		return name;
	}
	public String getGroupNumber(){
		return groupNumber;
	}
	public String getStudentID(){
		return studentID;
	}
	public String getDateOfTransfer(){
		return dateOfTransfer;
	}
	@Override
	public boolean equals(Object obj){
		Student stud = (Student)obj;
		if (stud.getSurname().equals(surname) && 
			stud.getName().equals(name) && 
			stud.getSecondName().equals(secondName) && 
			stud.getGroupNumber().equals(groupNumber) && 
			stud.getStudentID().equals(studentID) && 
			stud.getDateOfTransfer().equals(dateOfTransfer) &&
			stud.getFaculty().equals(faculty))
			return true; 
		else return false;
	}
	public String getFaculty(){
		return faculty;
	}
	public String getFieldByIndex(int index){
		String field = null;
		try{
			switch(index){
				case 0 : {field = getSurname(); break;}
				case 1 : {field = getName(); break;}
				case 2 : {field = getSecondName(); break;}
				case 3 : {field = getFaculty(); break;}
				case 4 : {field = getGroupNumber(); break;}
				case 5 : {field = getStudentID(); break;}
				case 6 : {field = getDateOfTransfer(); break;}
				default: throw new Exception();
			}
		}catch(Exception e){
		e.printStackTrace();
		}
		return field;
	}
}
