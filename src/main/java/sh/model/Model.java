package sh.model;

import sh.dao.GroupDao;
import sh.dao.StudDao;
import sh.factory.Factory;

import java.io.IOException;

public class Model {
    private Factory factory;

    public Model(Factory factory) throws IOException, ClassNotFoundException {
        this.factory = factory;
    }

    public StudDao getStudDao() throws IOException, ClassNotFoundException {

        return factory.getStudDao();
    }

    public GroupDao getGroupDao() {
        return factory.getGroupDao();
    }
}
