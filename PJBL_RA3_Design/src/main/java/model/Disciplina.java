package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Disciplina {
    @Id@GeneratedValue
    private int idDisciplina;
    private String nomeDisciplina;
    private CargaHoraria cargaHoraria;

    //construtor vazio exigido
    protected Disciplina(){};

    //construtor padrão
    public Disciplina(String nomeDisciplina, CargaHoraria cargaHoraria){
        this.nomeDisciplina = nomeDisciplina;
        this.cargaHoraria = cargaHoraria;
    }

    //Getters e Setters
    public int getIdDisciplina() {return idDisciplina;}
    public void setIdDisciplina(int idDisciplina) {this.idDisciplina = idDisciplina;}

    public String getNomeDisciplina() {return nomeDisciplina;}
    public void setNomeDisciplina(String nomeDisciplina) {this.nomeDisciplina = nomeDisciplina;}

    public CargaHoraria getCargaHoraria() {return cargaHoraria;}
    public void setCargaHoraria(CargaHoraria cargaHoraria) {this.cargaHoraria = cargaHoraria;}
}
