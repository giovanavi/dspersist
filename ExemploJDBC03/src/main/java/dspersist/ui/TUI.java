package dspersist.ui;

import dspersist.dao.ContatoDAO;
import dspersist.dao.ContatoDAOJDBC;
import dspersist.model.Contato;
    
import java.util.List;

public class TUI {
    public static void main(String[] args) {
        ContatoDAO contatoDAO = new ContatoDAOJDBC();
        List<Contato> contatos = contatoDAO.findAll();
        for (Contato c: contatos) {
            System.out.println(c);
        }

    }
}