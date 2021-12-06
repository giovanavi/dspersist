package com.biblioteca.control;

import com.biblioteca.dao.DAOException;
import com.biblioteca.dao.UsuarioDAO;
import com.biblioteca.model.Usuario;
import com.biblioteca.util.JPAUtil;
import javax.persistence.EntityManager;
import java.util.List;

public class UsuarioDAOJPA implements UsuarioDAO {

    @Override
    public void save(Usuario usuario) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            JPAUtil.beginTransaction();
            em.persist(usuario);
            JPAUtil.commit();
        }catch(DAOException e){
            JPAUtil.rollback();
            e.printStackTrace();
        }finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public void update(Usuario usuario) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            JPAUtil.beginTransaction();
            em.merge(usuario);
            JPAUtil.commit();
        }catch(DAOException e){
            JPAUtil.rollback();
            e.printStackTrace();
        }finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public void delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        Usuario usuario = find(id);
        try {
            JPAUtil.beginTransaction();
            em.remove(usuario);
            JPAUtil.commit();
        }catch (DAOException e){
            JPAUtil.rollback();
            e.printStackTrace();
        }finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public Usuario find(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        Usuario usuario = null;
        try {
            usuario = (Usuario) em.createQuery("select u from Usuario as u where id = :id").
                    setParameter("id", id).getSingleResult();
        }catch (DAOException e){
            e.printStackTrace();
        }finally {
            JPAUtil.closeEntityManager();
        }
        return usuario ;
    }

    @Override
    public List<Usuario> find() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Usuario> lista = null;
        try {
            lista = em.createQuery("select u from Usuario as u", Usuario.class)
                    .getResultList();
        }catch (DAOException e){
            e.printStackTrace();
        }finally {
            JPAUtil.closeEntityManager();
        }
        return lista ;
    }
}
