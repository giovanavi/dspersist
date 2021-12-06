package com.biblioteca.app;

import com.biblioteca.control.LivroDAOJPA;
import com.biblioteca.dao.LivroDAO;
import com.biblioteca.model.Livro;

public class ExemploLivro {
    public static void main(String[] args) {
        LivroDAO livroDAO = new LivroDAOJPA();
//        Livro livro = livroDAO.find(2);
//        livroDAO.emprestarLivro(2);
        Livro livro = new Livro("123456", "HP4", "JKR", "ROCCO");
        livroDAO.save(livro);
//        Livro livro1 = livroDAO.find(1);
        livroDAO.delete(1);

//        livroDAO.update(livro);
    }
}
