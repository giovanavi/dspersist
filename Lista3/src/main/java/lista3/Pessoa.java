package lista3;

import java.io.Serializable;

public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nome, email, fone;

    public Pessoa() {
    }

    public Pessoa(int id, String nome, String email, String fone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.fone = fone;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFone() {
        return fone;
    }
    public void setFone(String fone) {
        this.fone = fone;
    }
    public int getId() {
        return id;
    }
    public void setId(){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pessoa [id=" +id+ ", nome=" + nome + ", email=" + email + ", fone=" + fone + "]";
    }
}
