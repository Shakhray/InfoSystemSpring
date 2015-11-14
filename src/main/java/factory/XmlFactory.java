package factory;

import dao.*;

public class XmlFactory extends Factory{
	public StudDao getStudDao(){
		return new StudDaoXml();
	}
	public GroupDao getGroupDao(){
		return new GroupDaoXml();
	}
}
