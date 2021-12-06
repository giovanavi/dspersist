package com.biblioteca.control;

import com.biblioteca.dao.DAOException;
import com.biblioteca.dao.LivroDAO;
import com.biblioteca.model.Livro;
import com.biblioteca.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class LivroDAOJPA implements LivroDAO {
    @Override
    public void save(Livro livro) {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            JPAUtil.beginTransaction();
            em.persist(livro);
            JPAUtil.commit();
        }catch (DAOException e){
            JPAUtil.rollback();
            e.printStackTrace();
        }finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public void update(Livro livro) {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            JPAUtil.beginTransaction();
            em.merge(livro);
            JPAUtil.commit();
        }catch (DAOException e){
            JPAUtil.rollback();
            e.printStackTrace();
        }finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public void delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            Livro livro  = find(id);
            JPAUtil.beginTransaction();
            em.remove(livro);
            JPAUtil.commit();
        }catch (DAOException e){
            JPAUtil.rollback();
            e.printStackTrace();
        }finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public Livro find(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        Livro livro = null;
        try{
            livro = em.createQuery("select l from Livro as l where id = :id", Livro.class).setParameter("id", id).getSingleResult();
        }catch (DAOException e){
            e.printStackTrace();
        }finally {
            JPAUtil.closeEntityManager();
        }
        return livro;
    }

    @Override
    public List<Livro> find() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Livro> lista = null;
        try{
            lista = em.createQuery("select l from Livro as l", Livro.class).getResultList();
        }catch (DAOException e){
            e.printStackTrace();
        }finally {
            JPAUtil.closeEntityManager();
        }
        return lista;
    }

    @Override
    public void emprestarLivro(int id) {
        Livro livro = find(id);
        livro.setEmprestado(true);
        update(livro);
    }

    public void devolverLivro(int id) {
        Livro livro = find(id);
        livro.setEmprestado(false);
        update(livro);
    }
}
