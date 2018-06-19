import java.lang.reflect.Method;

public class Creds {

    public static String[] login(Object o) {
        Class clazz = o.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            method.setAccessible(true);
            System.out.println("Processing method: " + method.getName());
            if (method.isAnnotationPresent(Credentials.class)) {
                String[] strings = method.getAnnotation(Credentials.class).creds();
                return strings;
            }
        }
        return new String[2];
    }
}
