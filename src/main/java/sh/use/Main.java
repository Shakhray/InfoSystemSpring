package sh.use;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sh.SpringConfig;
import sh.viewers.Console;

public class Main {
    public static void main(String[] args) {
        //ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Console console = context.getBean(Console.class);
        //Console console = (Console)context.getBean("console");
        console.runConsole();
    }

}
