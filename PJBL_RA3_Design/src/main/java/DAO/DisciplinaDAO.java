package DAO;

import config.EMF;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import model.Disciplina;
import model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {
    private static DisciplinaDAO instanciaDisciplinaDAO;

    private DisciplinaDAO() {}

    public static DisciplinaDAO getInstancia() {
        if (instanciaDisciplinaDAO == null) {
            instanciaDisciplinaDAO = new DisciplinaDAO();
        }

        return instanciaDisciplinaDAO;
    }

    public void cadastrarDisciplina(Disciplina novaDisciplina){
        EntityManager em = EMF.getInstancia().gerarEntityMenager();

        em.getTransaction().begin();
        em.persist(novaDisciplina);
        em.getTransaction().commit();
        em.close();
    }

    public void excluirDisciplina(String nomeDisciplina){
        List<Disciplina> disciplinaGerenciada = buscarDisciplinaPorNome(nomeDisciplina);
        Disciplina disciplinaParaExcluir = disciplinaGerenciada.get(0);

        EntityManager em = EMF.getInstancia().gerarEntityMenager();


        em.getTransaction().begin();
        em.remove(em.merge(disciplinaParaExcluir));
        em.getTransaction().commit();
        em.close();

        System.out.println("Disciplina excluida com sucesso!");
    }

    public List<Disciplina> buscarDisciplinaPorNome(String nomeDisciplina) {
        String jpql = "SELECT disciplina FROM Disciplina disciplina WHERE disciplina.nomeDisciplina = :nome";

        EntityManager em = EMF.getInstancia().gerarEntityMenager();

        try {
            List<Disciplina> resultado =
                    em.createQuery(jpql, Disciplina.class)
                            .setParameter("nome", nomeDisciplina)
                            .getResultList();
            return resultado;

        } catch(NoResultException e) {
            return null;

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public void atualizarDisciplina(Disciplina disciplinaParaAtualizar){

        EntityManager em = EMF.getInstancia().gerarEntityMenager();

        try {
            em.getTransaction().begin();
            em.merge(disciplinaParaAtualizar);
            em.getTransaction().commit();
            em.close();
            System.out.println("Disciplina atualizada com sucesso!");
        } catch(Exception e){
            em.getTransaction().rollback(); // Se der erro, desfaz
            e.printStackTrace();
            System.out.println("Erro ao atualizar disciplina");
        }
    }
}
