package model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PROFESSOR")
public class Professor  extends Usuario {
    //construtor vazio exigido
    protected Professor(){};

    //construtor padrão
    public Professor(String nome, String telefone) {
        super(nome, telefone);
    }
}
