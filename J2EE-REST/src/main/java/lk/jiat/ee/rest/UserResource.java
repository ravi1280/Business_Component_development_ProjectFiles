package lk.jiat.ee.rest;


import jakarta.json.JsonObject;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

import java.util.Arrays;
import java.util.List;

@Path("/users")
public class UserResource {

    @Context
    private UriInfo uriInfo;

    @Context
    private HttpServletRequest servletRequest;


    @GET
    public List<String> getUsers() {
        System.out.println(uriInfo.getAbsolutePath());
        System.out.println(servletRequest.getRequestURI());

        //get session details
        System.out.println(servletRequest.getSession());
        return Arrays.asList(new String[]{"John" ," Doe" ,"Flex"});

    }

    @GET
    public String getDetails(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        System.out.println(request);
        System.out.println(response);
        return request.getRequestURI();
    }

    @GET
    @Path("/{id}")
    public User getUserDetail(@PathParam("id") Integer id) {
        //Find User by ID
        System.out.println("getUser1 : " + id);

        User user = new User();
        user.setId(id);
        user.setName("Ravishka");
        user.setEmail("ravishka@gmail.com");

        return user ;
    }


    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    public String getUser(@PathParam("id") String id) {
        System.out.println("getUser2 : " + id);
        return "<h1>Hello World</h1> <hr/> <p>User id :"+id+"</p>" ;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    public String getUser1(JsonObject user,@QueryParam("type") String type) {
        System.out.println("getUser: " + user.toString());
        return "<h1>Hello World</h1> <hr/> <p>User id :"+user+"</p> <hr/> <p>Type :"+type+"</p>" ;
    }

    @POST
    @Path("u1")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    public String getUser2(@MatrixParam("detail") String detail,@MatrixParam("lan") String lan) {
        System.out.println("getDetail: " + detail +" lan: " + lan);
        return "<h1>Hello World</h1> <hr/> <p>User id :"+detail+"</p> <hr/> <p>Lan : "+lan+"</p>" ;
    }
}
