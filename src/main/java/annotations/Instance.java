package annotations;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Instance {
    private static final Logger log = LogManager.getLogger(Instance.class);

    public static void create(Object o) {
        try {
//            Class clazz = Class.forName(o.getClass().getName());
//            ClassLoader classLoader = clazz.getClassLoader();
            Class clazz = o.getClass();
            Class clazz2 = clazz.getSuperclass();
            Field[] fields2 = clazz2.getDeclaredFields();
            log.debug("Scanning: " + o.getClass().getName());
            Field[] fields = clazz.getDeclaredFields();
            List<Field> fields1 = new ArrayList<>();
            fields1.addAll(Arrays.asList(fields));
            fields1.addAll(Arrays.asList(fields2));
            for (Field field : fields1) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Injector.class)) {
//                    classLoader.loadClass(field.getType().getName());
                    Class aClass = Class.forName(field.getType().getName());
                    Object obj = aClass.newInstance();
                    field.set(o, obj);
                    log.debug(String.format("Instance of: %s was successfully created!", field.getType().getName()));
                }
            }
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            log.error(e);
        }
    }

    public static void create(Set<Field> fieldSet) {
        for (Field field : fieldSet) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Injector.class)) {
                try {
                    ClassLoader classLoader = field.getDeclaringClass().getClassLoader();
                    classLoader.loadClass(field.getType().getName());
                    Object obj = field.getType().newInstance();

                    field.set(field.getDeclaringClass().newInstance(), obj);

                    log.info(String.format("Instance of: %s was successfully created!", field.getType().getName()));
                } catch (InstantiationException | IllegalAccessException e) {
                    e.getMessage();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
