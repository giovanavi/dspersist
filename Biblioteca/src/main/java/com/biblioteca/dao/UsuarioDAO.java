package com.biblioteca.dao;

import com.biblioteca.model.Usuario;

import java.util.List;

public interface UsuarioDAO {
    public void save(Usuario u);
    public void update(Usuario u);
    public void delete(int id);
    public Usuario find(int id);
    public List<Usuario> find();
}
