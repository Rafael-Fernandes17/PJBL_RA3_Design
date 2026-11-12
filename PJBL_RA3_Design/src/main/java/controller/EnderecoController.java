package controller;

import DAO.EnderecoDAO;
import model.Endereco;

import java.util.Scanner;

public class EnderecoController {
    private EnderecoDAO enderecoDAO = EnderecoDAO.getInstancia();

    public void cadastrarEndereco(String rua, Integer numero, String cidade, String estado) {
        Endereco consultaEndereco = enderecoDAO.buscarEndereco(rua, numero, cidade, estado);

        if (consultaEndereco == null) {
            Endereco endereco = new Endereco(rua, numero, cidade, estado);
            enderecoDAO.cadastrarEndereco(endereco);
        } else {
            System.out.println("Endereço já cadastrado no sistema");
        }
    }

    public void excluirEndereco(String rua, Integer numero, String cidade, String estado) {
        Endereco consultaEndereco = enderecoDAO.buscarEndereco(rua, numero, cidade, estado);

        if (consultaEndereco == null) {
            System.out.println("Não foi encontrado nenhum endereco correspondente em nosso sistema");
        } else {
            enderecoDAO.excluirEndereco(consultaEndereco);
            System.out.println("Endereço excluido com sucesso");
        }
    }

    public void consultarEndereco(String rua, Integer numero, String cidade, String estado){
        Endereco consultaEndereco = enderecoDAO.buscarEndereco(rua, numero, cidade, estado);

        if (consultaEndereco == null) {
            System.out.println("Não foi encontrado nenhum endereco correspondente em nosso sistema");
        } else {
            System.out.println("RUA: " + consultaEndereco.getRua());
            System.out.println("Numero: " + consultaEndereco.getNumero());
            System.out.println("CIDADE: " + consultaEndereco.getCidade());
            System.out.println("ESTADO: " + consultaEndereco.getEstado());
        }
    }

    public void atualizarEndereco(String ruaAntiga, Integer numeroAntigo, String cidadeAntiga, String estadoAntigo){
        Endereco consultaEndereco = enderecoDAO.buscarEndereco(ruaAntiga, numeroAntigo, cidadeAntiga, estadoAntigo);

        if (consultaEndereco == null) {
            System.out.println("Não foi encontrado nenhum endereco correspondente em nosso sistema");
        } else {
            Scanner input = new Scanner(System.in);

            System.out.println("Digite a nova rua:");
            String novaRua = input.nextLine();

            System.out.println("Digite o novo número:");
            Integer novoNumero = input.nextInt();
            input.nextLine();

            System.out.println("Digite a nova cidade:");
            String novaCidade = input.nextLine();

            System.out.println("Digite o novo estado:");
            String novoEstado = input.nextLine();

            consultaEndereco.setRua(novaRua);
            consultaEndereco.setCidade(novaCidade);
            consultaEndereco.setNumero(novoNumero);
            consultaEndereco.setEstado(novoEstado);


            enderecoDAO.atualizarEndereco(consultaEndereco);
        }
    }
}
