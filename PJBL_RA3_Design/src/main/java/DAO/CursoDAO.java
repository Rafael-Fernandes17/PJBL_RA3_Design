package DAO;

import config.EMF;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import model.Curso;
import model.Disciplina;

import java.util.List;

public class CursoDAO {
    private static CursoDAO instanciaCursoDAO;

    private CursoDAO() {}

    public static CursoDAO getInstancia() {
        if (instanciaCursoDAO == null) {
            instanciaCursoDAO = new CursoDAO();
        }

        return instanciaCursoDAO;
    }

    public boolean cadastrarCurso(Curso cursoNovo) {
        EntityManager em = EMF.getInstancia().gerarEntityMenager();

        try {
            em.getTransaction().begin();
            em.persist(cursoNovo);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback(); // Se der erro, desfaz
            e.printStackTrace();
            return false;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public boolean excluirCurso(Curso curso) {
        EntityManager em = EMF.getInstancia().gerarEntityMenager();

        try {
            em.getTransaction().begin();
            Curso cursoGerenciado = em.merge(curso);
            em.remove(cursoGerenciado);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback(); // Se der erro, desfaz
            e.printStackTrace();
            return false;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Curso buscarCurso(String nomeCurso) {
        String jpql = "SELECT curso FROM Curso curso WHERE curso.nome = :nomeCurso";

        EntityManager em = EMF.getInstancia().gerarEntityMenager();

        try {
            Curso resultado =
                    em.createQuery(jpql, Curso.class)
                            .setParameter("nomeCurso", nomeCurso)
                            .getSingleResult();
            return resultado;

        } catch (NoResultException e) {
            return null;

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    public boolean atualizarCurso(Curso curso) {
        EntityManager em = EMF.getInstancia().gerarEntityMenager();

        try {
            em.getTransaction().begin();
            em.merge(curso);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback(); // Se der erro, desfaz
            e.printStackTrace();
            return false;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }




}
