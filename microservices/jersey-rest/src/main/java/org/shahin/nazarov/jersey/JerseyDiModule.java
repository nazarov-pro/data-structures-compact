package org.shahin.nazarov.jersey;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class JerseyDiModule extends AbstractModule {

    @Override
    protected void configure() {
        super.configure();
    }

    @Provides
    public Executor executor(){
        return Executors.newFixedThreadPool(5);
    }
}
