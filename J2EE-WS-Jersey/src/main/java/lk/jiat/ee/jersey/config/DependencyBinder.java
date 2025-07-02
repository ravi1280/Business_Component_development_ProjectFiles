package lk.jiat.ee.jersey.config;

import lk.jiat.ee.jersey.model.User;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class DependencyBinder extends AbstractBinder {

    @Override
    protected void configure() {
        System.out.println("DependencyBinder...");
        bind(User.class).to(User.class);

    }
}
