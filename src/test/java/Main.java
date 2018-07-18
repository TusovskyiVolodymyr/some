import annotations.Injector;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.Field;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(""))
                .setScanners(new FieldAnnotationsScanner()));
        Set<Field> annotated = reflections.getFieldsAnnotatedWith(Injector.class);
        System.out.println(annotated);
        annotated.forEach(System.out::println);
        System.out.println(annotated.size());
    }
}
