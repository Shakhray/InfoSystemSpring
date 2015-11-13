package sh.factory;

import sh.dao.GroupDao;
import sh.dao.GroupDaoXml;
import sh.dao.StudDao;
import sh.dao.StudDaoXml;

public class XmlFactory extends Factory {
    public StudDao getStudDao() {
        return new StudDaoXml();
    }

    public GroupDao getGroupDao() {
        return new GroupDaoXml();
    }
}
