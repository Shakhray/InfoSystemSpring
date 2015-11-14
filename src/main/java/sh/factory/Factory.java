package sh.factory;

import sh.dao.GroupDao;
import sh.dao.StudDao;

import java.io.IOException;

public abstract class Factory {
    public abstract StudDao getStudDao() throws IOException, ClassNotFoundException;

    public abstract GroupDao getGroupDao();
}
