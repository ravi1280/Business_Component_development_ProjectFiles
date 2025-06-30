package lk.jiat.ee.rest;


import jakarta.ws.rs.*;

@Path("/")
public class Home {

    @GET
    public String   home() {
        System.out.println("Home");
        return "hello World";
    }

    @Path("user")
    @GET
    public String user(){
        System.out.println("user");
        return "hello User";
    }

    @POST
    public String   post() {
        System.out.println("Post");
        return "hello Post";
    }

    @PUT
    public String   put() {
        System.out.println("Put");
        return "hello Put";
    }
    @HEAD
    public String   head() {
        System.out.println("Head");
        return "hello Head";
    }
    @OPTIONS
    public String   options() {
        System.out.println("Options");
        return "hello Option";
    }
    @DELETE
    public String   delete() {
        System.out.println("Delete");
        return "hello Delete";
    }
}
