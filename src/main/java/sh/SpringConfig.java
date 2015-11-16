package sh;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import sh.controllers.Controller;
import sh.dao.GroupDaoXml;
import sh.dao.StudDao;
import sh.dao.StudDaoDB;
import sh.dao.StudDaoXml;
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
        return new Model(MODEL_KEY);
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

    @Bean
    public StudDaoXml studDaoXml() {
        StudDaoXml studDaoXml = new StudDaoXml();
        studDaoXml.setStudpattern(studentPattern());
        return studDaoXml;
    }

    @Bean
    public GroupDaoXml groupDaoXml() {
        GroupDaoXml groupDaoXml = new GroupDaoXml();
        groupDaoXml.setStudDao(studDaoXml());
        return groupDaoXml;
    }

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
}
