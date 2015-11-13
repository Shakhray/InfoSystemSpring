package model;

import controllers.XmlOrSerial;
import dao.GroupDao;
import dao.StudDao;
import factory.Factory;
import factory.SerialFactory;
import factory.XmlFactory;

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
            case XML: {
                return new XmlFactory();
            }
            case SERIAL: {
                return new SerialFactory();
            }
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
