package lk.jiat.ee.jersey.controller;


import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lk.jiat.ee.jersey.model.User;
import org.apache.commons.io.FilenameUtils;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.server.mvc.Viewable;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class HomeController {

    @Inject
    private User user;

    @Context
    ServletContext servletContext;

    @GET
    public Viewable index() {
        System.out.println(user);
        Map<String, Object> model = new HashMap<>();
        model.put("message", "Hello World");

        return new Viewable("/index", model);
    }

    @POST
    @Path("/file_upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response fileUpload( @FormDataParam("file") FormDataBodyPart file, @FormDataParam("files[]") FormDataBodyPart files) {
        System.out.println("Uploading file...");

        //One file
        InputStream stream = file.getContent();
        ContentDisposition contentDisposition = file.getContentDisposition();
        String fileName = contentDisposition.getFileName();
        String FileExtension = FilenameUtils.getExtension(fileName);
        System.out.println(FileExtension);

        try {
            int read = 0;
            byte[] buffer = new byte[1024];
           String realPath = servletContext.getRealPath("/");
            java.nio.file.Path path = Paths.get(realPath+"/upload");
           if(!Files.exists(path)){
               try {
                   Files.createDirectory(path);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }

            FileOutputStream fileOutputStream =
                    new FileOutputStream(new File(path+"/" + System.currentTimeMillis() + "." + FileExtension));

            while ((read = stream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, read);

            }
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Many files
        files.getParent().getBodyParts().forEach(part->{


            InputStream is = part.getEntityAs(InputStream.class);

            String fileName02 = files.getContentDisposition().getFileName();
            String FileExtension02 = FilenameUtils.getExtension(fileName02);
            System.out.println(FileExtension02);

            try {
                int read = 0;
                byte[] buffer = new byte[1024];
                String realPath = servletContext.getRealPath("/");
                java.nio.file.Path path = Paths.get(realPath+"/upload");
                if(!Files.exists(path)){
                    try {
                        Files.createDirectory(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                FileOutputStream fileOutputStream =
                        new FileOutputStream(new File(path+"/" + System.currentTimeMillis() + "." + FileExtension02));

                while ((read = is.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, read);

                }
                Thread.sleep(1000);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });


        return Response.ok().build();

    }
}
