package model;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TIPO_USUARIO", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;
    private String nome;
    @Column(length = 15)
    private String telefone;

    //construtor vazio exigido
    protected Usuario(){};

    //construtor padrão
    public Usuario(String nome, String telefone){
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome obrigatório");
        }

        //verifica se telefone é null
        if (telefone == null) {
            throw new IllegalArgumentException("Telefone obrigatório");
        }

        this.nome = nome;
        this.telefone = telefone;
    }

    //Getters e Setters
    public Integer getIdUsuario() {return idUsuario;}
    public void setIdUsuario(Integer idUsuario) {this.idUsuario = idUsuario;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}
}
