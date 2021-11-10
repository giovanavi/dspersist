package exemplos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

//store: armazenar

public class TesteArqPropGravacao {
    public static void main(String [] args) {
        Properties prop = new Properties();

        try{
            prop.setProperty("database", "localhost");
            prop.setProperty("dbuser", "giovana");
            prop.setProperty("dbpasword", "senha");
            prop.store(new FileOutputStream("config.properties"), null);
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
