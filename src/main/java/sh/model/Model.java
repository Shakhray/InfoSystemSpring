package sh.model;

import sh.controllers.XmlOrSerial;
import sh.dao.GroupDao;
import sh.dao.StudDao;
import sh.factory.DBFactory;
import sh.factory.Factory;
import sh.factory.SerialFactory;
import sh.factory.XmlFactory;

import java.io.IOException;

public class Model {

    private StudDao sdao;
    private GroupDao gdao;

    public Model(String dataBaseKey) throws IOException, ClassNotFoundException {
        Factory factory = getFactory(dataBaseKey);
        sdao = factory.getStudDao();
        gdao = factory.getGroupDao();
        gdao.setStudDao(sdao);
    }

    private Factory getFactory(String dataBaseKey) {
        XmlOrSerial key = XmlOrSerial.valueOf(dataBaseKey.toUpperCase());
        switch (key) {
            case XML:
                return new XmlFactory();
            case SERIAL:
                return new SerialFactory();
            case DB:
                return new DBFactory();
            default:
                throw new IllegalArgumentException();
        }
    }

    public StudDao getStudDao() {
        return sdao;
    }

    public GroupDao getGroupDao() {
        return gdao;
    }
}
