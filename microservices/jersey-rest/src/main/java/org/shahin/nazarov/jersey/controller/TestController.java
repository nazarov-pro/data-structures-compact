package org.shahin.nazarov.jersey.controller;

import com.google.inject.Inject;
import org.glassfish.jersey.server.ManagedAsync;
import org.shahin.nazarov.jersey.Data;
import org.shahin.nazarov.jersey.JerseyDiModule;
import org.shahin.nazarov.jersey.JerseyRegister;
import org.shahin.nazarov.jersey.PATCH;
import org.shahin.nazarov.util.reflection.Injection;

import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.Executor;

@JerseyRegister
@Path("/")
public class TestController {


    private Executor executor;
    public TestController(){
        this.executor = Injection.getInstance(new JerseyDiModule()).get(Executor.class);
    }


    @GET
    @Produces(MediaType.TEXT_HTML)
    public String hello(){
        return "{\"name\": \"Shahin\"}";
    }


    @Path("/me")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Data data() throws InterruptedException {
        Data data = new Data();
        Thread.sleep(5000);
        data.setAge(1);
        data.setName("dfds");
        return data;
    }

    @Path("/async/me")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void data(@Suspended final AsyncResponse asyncResponse) {
        executor.execute(() -> {
            Data data = new Data();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.setAge(1);
            data.setName("dfds");
            asyncResponse.resume(data);
        });
    }


    @Path("/me")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Data dataEdit(Data data){
        data.setName("cada");
        return data;
    }

    @Path("/me")
    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Data dataEditIt(Data data){
        data.setAge(1);
        return data;
    }


}
