import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Serializa {
    public static void main(String[] args) {
        File file = new File("biblioteca.csv");

        List<Livro> livros = readFromCSV(file);

        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setLivros(livros);

        convertToXml(biblioteca);
        convertToJson(biblioteca);
    }

    private static List<Livro> readFromCSV(File file){
        List<Livro>lista = new ArrayList<>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line != null){
                String[] atributos = line.split(",");
                Livro livro = createLivro(atributos);
                lista.add(livro);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static Livro createLivro(String[] atributos){
        Livro livro = new Livro();
        livro.setIsbn(Integer.parseInt(atributos[0]));
        livro.setTitulo(atributos[1]);
        livro.setAutor(atributos[2]);
        livro.setEditora(atributos[3]);
        livro.setAno_publicacao(atributos[4]);
        return livro;
    }

    private static void convertToJava(Biblioteca livros){
        try {
            FileOutputStream fos = new FileOutputStream("biblioteca.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(livros);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void convertToXml(Biblioteca livros) {
        XmlMapper mappper = new XmlMapper();
        try {
            mappper.writeValue(new File("biblioteca.xml"), livros);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void convertToJson(Biblioteca livros){
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("biblioteca.json"), livros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
