import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CadastroApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            if(sc.nextLine().equals("fim"))
                break;
            System.out.println("ISBN");
            int isbn = Integer.parseInt(sc.nextLine());
            System.out.println("Titulo");
            String titulo = sc.nextLine();
            System.out.println("Autor");
            String autor = sc.nextLine();
            System.out.println("Editora");
            String editora = sc.nextLine();
            System.out.println("Ano de publicação");
            String ano_publi = sc.nextLine();
            addLivro(new Livro(isbn, titulo, autor, editora ,ano_publi));
        }
    }

    public static void addLivro(Livro livro){
        try {
            FileWriter fw =new FileWriter(new File("biblioteca.csv"), true);
            PrintWriter pw = new PrintWriter(fw);

            StringBuilder sb = new StringBuilder();
            sb.append(livro.getIsbn());
            sb.append(",");
            sb.append(livro.getTitulo());
            sb.append(",");
            sb.append(livro.getAutor());
            sb.append(",");
            sb.append(livro.getEditora());
            sb.append(",");
            sb.append(livro.getAno_publicacao());

            pw.println(sb);
            pw.flush();
            pw.close();
            System.out.println("Salvo: "+sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
