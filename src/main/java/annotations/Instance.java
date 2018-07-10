package annotations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

public class Instance {
    private static final Logger log = LogManager.getLogger();

    public static void create(Object o) {
        Class clazz = o.getClass();
        log.info("Scanning: " + o.getClass().getName());
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Injector.class)) {
                try {
                    Object obj = field.getType().newInstance();
                    field.set(o, obj);
                    log.info(String.format("Instance of: %s was successfully created!", o.getClass().getName()));
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
