package sh.use;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import sh.Context;
import sh.SpringConfig;
import sh.viewers.Console;

import javax.sql.DataSource;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        ApplicationContext context = Context.getInstance().getContext();//new AnnotationConfigApplicationContext(SpringConfig.class);
        Console console = context.getBean(Console.class);
        //Console console = (Console)context.getBean("console");
        console.runConsole();

//        String sqlQuerry = "select * from Students";
//        JdbcTemplate template = (JdbcTemplate)context.getBean("jdbcTemplate");
//        List result = template.queryForList(sqlQuerry);
//        System.out.println("\nResult : " + result.toString());
    }

}
