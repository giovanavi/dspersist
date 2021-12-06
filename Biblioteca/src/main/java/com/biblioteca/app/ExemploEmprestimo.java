package com.biblioteca.app;

import com.biblioteca.model.*;
import com.biblioteca.control.*;
import com.biblioteca.dao.*;


public class ExemploEmprestimo {
    public static void main(String[] args) {
        LivroDAO livroDAO = new LivroDAOJPA();
        Livro livro =  livroDAO.find(1);
        UsuarioDAO usuarioDAO = new UsuarioDAOJPA();
        Usuario usuario = usuarioDAO.find(1);

        EmprestimoDAO emprestimoDAO = new EmprestimoDAOJPA();
        Emprestimo emprestimo = new Emprestimo("20/12/2020", "20/01/2021", usuario, livro);
        emprestimoDAO.add(emprestimo);
    }
}
