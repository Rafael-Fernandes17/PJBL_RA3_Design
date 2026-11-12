package model;

import jakarta.persistence.*;

@Entity
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DISCIPLINA")
    private int idDisciplina;
    @Column(name = "NOME_DISCIPLINA")
    private String nomeDisciplina;
    @Enumerated(EnumType.STRING)
    @Column(name = "CARGA_HORARIA")
    private CargaHoraria cargaHoraria;
    @ManyToOne
    @JoinColumn(name = "ID_CURSO", nullable = true)
    private Curso curso;

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

    public Curso getCurso() {return curso;}
    public void setCurso(Curso curso) {this.curso = curso;}
}
