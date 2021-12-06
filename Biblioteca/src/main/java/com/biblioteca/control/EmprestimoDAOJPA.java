package com.biblioteca.control;

import com.biblioteca.dao.DAOException;
import com.biblioteca.dao.EmprestimoDAO;
import com.biblioteca.model.Emprestimo;
import com.biblioteca.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class EmprestimoDAOJPA implements EmprestimoDAO {
    @Override
    public void add(Emprestimo emprestimo) {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            JPAUtil.beginTransaction();
            em.persist(emprestimo);
            JPAUtil.commit();
        }catch (DAOException e){
            JPAUtil.rollback();
            e.printStackTrace();
        }finally{
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public void update(Emprestimo emprestimo) {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            JPAUtil.beginTransaction();
            em.merge(emprestimo);
            JPAUtil.commit();
        }catch (DAOException e){
            JPAUtil.rollback();
            e.printStackTrace();
        }finally{
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public Emprestimo find(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        Emprestimo emprestimo = null;
        try{
            emprestimo = em.createQuery("select e from Emprestimo as e where id = :id", Emprestimo.class)
                    .setParameter("id", id).getSingleResult();
        }catch (DAOException e){
            e.printStackTrace();
        }finally {
            JPAUtil.closeEntityManager();
        }
        return emprestimo;
    }

    @Override
    public List<Emprestimo> find() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Emprestimo> lista = null;
        try{
            lista = em.createQuery("select e from Emprestimo as e", Emprestimo.class).getResultList();
        }catch (DAOException e){
            e.printStackTrace();
        }finally {
            JPAUtil.closeEntityManager();
        }
        return lista;
    }


}
