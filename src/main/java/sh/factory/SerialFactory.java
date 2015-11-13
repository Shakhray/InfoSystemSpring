package sh.factory;

import sh.dao.GroupDao;
import sh.dao.GroupDaoSerial;
import sh.dao.StudDao;
import sh.dao.StudDaoSerial;

public class SerialFactory extends Factory {
    public SerialFactory() {
        super();
    }

    public StudDao getStudDao() {
        return new StudDaoSerial();
    }

    public GroupDao getGroupDao() {
        return new GroupDaoSerial();
    }
}
