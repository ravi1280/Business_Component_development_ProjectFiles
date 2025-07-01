package lk.jiat.ee.jersey.controller;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.glassfish.jersey.server.mvc.Viewable;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

import java.util.HashMap;
import java.util.Map;

@Path("/")
public class HomeController {

    @GET
    public Viewable index() {
        Map<String, Object> model = new HashMap<>();
        model.put("message", "Hello World");

        return new Viewable("/index", model);
    }
}
