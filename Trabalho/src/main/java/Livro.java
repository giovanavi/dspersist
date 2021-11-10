import java.io.*;

public class Livro implements Serializable{
    private int isbn;
    private String titulo, editora, autor, ano_publicacao;

    public Livro(){

    }
    public Livro(int isbn, String titulo, String autor, String editora ,String ano_publicacao){
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.ano_publicacao = ano_publicacao;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAno_publicacao() {
        return ano_publicacao;
    }

    public void setAno_publicacao(String ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }

    @Override
    public String toString() {
        return "Livro{" + "isbn=" + isbn +
                ", titulo='" + titulo + '\'' +
                ", editora='" + editora + '\'' +
                ", autor='" + autor + '\'' +
                ", ano_publicacao='" + ano_publicacao + '\'' +
                '}';
    }
}
