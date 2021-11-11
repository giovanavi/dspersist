package dspersist.dao;

import dspersist.model.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAOJDBC implements ContatoDAO{

    @Override
    public List<Contato> findAll() {
        List<Contato> contatos = new ArrayList<Contato>();
        Connection conex = null;

        try {
            conex = ConenctionFactory.getConnection();
            System.out.println("Conexão OK!!");

            String sql = "select * from contatos";
            PreparedStatement st = conex.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Contato c = new Contato();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                c.setEndereco(rs.getString("endereco"));
                contatos.add(c);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }finally {
            try {
                conex.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }

        return contatos;
    }

    @Override
    public void read(Contato contato) {
        Connection conex = null;

        try {
            conex = ConenctionFactory.getConnection();
            System.out.println("Conexão OK!!");

            String sql = "insert into contatos (nome, email, endereco) values (?,?,?)";
            PreparedStatement ps = conex.prepareStatement(sql);
            ps.setString(1,contato.getNome());
            ps.setString(2,contato.getEmail());
            ps.setString(3,contato.getEndereco());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }finally {
            try {
                conex.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
