package com.biblioteca.app;

import com.biblioteca.control.UsuarioDAOJPA;
import com.biblioteca.dao.UsuarioDAO;
import com.biblioteca.model.Usuario;

public class ExemploUsuario {
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAOJPA();
        Usuario usuario = new Usuario("Giovana Vieira", "123.123.123-12", "giovana@gmail.com", "Rua G, 12");
        usuarioDAO.save(usuario);
        usuarioDAO.delete(2);
    }
}
