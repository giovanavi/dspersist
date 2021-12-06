package com.biblioteca.dao;

import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Usuario;

import java.util.List;

public interface EmprestimoDAO {
    public void add(Emprestimo emprestimo);
    public void update(Emprestimo emprestimo);
    public Emprestimo find(int id);
    public List<Emprestimo> find();

}
