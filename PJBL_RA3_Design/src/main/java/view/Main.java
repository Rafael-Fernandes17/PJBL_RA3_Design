package view;

import controller.DisciplinaController;
import controller.UsuarioController;
import model.Aluno;
import model.CargaHoraria;
import model.Usuario;
import model.tipoUsuario;

import java.util.List;

public class Main {
    static void main() {
        try {
            //Aluno aluno1 = new Aluno("rafael", "41996097443");
            UsuarioController controllerUsuario = new UsuarioController();
            DisciplinaController disciplinaController = new DisciplinaController();

            //disciplinaController.cadastrarDisciplina("Matematica", CargaHoraria.H80);
            //disciplinaController.alterarNomeDisciplina("Matematica", "Matematica II");
            disciplinaController.excluirDisciplina("Matematica II");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }


    }
}
