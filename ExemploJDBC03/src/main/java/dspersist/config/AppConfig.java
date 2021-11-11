package dspersist.config;

import java.io.*;
import java.util.Properties;

public class AppConfig {
    public static Properties PROPS = null;

    static {
        try {
            Properties prop = new Properties();
            InputStream is = AppConfig.class.getResourceAsStream("/config.properties");
            prop.load(is);
            PROPS = prop;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

