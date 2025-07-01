package lk.jiat.ee.jersey.config;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;


@ApplicationPath("/api/v1")
public class AppConfig extends ResourceConfig {
    public AppConfig() {
        packages("lk.jiat.ee.jersey.controller");
        packages("lk.jiat.ee.jersey.middleware");
    }
}
