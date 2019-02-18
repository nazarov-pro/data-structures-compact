package org.shahin.nazarov;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class TestController {
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String hello(){
        return "{\"name\": \"Shahin\"}";
    }

}
