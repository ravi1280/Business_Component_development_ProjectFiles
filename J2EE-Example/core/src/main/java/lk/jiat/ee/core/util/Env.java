package lk.jiat.ee.core.util;

import java.io.InputStream;
import java.util.Properties;

public class Env {
    public static Properties props = new Properties();
    static {
        try {
          InputStream stream = Env.class.getClassLoader().getResourceAsStream("application.properties");
          props.load(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getProperty(String key) {
        return props.getProperty(key);
    }
}
