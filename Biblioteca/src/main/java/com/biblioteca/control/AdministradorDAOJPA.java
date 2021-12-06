package com.biblioteca.control;

import com.biblioteca.dao.AdministradorDAO;
import com.biblioteca.dao.DAOException;
import com.biblioteca.model.Administrador;
import com.biblioteca.model.Livro;
import com.biblioteca.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class AdministradorDAOJPA implements AdministradorDAO {

    @Override
    public boolean login(Administrador adm) {
        return false;
    }

    @Override
    public void save(Administrador adm) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            JPAUtil.beginTransaction();
            em.persist(adm);
//            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso.");
            JPAUtil.commit();
        } catch (Exception e) {
            JPAUtil.rollback();
            e.printStackTrace();
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public void delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            JPAUtil.beginTransaction();
            Administrador adm = find(id);
            em.remove(adm);
//            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso.");
            JPAUtil.commit();
        } catch (Exception e) {
            JPAUtil.rollback();
            e.printStackTrace();
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public Administrador find(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        Administrador adm = null;
        try{
            adm = em.createQuery("select a from Administrador as a where id = :id", Administrador.class)
                    .setParameter("id", id).getSingleResult();
        }catch (DAOException e){
            e.printStackTrace();
        }finally {
            JPAUtil.closeEntityManager();
        }
        return adm;
    }

    @Override
    public List<Administrador> find() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Administrador> lista = null;
        try{
            lista = em.createQuery("select a from Administrador as a", Administrador.class).getResultList();
        }catch (DAOException e){
            e.printStackTrace();
        }finally {
            JPAUtil.closeEntityManager();
        }
        return lista;
    }


}
