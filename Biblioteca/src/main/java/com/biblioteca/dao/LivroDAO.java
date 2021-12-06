package com.biblioteca.dao;

import com.biblioteca.model.Livro;

import java.util.List;

public interface LivroDAO {
    public void save(Livro l);
    public void update(Livro l);
    public void delete(int id);
    public Livro find(int id);
    public List<Livro> find();
    public void emprestarLivro(int id);

}
