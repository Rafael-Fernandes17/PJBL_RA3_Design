package model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ALUNO")
public class Aluno extends Usuario {

    //construtor vazio exigido
    protected Aluno(){};

    //construtor padrão
    public Aluno (String nome, String telefone){
        super(nome, telefone);
    }
}
