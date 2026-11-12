package DAO;

import config.EMF;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import model.Endereco;

public class EnderecoDAO {
    private static EnderecoDAO instanciaEnderecoDAO;

    private EnderecoDAO() {
    }

    public static EnderecoDAO getInstancia() {
        if (instanciaEnderecoDAO == null) {
            instanciaEnderecoDAO = new EnderecoDAO();
        }
        return instanciaEnderecoDAO;
    }

    public boolean cadastrarEndereco(Endereco endereco) {
        EntityManager em = EMF.getInstancia().gerarEntityMenager();

        try {
            em.getTransaction().begin();
            em.persist(endereco);
            em.getTransaction().commit();
            em.close();

            return true;
        } catch (NoResultException e) {

            return false;
        }
    }

    public boolean excluirEndereco(Endereco endereco) {
        EntityManager em = EMF.getInstancia().gerarEntityMenager();

        try {
            em.getTransaction().begin();
            em.remove(em.merge(endereco));
            em.getTransaction().commit();
            em.close();

            return true;
        } catch (NoResultException e) {

        }
        return false;
    }

    public Endereco buscarEndereco(String rua, Integer numero, String cidade, String estado) {
        String jpql = "SELECT endereco FROM Endereco endereco WHERE endereco.rua = :rua " +
                "AND endereco.numero = :numero " + "AND endereco.cidade = :cidade " +
                "AND endereco.estado = :estado";

        EntityManager em = EMF.getInstancia().gerarEntityMenager();

        try {
            Endereco resultado =
                    em.createQuery(jpql, Endereco.class)
                            .setParameter("rua", rua)
                            .setParameter("numero", numero)
                            .setParameter("cidade", cidade)
                            .setParameter("estado", estado)
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

    public boolean atualizarEndereco(Endereco endereco) {
        EntityManager em = EMF.getInstancia().gerarEntityMenager();

        try {
            em.getTransaction().begin();
            em.merge(endereco);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback(); // Se der erro, desfaz
            e.printStackTrace();
            return false;
        }
    }
}
