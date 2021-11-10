import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca implements Serializable {
    private List<Livro> livros;

    public Biblioteca(){
        this.livros = new ArrayList<>();
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        return "Biblioteca{" + "livros = " + livros + '}';
    }
}
