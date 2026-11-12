package model;

import jakarta.persistence.*;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_ENDERECO")
    private Integer idEndereco;
    private String rua;
    private Integer numero;
    private String cidade;
    private String estado;

    //construtor vazio exigido
    protected Endereco(){};

    //construtor padrão
    public Endereco(String rua, Integer numero, String cidade, String estado) {
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
    }


    //Getters e Setters
    public String getRua() {return rua;}
    public void setRua(String rua) {this.rua = rua;}

    public Integer getNumero() {return numero;}
    public void setNumero(Integer numero) {this.numero = numero;}

    public String getCidade() {return cidade;}
    public void setCidade(String cidade) {this.cidade = cidade;}

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}
}
