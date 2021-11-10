package exemplos;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//load: carrega

public class TesteArqPropLeitura {
    public static void main(String[]args) {
        Properties prop = new Properties();

        try {
            prop.load(new FileInputStream("config.properties"));
//			prop.load(testeArqPropLeitura.class.getClassLoader().getResourceAsStream("config2.properties"));
            System.out.println(	prop.getProperty("database"));
            System.out.println(prop.getProperty("dbuser"));
            System.out.println(prop.getProperty("dbpasword"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
