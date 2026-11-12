package controller;

import DAO.CursoDAO;
import DAO.DisciplinaDAO;
import model.Curso;
import model.Disciplina;

import java.util.List;
import java.util.Scanner;

public class CursoController {
    private CursoDAO cursoDAO = CursoDAO.getInstancia();

    public void cadastrarCurso(String nomeCurso, String descricaoCurso){
        Curso consultaCurso = cursoDAO.buscarCurso(nomeCurso);

        if(consultaCurso == null){
            Curso novoCurso = new Curso(nomeCurso, descricaoCurso);
            boolean resultadoCadastro = cursoDAO.cadastrarCurso(novoCurso);

            if(resultadoCadastro == true) {
                System.out.println("Curso cadastrado com sucesso");
            } else {
                System.out.println("Erro ao cadastrar o curso");
            }
        } else {
            System.out.println("Já existe um curso com esse nome registrado no sistema");
        }
    }

    public void excluirCurso(String nomeCurso){
        Curso consultaCurso = cursoDAO.buscarCurso(nomeCurso);

        if(consultaCurso == null){
            System.out.println("Esse curso não está cadastrado no nosso sitema");
        } else {
            boolean resultadoExclusao = cursoDAO.excluirCurso(consultaCurso);

            if(resultadoExclusao == true){
                System.out.println("Curso excluido com sucesso");
            } else {
                System.out.println("Não foi possível excluir esse curso");
            }
        }
    }

    public void consultarCurso(String nomeCurso){
        Curso consultaCurso = cursoDAO.buscarCurso(nomeCurso);

        if(consultaCurso == null){
            System.out.println("Esse curso não está cadastrado no nosso sitema");
        } else {
            System.out.println("ID CURSO: " + consultaCurso.getId());
            System.out.println("NOME CURSO: " + consultaCurso.getNome());
            System.out.println("DESCRICAO: " + consultaCurso.getDescricao());
            System.out.println("DISCIPLINAS DO CURSO: ");

            for(Disciplina d : consultaCurso.getDisciplinas()){
                System.out.println(d.getNomeDisciplina());
            }
        }
    }

    public void atualizarCurso(String nomeAntigo, String descricaoAntiga){

        Curso consultaCurso = cursoDAO.buscarCurso(nomeAntigo);

        if (consultaCurso == null) {
            System.out.println("Não foi encontrado nenhum curso com esse nome em nosso sistema");
        } else {
            Scanner input = new Scanner(System.in);

            System.out.println("Digite o novo nome do curso:");
            String novoNome = input.nextLine();

            System.out.println("Digite a nova descricao do curso:");
            String novaDescricao = input.nextLine();


            consultaCurso.setNome(novoNome);
            consultaCurso.setDescricao(novaDescricao);


            boolean atualizacaoCurso = cursoDAO.atualizarCurso(consultaCurso);

            if (atualizacaoCurso == true) {
                System.out.println("Curso " + nomeAntigo + " atualizado com sucesso.");
                System.out.println("Novo nome:  " + consultaCurso.getNome());

                System.out.println("Curso " + nomeAntigo + " atualizado com sucesso.");
                System.out.println("Nova descricao:  " + consultaCurso.getDescricao());
            } else {
                System.out.println("Não foi possível atualizar este curso");
            }
        }
    }

    public void adicionarDisciplina(String nomeCurso, String nomeDisciplina) {
        DisciplinaDAO disciplinaDAO = DisciplinaDAO.getInstancia();
        List<Disciplina> consultaDisciplina = disciplinaDAO.buscarDisciplinaPorNome(nomeDisciplina);

        if(consultaDisciplina.isEmpty()){
            System.out.println("Essa disciplina não esta cadastrada no sistema");
            return;
        }

        Disciplina disciplinaEncontrada = consultaDisciplina.get(0);

        Curso consultaCurso = cursoDAO.buscarCurso(nomeCurso);

        if (consultaCurso == null) {
            System.out.println("Não foi encontrado nenhum curso com esse nome em nosso sistema");
        } else {
            consultaCurso.getDisciplinas().add(disciplinaEncontrada);
            consultaCurso.setDisciplinas(consultaCurso.getDisciplinas());

            if (disciplinaEncontrada.getCurso() != null) {
                System.out.println("Essa disciplina já está vinculada a um curso.");
                return;
            }

            disciplinaEncontrada.setCurso(consultaCurso);

            //atualizar no banco depois de adicionar disciplina
            boolean atualizacaoCurso = cursoDAO.atualizarCurso(consultaCurso);
            boolean atualizacaoDisciplina = disciplinaDAO.atualizarDisciplina(disciplinaEncontrada);

            if (atualizacaoCurso == true && atualizacaoDisciplina) {
                System.out.println("Disciplina adicionada ao curso com sucesso");
            } else {
                System.out.println("Não foi possível adicionar essa disciplina a este curso");
            }

        }
    }
}
