package model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMINISTRADOR")
public class Administrador extends Usuario {

    //construtor vazio exigido
    protected Administrador(){};

    //construtor padrão
    public Administrador(String nome, String telefone, Endereco endereco){
        super(nome, telefone, endereco);
    }
}
