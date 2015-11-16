package sh.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sh.Context;
import sh.SpringConfig;
import sh.dao.GroupDao;
import sh.dao.GroupDaoDB;
import sh.dao.StudDao;
import sh.dao.StudDaoDB;
import sh.viewers.Console;

import java.io.IOException;

/**
 * Created by AShakhray on 16.11.2015.
 */
public class DBFactory extends Factory {
    @Override
    public StudDao getStudDao() throws IOException, ClassNotFoundException {
        ApplicationContext context = Context.getInstance().getContext();
        return context.getBean(StudDaoDB.class);
        //return null;
    }

    @Override
    public GroupDao getGroupDao() {
        return new GroupDaoDB();
    }
}
