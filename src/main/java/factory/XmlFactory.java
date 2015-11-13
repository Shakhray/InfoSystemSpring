package factory;

import dao.GroupDao;
import dao.GroupDaoXml;
import dao.StudDao;
import dao.StudDaoXml;

public class XmlFactory extends Factory {
    public StudDao getStudDao() {
        return new StudDaoXml();
    }

    public GroupDao getGroupDao() {
        return new GroupDaoXml();
    }
}
