package org.shahin.nazarov.jersey;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import org.glassfish.jersey.server.filter.CsrfProtectionFilter;
import org.shahin.nazarov.util.reflection.Reflections;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Collection<Class> classes = Collections.EMPTY_SET;
        try {
            classes = Reflections.getClasses(getClass().getPackage().getName(), JerseyRegister.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        swaggerImplementation(classes);
        classes.add(CsrfProtectionFilter.class);
        return new HashSet(classes);
    }

    private void swaggerImplementation(Collection<Class> classes) {
        classes.add(OpenApiResource.class);
//        classes.add(AcceptHeaderOpenApiResource.class);
    }


}

