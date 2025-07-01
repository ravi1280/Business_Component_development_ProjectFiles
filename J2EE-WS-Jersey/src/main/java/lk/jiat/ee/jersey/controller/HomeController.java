package lk.jiat.ee.jersey.controller;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/home")
public class HomeController {

    @GET
    public String index() {
        return "Index ";

    }

}
