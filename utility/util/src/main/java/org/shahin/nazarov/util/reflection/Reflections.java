package org.shahin.nazarov.util.reflection;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

public class Reflections {

    public static Collection<Class> getClasses(String packageName, Class<? extends Annotation> annotation)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList();
        while (resources.hasMoreElements()) {
            URL resource = (URL) resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList classes = new ArrayList();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName, annotation));
        }
        return classes;
    }


    public static List<Class> findClasses(File directory, String packageName,
                                           Class<? extends Annotation> annotation)
            throws ClassNotFoundException {
        List<Class> classes = new ArrayList();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName(), annotation));
            } else if (file.getName().endsWith(".class")) {
                Class<?> aClass = Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
                if (aClass.getDeclaredAnnotation(annotation) != null)
                    classes.add(aClass);
            }
        }
        return classes;
    }
}
