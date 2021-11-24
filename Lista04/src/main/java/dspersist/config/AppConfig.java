package dspersist.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    public static Properties PROP = null;

    static {
        try {
            Properties prop = new Properties();
            InputStream is = AppConfig.class.getResourceAsStream("/config.properties");
            prop.load(is);
            PROP = prop;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
