package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CURSO")
    private Integer idCurso;
    private String nome;
    private String descricao;
    @OneToMany(mappedBy = "curso")
    private List<Disciplina> disciplinas = new ArrayList<>();

    //construtor vazio exigido
    protected Curso(){};

    //construtor padrão
    public Curso(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

    // Getters e Setters


    public Integer getId() {return idCurso;}
    public void setId(Integer id) {this.idCurso = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}

    public List<Disciplina> getDisciplinas() {return disciplinas;}
    public void setDisciplinas(List<Disciplina> disciplinas) {this.disciplinas = disciplinas;}
}
