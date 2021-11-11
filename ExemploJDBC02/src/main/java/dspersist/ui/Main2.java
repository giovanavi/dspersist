package dspersist.ui;

import dspersist.dao.ContatoDAO;
import dspersist.dao.ContatoDAOJDBC;
import dspersist.model.Contato;

import javax.swing.*;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        ContatoDAO contatoDAO = new ContatoDAOJDBC();
        List<Contato> contatos = contatoDAO.findAll();

        StringBuffer sb = new StringBuffer();
        for (Contato c: contatos) {
            sb.append(c);
            sb.append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }
}
