package lista2;

import java.io.*;
import java.util.Properties;

public class Questao203 {
    public static void main(String[]args) throws FileNotFoundException, IOException  {
        if(args.length < 1) {
            System.out.println("Insira o nome de seu arquivo de texto");
            System.exit(0);
        }
        String arq = args[0];

        Properties prop = new Properties();
//		prop.load(new FileInputStream("config.properties"));
        prop.load(Questao203.class.getClassLoader().getResourceAsStream("config.properties"));
        int linha_inicial = Integer.parseInt(prop.getProperty("linha_inicial"));
        int linha_final = Integer.parseInt(prop.getProperty("linha_final"));

        InputStream is = new FileInputStream(arq);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String str = "";

        for(int j=0; j<=linha_final;j++) {
            str = br.readLine();
            if(j>=linha_inicial) {
                if(str!=null) {
                    System.out.println(str);
                }
            }
        }
        br.close();
    }
}
