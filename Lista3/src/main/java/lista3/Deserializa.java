package lista3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.List;

public class Deserializa {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            //API JAVA
            System.out.println("Java");
            FileInputStream fileIn = new FileInputStream("pessoa.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<Pessoa> pessoa = (List<Pessoa>) in.readObject();
            System.out.println(pessoa);
            in.close();
            fileIn.close();

            System.out.println("JSON");
            List<Pessoa> p1 = mapper.readValue(new File("pessoa.json"), List.class);
            System.out.println(p1);

            System.out.println("XML");
            File file = new File("pessoa.xml");
            XmlMapper xmlMapper = new XmlMapper();
            String xml = inputStreamToString(new FileInputStream(file));
            List<Pessoa> value = xmlMapper.readValue(xml, List.class);
            System.out.println(value);
        }catch(Exception ex) {
            ex.printStackTrace();
        }

    }
    public static String inputStreamToString(FileInputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}

