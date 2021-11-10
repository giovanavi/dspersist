package exemplos;

import java.io.*;

//lendo uma entrada até um "\n" e imprimindo.

public class TesteEntrada3 {
    public static void main(String [] args) throws IOException{
        InputStream is = System.in;
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();

        while(s!=null){
            System.out.println(s);
            s = br.readLine();
        }
    }
}