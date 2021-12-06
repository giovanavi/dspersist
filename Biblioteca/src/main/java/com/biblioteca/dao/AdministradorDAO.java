package com.biblioteca.dao;

import com.biblioteca.model.Administrador;

import java.util.List;

public interface AdministradorDAO {
    public boolean login(Administrador adm);
    public void save(Administrador adm);
    public void delete(int id);
    public Administrador find(int id);
    public List<Administrador> find();

}
