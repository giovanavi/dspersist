package dspersist.ui;

import dspersist.dao.ContatoDAO;
import dspersist.dao.ContatoDAOJDBC;
import dspersist.model.Contato;
    
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ContatoDAO contatoDAO = new ContatoDAOJDBC();
        List<Contato> contatos = contatoDAO.findAll();
        for (Contato c: contatos) {
            System.out.println(c);
        }

//        contatoDAO.read(new Contato("Arthur","arthura@gmail.com", "Rua A,18"));

//        contatos = contatoDAO.findAll();
//        for (Contato c: contatos) {
//            System.out.println(c);
//        }
    }
}