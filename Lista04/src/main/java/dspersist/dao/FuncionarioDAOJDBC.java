package dspersist.dao;

import dspersist.model.Funcionario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAOJDBC implements FuncionarioDAO {
    @Override

    //SAVE
    public void create(Funcionario f) {
        Connection conex = null;
        try {
            conex = ConnectionFactory.getConnection();
            String insert = "insert into funcionarios (cpf, matricula, nome, email, telefone) values (?,?,?,?,?)";
            PreparedStatement ps = conex.prepareStatement(insert);
            ps.setString(1, f.getCpf());
            ps.setString(2, f.getMatricula());
            ps.setString(3, f.getNome());
            ps.setString(4, f.getEmail());
            ps.setString(5, f.getTelefone());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso.");
        } catch (SQLException e) {
            throw new DAOException("Erro ao executar operação. ",e);
        }finally {
            if(conex!=null) {
                try {
                    conex.close();
                } catch (SQLException e) {
                    throw new DAOException("Erro ao fechar conexão com o banco. ",e);
                }
            }
        }
    }

    //UPDATE
    public void update(Funcionario f){
        Connection conex = null;
        try {
            conex = ConnectionFactory.getConnection();
            String sql = "update funcionarios set cpf=?, matricula=?, nome=?, email=?, telefone=? where  id=?";
            PreparedStatement ps = conex.prepareStatement(sql);
            ps.setString(1,f.getCpf());
            ps.setString(2, f.getMatricula());
            ps.setString(3, f.getNome());
            ps.setString(4, f.getEmail());
            ps.setString(5, f.getTelefone());
            ps.setInt(6, f.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionário atualizado com sucesso.");
        } catch (SQLException e) {
            throw new DAOException("Erro ao executao operação. ",e);
        }finally {
            if(conex!=null) {
                try {
                    conex.close();
                } catch (SQLException e) {
                    throw new DAOException("Erro ao fechar conexão com o banco. ",e);
                }
            }
        }
    }

    //DELETE BY CPF
    public void deleteByCpf(String cpf){
        Connection conex =  null;
        try {
            conex = ConnectionFactory.getConnection();
            String delete_id = "delete from funcionarios where cpf = ?";
            PreparedStatement ps = conex.prepareStatement(delete_id);
            ps.setString(1, cpf);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionário excluído com sucesso.");
        } catch (SQLException e) {
            throw new DAOException("Falha na excução da operação. ", e);
        }finally {
            if(conex!=null) {
                try {
                    conex.close();
                } catch (SQLException e) {
                    throw new DAOException("Erro ao fechar conexão com o banco. " , e);
                }
            }
        }

    }
    //DELETE BY MATRICULA

    public void deleteByMatricula(String matricula){
        Connection conex =  null;
        try {
            conex = ConnectionFactory.getConnection();
            String delete_id = "delete from funcionarios where matricula = ?";
            PreparedStatement ps = conex.prepareStatement(delete_id);
            ps.setString(1, matricula);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionário excluído com sucesso.");
        } catch (SQLException e) {
            throw new DAOException("Falha na excução da operação. ", e);
        }finally {
            if(conex!=null) {
                try {
                    conex.close();
                } catch (SQLException e) {
                    throw new DAOException("Erro ao fechar conexão com o banco. " , e);
                }
            }
        }
    }

    //MAP
    public Funcionario map(ResultSet rs) throws SQLException {
        Funcionario f = new Funcionario();
        f.setId(rs.getInt("id"));
        f.setCpf(rs.getString("cpf"));
        f.setMatricula(rs.getString("matricula"));
        f.setNome(rs.getString("nome"));
        f.setEmail(rs.getString("email"));
        f.setTelefone(rs.getString("telefone"));
        return f;
    }

    //FIND ALL
    @Override
    public List<Funcionario> findAll() {
        List<Funcionario> lista = new ArrayList<>();
        Connection conex = null;
        try {
            conex = ConnectionFactory.getConnection();
            String sql = "select * from funcionarios order by id";
            PreparedStatement st = conex.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Funcionario funcionario = map(rs);
                lista.add(funcionario);
            }
        } catch (SQLException e) {
            throw new DAOException("Erro ao executar operação. ", e);
        }finally {
            if(conex!=null) {
                try {
                    conex.close();
                } catch (SQLException e) {
                    throw new DAOException("Erro ao fechar conexão com o banco.", e);
                }
            }
        }
        return lista;
    }

    //FIND BY NAME
    @Override
    public List<Funcionario> findByName(String name) {
        Connection conex = null;
        List<Funcionario> lista = new ArrayList<>();
        try {
            conex = ConnectionFactory.getConnection();
            String sql = "select * from funcionarios where upper(nome) like ? order by id asc";
            PreparedStatement ps = conex.prepareStatement(sql);
            ps.setString(1, "%"+name.toUpperCase()+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Funcionario funcionario = map(rs);
                lista.add(funcionario);
            }
        } catch (SQLException e) {
            throw new DAOException("Erro ao executar operação. ", e);
        }finally {
            if(conex!=null){
                try {
                    conex.close();
                } catch (SQLException e) {
                    throw new DAOException("Erro ao fechar conexão com o banco. ", e);
                }
            }
        }

        return lista;
    }

    //FIND BY CPF
    @Override
    public Funcionario findByCpf(String cpf) {
        Connection conex = null;
        Funcionario f = null;
        try {
            conex = ConnectionFactory.getConnection();
            String sql = "select * from funcionarios where cpf = ?";
            PreparedStatement ps = conex.prepareStatement(sql);
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                f = map(rs);
            }
        } catch (SQLException e) {
            throw new DAOException("Erro ao executar operação. ", e);
        }finally {
            if(conex!=null){
                try {
                    conex.close();
                } catch (SQLException e) {
                    throw new DAOException("Erro ao fechar conexão com o banco. ", e);
                }
            }
        }
        return f;
    }

    //FIND BY MATRICULA
    @Override
    public Funcionario findByMatricula(String matricula) {
        Connection conex = null;
        Funcionario f = null;
        try {
            conex = ConnectionFactory.getConnection();
            String find = "select * from funcionarios where matricula = ?";
            PreparedStatement ps = conex.prepareStatement(find);
            ps.setString(1, matricula);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                f = map(rs);
            }
        } catch (SQLException e) {
            throw new DAOException("Erro ao executar operação. ", e);
        }finally {
            if(conex!=null){
                try {
                    conex.close();
                } catch (SQLException e) {
                    throw new DAOException("Erro ao fechar conexão com o banco. ", e);
                }
            }
        }

        return f;
    }
}
