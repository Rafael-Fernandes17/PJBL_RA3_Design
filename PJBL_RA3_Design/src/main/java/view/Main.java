package view;

import controller.UsuarioController;
import model.Aluno;
import model.Usuario;
import model.tipoUsuario;

import java.util.List;

public class Main {
    static void main() {
        try {
            //Aluno aluno1 = new Aluno("rafael", "41996097443");
            UsuarioController controllerUsuario = new UsuarioController();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }


    }
}
