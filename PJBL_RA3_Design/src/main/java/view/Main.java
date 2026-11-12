package view;

import controller.CursoController;
import controller.DisciplinaController;
import controller.EnderecoController;
import controller.UsuarioController;
import model.*;

import java.util.List;

public class Main {
    static void main() {
        try {
            UsuarioController controllerUsuario = new UsuarioController();
            DisciplinaController disciplinaController = new DisciplinaController();
            EnderecoController enderecoController = new EnderecoController();
            CursoController cursoCOontroller = new CursoController();

            //cursoCOontroller.cadastrarCurso("Engenharia da computação", "Curso voltado nos processos de desenvolvimento de um software");
            //cursoCOontroller.consultarCurso("Engenharia de Software");
            //disciplinaController.cadastrarDisciplina("Qualidade", CargaHoraria.H80);

            //cursoCOontroller.consultarCurso("Engenharia da computação");
            //cursoCOontroller.consultarCurso("Engenharia de Software");
            cursoCOontroller.excluirCurso("Engenharia da computação");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }


    }
}
