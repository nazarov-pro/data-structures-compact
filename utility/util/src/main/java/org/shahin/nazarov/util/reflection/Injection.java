package org.shahin.nazarov.util.reflection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Module;

public class Injection {
    private static Injection injection;
    private final Injector injector;

    private Injection(Module... modules){
        this.injector = Guice.createInjector(modules);
    }

    public static Injection getInstance(Module... modules) {
        if(injection == null){
            injection = new Injection(modules);
        }
        return injection;
    }

    public Injector getInjector() {
        return injector;
    }

    public <M> M get(Class<M> mClass){
        return this.injector.getInstance(mClass);
    }

    public <M> M get(Key<M> mKey){
        return this.injector.getInstance(mKey);
    }
}
