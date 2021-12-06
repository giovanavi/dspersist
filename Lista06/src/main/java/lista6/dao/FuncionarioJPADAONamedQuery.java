package lista6.dao;

import lista6.model.Funcionario;
import lista6.util.JPAUtil;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;

@Repository
@Primary
public class FuncionarioJPADAONamedQuery implements FuncionarioDAO{
    @Override
    public void create(Funcionario f) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            JPAUtil.beginTransaction();
            em.persist(f);
            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso.");
            JPAUtil.commit();
        }catch(Exception e){
            JPAUtil.rollback();
            e.printStackTrace();
        }finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public void update(Funcionario f) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            JPAUtil.beginTransaction();
            em.createNamedQuery("Funcionario.update")
                    .setParameter("cpf", f.getCpf())
                    .setParameter("matricula", f.getMatricula())
                    .setParameter("nome", f.getNome())
                    .setParameter("email", f.getEmail())
                    .setParameter("telefone", f.getTelefone())
                    .setParameter("id", f.getId())
                    .executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionário atualizado com sucesso.");
            JPAUtil.commit();
        } catch (Exception e) {
            JPAUtil.rollback();
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public void deleteByCpf(String cpf) {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            JPAUtil.beginTransaction();
            em.createNamedQuery("Funcionario.deleteByCpf").setParameter("cpf", cpf).executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionário deletado com suceso.");
            JPAUtil.commit();
        }catch(Exception e){
            JPAUtil.rollback();
        }finally{
            JPAUtil.closeEntityManager();
        }

    }

    @Override
    public void deleteByMatricula(String matricula) {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            JPAUtil.beginTransaction();
            em.createNamedQuery("Funcionario.deleteByMatricula").setParameter("matricula", matricula)
                    .executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionário deletado com sucesso.");
            JPAUtil.commit();
        }catch(Exception e){
            JPAUtil.rollback();
        }finally{
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public List<Funcionario> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Funcionario> lista = em.createNamedQuery("Funcionario.findAll", Funcionario.class)
                .getResultList();
        JPAUtil.closeEntityManager();
        return lista;
    }

    @Override
    public List<Funcionario> findByName(String name) {
        EntityManager em = JPAUtil.getEntityManager();
        List<Funcionario> lista = em.createNamedQuery("Funcionario.findByName", Funcionario.class)
                .setParameter("nome", "%"+name+"%").getResultList();
        JPAUtil.closeEntityManager();
        return lista;
    }

    @Override
    public Funcionario findByCpf(String cpf) {
        EntityManager em = JPAUtil.getEntityManager();
        Funcionario f = null;
        try {
            f = em.createNamedQuery("Funcionario.findByCpf", Funcionario.class)
                    .setParameter("cpf", cpf).getSingleResult();
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            JPAUtil.closeEntityManager();
        }
        return f;
    }

    @Override
    public Funcionario findByMatricula(String matricula) {
        EntityManager em = JPAUtil.getEntityManager();
        Funcionario f = null;
        try {
            f = em.createNamedQuery("Funcionario.findByMatricula", Funcionario.class)
                    .setParameter("matricula", matricula).getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JPAUtil.closeEntityManager();
        }
        return f;
    }
}
