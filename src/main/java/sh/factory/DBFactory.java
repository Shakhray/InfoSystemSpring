package sh.factory;

import sh.dao.GroupDao;
import sh.dao.GroupDaoDB;
import sh.dao.StudDao;
import sh.dao.StudDaoDB;

import java.io.IOException;

/**
 * Created by AShakhray on 16.11.2015.
 */
public class DBFactory extends Factory {

    private StudDaoDB studDao;

    @Override
    public StudDao getStudDao() throws IOException, ClassNotFoundException {
        return studDao;
    }

    public void setStudDao(StudDaoDB studDao) {
        this.studDao = studDao;
    }

    @Override
    public GroupDao getGroupDao() {
        return new GroupDaoDB();
    }
}
