package DAO;

import config.EMF;
import jakarta.persistence.EntityManager;
import model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

        private static UsuarioDAO instanciaUsuarioDAO;

        private UsuarioDAO() {}

        public static UsuarioDAO getInstancia() {
            if (instanciaUsuarioDAO == null) {
                instanciaUsuarioDAO = new UsuarioDAO();
            }

            return instanciaUsuarioDAO;
        }

        public void cadastrarUsuario(Usuario usuario){
            EntityManager em = EMF.getInstancia().gerarEntityMenager();

            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
            em.close();
            System.out.println("Usuario cadastrado com sucesso!");
        }

        public void excluirUsuario(String nome){
            List<Usuario> usuarioGerenciado = buscarUsuarioPorNome(nome);
            Usuario usuarioParaExcluir = usuarioGerenciado.get(0);

            EntityManager em = EMF.getInstancia().gerarEntityMenager();


            em.getTransaction().begin();
            em.remove(em.merge(usuarioParaExcluir));
            em.getTransaction().commit();
            em.close();

            System.out.println("Usuario excluido com sucesso!");
        }

        public List<Usuario> buscarUsuarioPorNome(String nomePassado) {
            String jpql = "SELECT usuario FROM Usuario usuario WHERE usuario.nome = :nome";

            EntityManager em = EMF.getInstancia().gerarEntityMenager();

            try {
                List<Usuario> resultado =
                        em.createQuery(jpql, Usuario.class)
                                .setParameter("nome", nomePassado)
                                .getResultList();
                return resultado;
            } catch(Exception e){
                em.getTransaction().rollback(); // Se der erro, desfaz
                e.printStackTrace();
                System.out.println("Erro ao atualizar usuario");
                List<Usuario> lista = new ArrayList<>();
                return lista;
            }
        }

        public void atualizarUsuario(Usuario usuarioParaAtualizar){
                System.out.println( "eu aqui " + usuarioParaAtualizar.getNome());

                EntityManager em = EMF.getInstancia().gerarEntityMenager();

                try {
                    em.getTransaction().begin();
                    em.merge(usuarioParaAtualizar);
                    em.getTransaction().commit();
                    em.close();
                    System.out.println("Usuario atualizado com sucesso!");
                } catch(Exception e){
                    em.getTransaction().rollback(); // Se der erro, desfaz
                    e.printStackTrace();
                    System.out.println("Erro ao atualizar usuario");
            }
        }
}

