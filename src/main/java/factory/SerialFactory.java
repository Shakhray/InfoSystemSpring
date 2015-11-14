package factory;

import dao.*;

public class SerialFactory extends Factory{
	public SerialFactory(){
		super();
	}
	public StudDao getStudDao(){
		return new StudDaoSerial();
	}
	public GroupDao getGroupDao(){
		return new GroupDaoSerial();
	}
}
