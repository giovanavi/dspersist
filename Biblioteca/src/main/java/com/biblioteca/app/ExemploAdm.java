package com.biblioteca.app;

import com.biblioteca.control.AdministradorDAOJPA;
import com.biblioteca.dao.AdministradorDAO;
import com.biblioteca.model.Administrador;

public class ExemploAdm {
    public static void main(String[] args) {
        AdministradorDAO administradorDAO = new AdministradorDAOJPA();
        Administrador administrador = new Administrador("login", "senha");
        administradorDAO.save(administrador);

    }
}
