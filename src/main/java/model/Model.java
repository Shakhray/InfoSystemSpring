package model;

import java.io.IOException;

import controllers.XmlOrSerial;

import dao.GroupDao;
import dao.StudDao;
import factory.Factory;
import factory.SerialFactory;
import factory.XmlFactory;

public class Model {
	
	private StudDao sdao;
	private GroupDao gdao;
	private Factory factory; 
	
	public Model(String databasekey) throws IOException, ClassNotFoundException{
		
		XmlOrSerial key = XmlOrSerial.valueOf(databasekey.toUpperCase());
		switch(key){
			case XML : {factory = new XmlFactory(); break;}
			case SERIAL : {factory = new SerialFactory(); break;}
			default : throw new IllegalArgumentException();
		}		
		sdao = factory.getStudDao();
		gdao = factory.getGroupDao();
		gdao.setStudDao(sdao);
	}
	public StudDao getStudDao(){
		return sdao;
	}
	public GroupDao getGroupDao(){
		return gdao;
	}
}
