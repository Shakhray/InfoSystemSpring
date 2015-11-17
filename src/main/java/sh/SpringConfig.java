package sh;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import sh.controllers.Controller;
import sh.controllers.XmlOrSerial;
import sh.dao.StudDaoDB;
import sh.factory.DBFactory;
import sh.factory.Factory;
import sh.factory.SerialFactory;
import sh.factory.XmlFactory;
import sh.model.Model;
import sh.patterns.StudentPattern;
import sh.viewers.Console;
import sh.viewers.Viewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static sh.ConfigParameters.MODEL_KEY;

/**
 * Created by AShakhray on 13.11.2015.
 */

@Configuration
public class SpringConfig {
    @Bean
    public Console console() throws IOException, ClassNotFoundException {
        Console console = new Console();
        console.setController(controller());
        console.setStudPattern(studentPattern());
        console.setReader(reader());
        console.setViewer(viewer());
        return console;
    }

    @Bean
    public Controller controller() throws IOException, ClassNotFoundException {
        Controller controller = new Controller();
        controller.setModel(model());
        return controller;
    }

    @Bean
    public Model model() throws IOException, ClassNotFoundException {
        Model model = new Model(getFactory(MODEL_KEY));
        //model.setFactory(dbFactory());
        return model;
    }

    private Factory getFactory(String dataBaseKey) {
        XmlOrSerial key = XmlOrSerial.valueOf(dataBaseKey.toUpperCase());
        switch (key) {
            case XML:
                return xmlFactory();
            case SERIAL:
                return serialFactory();
            case DB:
                return dbFactory();
            default:
                throw new IllegalArgumentException();
        }
    }

    @Bean
    public StudentPattern studentPattern() {
        return new StudentPattern();
    }

    @Bean
    public BufferedReader reader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    @Bean
    public Viewer viewer() {
        return new Viewer();
    }

//    @Bean
//    public StudDaoXml studDaoXml() {
//        StudDaoXml studDaoXml = new StudDaoXml();
//        studDaoXml.setStudpattern(studentPattern());
//        return studDaoXml;
//    }
//
//    @Bean
//    public GroupDaoXml groupDaoXml() {
//        GroupDaoXml groupDaoXml = new GroupDaoXml();
//        groupDaoXml.setStudDao(studDaoXml());
//        return groupDaoXml;
//    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:C:/Users/AShakhray/Documents/DataBase1.accdb");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public StudDaoDB studDaoDB() {
        StudDaoDB studDaoDB = new StudDaoDB();
        studDaoDB.setJdbcTemplate(jdbcTemplate());
        return studDaoDB;
    }

    @Bean
    public DBFactory dbFactory() {
        DBFactory dbFactory = new DBFactory();
        dbFactory.setStudDao(studDaoDB());
        return dbFactory;
    }

    @Bean
    public SerialFactory serialFactory() {
        return new SerialFactory();
    }

    @Bean
    public XmlFactory xmlFactory() {
        return new XmlFactory();
    }
}
