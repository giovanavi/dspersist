import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
//    public static void main(String[] args) throws Exception {
//        Properties prop = new Properties();
////      FileInputStream fis = new FileInputStream("config.properties");
//        InputStream is = AppConfig.class.getResourceAsStream("config.properties");
//        prop.load(is);
//        System.out.println(prop.getProperty("jdbc.url"));
//        System.out.println(prop.getProperty("jdbc.usuario"));
//        System.out.println(prop.getProperty("jdbc.senha"));
//    }

    public static Properties PROPS = null;

    static {
        try {
            Properties prop = new Properties();
            InputStream is = AppConfig.class.getResourceAsStream("config.properties");
            prop.load(is);
            PROPS = prop;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
