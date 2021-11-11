package dspersist.dao;

import dspersist.model.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAOJDBC implements ContatoDAO{

    public Contato map(ResultSet st) throws SQLException{
        Contato contato = new Contato();
        contato.setId(st.getInt("id"));
        contato.setNome(st.getString("nome"));
        contato.setEmail(st.getString("email"));
        contato.setEndereco(st.getString("endereco"));
        return contato;
    }

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
                Contato contato = map(rs);
                contatos.add(contato);
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

}
