package sh;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * Created by AShakhray on 16.11.2015.
 */
public class Context {
    private static Context context;
    private ApplicationContext appContext = new AnnotationConfigApplicationContext(SpringConfig.class);
    private Context() {

    }
    public static synchronized Context getInstance() {
        if (context == null) context = new Context();//new AnnotationConfigApplicationContext(SpringConfig.class);
        return context;
    }

    public ApplicationContext getContext() {
        return appContext;
    }
}
