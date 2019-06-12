package annotations;

import utils.WebDriverProperties;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnnotationProcessor {

    @Property("user1FullName")
    private String string;


    public AnnotationProcessor() {
        proceed(this);
    }

    public static void proceed(Object o) {
        Class clazz = o.getClass();
        Class clazz2 = clazz.getSuperclass();
        Field[] fields2 = clazz2.getDeclaredFields();
        Field[] fields = clazz.getDeclaredFields();
        List<Field> fields1 = new ArrayList<>();
        fields1.addAll(Arrays.asList(fields));
        fields1.addAll(Arrays.asList(fields2));
        for (Field field : fields1) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Property.class) && field.getType() == String.class) {
                String key = field.getAnnotation(Property.class).value();
                try {
                    field.set(o, WebDriverProperties.getProperty(key));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
