package exemplos;

import java.io.FileInputStream;
import java.io.ObjectInputStream;


public class Deserializando {
    public static void main(String [] args) {
        Pessoa p = null;
        try {
            FileInputStream fileIn = new FileInputStream("src/teste1/serializa/pessoa.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            p = (Pessoa) in.readObject();
            in.close();
            fileIn.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(p);
    }
}