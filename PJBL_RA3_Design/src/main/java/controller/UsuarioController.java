package controller;
import DAO.UsuarioDAO;
import model.*;

import java.util.List;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;

    public UsuarioController(){
        this.usuarioDAO =  UsuarioDAO.getInstancia();
    }

    public void cadastrarUsuario(String nome, String telefone, tipoUsuario tipo) {

        if (consultarUsuarioPorNome(nome).isEmpty()) {
            //verifica novamente se tem 11 digitos
            if (!telefone.matches("\\d{11}")) {
                throw new IllegalArgumentException("Telefone invalido, deve ter 11 digitos numericos");
            }

            //Ajustando o formato do telefone
            String telefoneAjustado =
                    "(" + telefone.substring(0, 2) + ") "
                            + telefone.substring(2, 7) + "-"
                            + telefone.substring(7, 11);

            Usuario novoUsuario = switch (tipo) {
                case ADMINISTRADOR -> new Administrador(nome, telefoneAjustado);
                case ALUNO -> new Aluno(nome, telefoneAjustado);
                case PROFESSOR -> new Professor(nome, telefone);
                default -> throw new IllegalArgumentException("Tipo de usuário inválido: " + tipo);
            };
            usuarioDAO.cadastrarUsuario(novoUsuario);
        } else {
            System.out.println("Aqui esse usuario já esta cadastrado no sistema");
        }
    }

    public void excluirUsuario(String nome) {

        if (consultarUsuarioPorNome(nome).isEmpty()) {
            System.out.println("esse usuario não esta cadastrado no sistema");

        } else {
            usuarioDAO.excluirUsuario(nome);
        }
    }

    public List consultarUsuarioPorNome(String nome){
        List<Usuario> consultaUsuario = usuarioDAO.buscarUsuarioPorNome(nome);

        if(consultaUsuario.isEmpty()){
            System.out.println("Usuário inexistente");
        }
        return consultaUsuario;
    }

    public void alterarNome(String nomeAntigo, String nomeNovo){
        List<Usuario> consultaUsuario = consultarUsuarioPorNome(nomeAntigo);

        if(consultaUsuario.isEmpty()){return;}

        Usuario novoUsuario = consultaUsuario.get(0);
        novoUsuario.setNome(nomeNovo);
        String telefoneAjustado =
                "(" + novoUsuario.getTelefone().substring(0, 2) + ") "
                        + novoUsuario.getTelefone().substring(2, 7) + "-"
                        + novoUsuario.getTelefone().substring(7, 11);

        novoUsuario.setTelefone(telefoneAjustado);

        System.out.println(("Nome antigo do usuario: " + nomeAntigo));
        System.out.println("Nome usuario " + novoUsuario.getNome());
        usuarioDAO.atualizarUsuario(novoUsuario);
    }
}
