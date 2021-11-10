package lista3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Serializa {
    public static void main(String[] args) {
        Pessoa p1 = new Pessoa(1,"Ana Leticia", "ana@gmail.com","123456789");
        Pessoa p2 = new Pessoa(2,"João Miguel", "joao@gmail.com", "987654321");
        Pessoa p3 = new Pessoa(3,"Maria Joana", "maria@gmail.com", "019283746");
        Pessoa p4 = new Pessoa(4,"Marcos Suel", "marcos@gmail.com", "526381930");

        ObjectMapper objectMapper = new ObjectMapper();
        XmlMapper xmlMapper = new XmlMapper();
        List<Pessoa> lista = new ArrayList<>();
        lista.add(p1); lista.add(p2); lista.add(p3); lista.add(p4);

        FileOutputStream fileOut = null;
        try {
            //API JAVA
            fileOut = new FileOutputStream("pessoa.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(lista);
            out.close();
            fileOut.close();
            System.out.println("Serializado");
            //JAVA TO JSON
            objectMapper.writeValue(new File("pessoa.json"), lista);
            System.out.println("Serializado");
            //JAVA TO XML
            xmlMapper.writeValue(new File("pessoa.xml"), lista);
            System.out.println("Serializado");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

