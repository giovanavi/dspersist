package exemplos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class Serializando {
    public static void main(String[]args) {
        Pessoa p = new Pessoa();
        p.setNome("Ana");
        p.setEmail("ana@gmail.com");
        p.setFone("88123456789");

        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream("src/teste1/serializa/pessoa.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(p);
            out.close();
            fileOut.close();
            System.out.println("Serializado");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}