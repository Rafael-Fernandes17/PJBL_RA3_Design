package view;

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
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }


    }
}
