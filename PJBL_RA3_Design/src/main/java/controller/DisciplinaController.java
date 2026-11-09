package controller;

import DAO.DisciplinaDAO;
import DAO.UsuarioDAO;
import model.CargaHoraria;
import model.Disciplina;
import model.Usuario;

import java.util.List;

public class DisciplinaController {
    private DisciplinaDAO disciplinaDAO;

    public DisciplinaController(){
        this.disciplinaDAO =  disciplinaDAO.getInstancia();
    }

    public void cadastrarDisciplina(String nomeDisciplina, CargaHoraria cargaHoraria){
        if (consultarDisciplinaPorNome(nomeDisciplina).isEmpty()) {
            Disciplina novaDisciplina = new Disciplina(nomeDisciplina, cargaHoraria);
            disciplinaDAO.cadastrarDisciplina(novaDisciplina);
            return;
        }
        System.out.println("Disciplina inexistente");
    }

    public void excluirDisciplina(String nomeDisciplina) {

        if (consultarDisciplinaPorNome(nomeDisciplina).isEmpty()) {
            return;
        } else {
            disciplinaDAO.excluirDisciplina(nomeDisciplina);
        }
    }
//    public alterarDisciplina(){}

    public List consultarDisciplinaPorNome(String nomeDisciplina){
        List<Disciplina> consultaDisciplina = disciplinaDAO.buscarDisciplinaPorNome(nomeDisciplina);

        if(consultaDisciplina.isEmpty()){
            System.out.println("Disciplina inexistente");
        }
        return consultaDisciplina;
    }

    public void alterarNomeDisciplina(String nomeAntigo, String nomeNovo){
        List<Disciplina> consultaDisciplina = consultarDisciplinaPorNome(nomeAntigo);

        if(consultaDisciplina.isEmpty()){return;}

        Disciplina novaDisciplina = consultaDisciplina.get(0);
        novaDisciplina.setNomeDisciplina(nomeNovo);

        disciplinaDAO.atualizarDisciplina(novaDisciplina);
    }
}
