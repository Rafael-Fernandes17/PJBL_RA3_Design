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

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "ID_ENDERECO", nullable = false)
    private Endereco endereco;

    //construtor vazio exigido
    protected Usuario(){};

    //construtor padrão
    public Usuario(String nome, String telefone, Endereco endereco){
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome obrigatório");
        }

        //verifica se telefone é null
        if (telefone == null) {
            throw new IllegalArgumentException("Telefone obrigatório");
        }

        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    //Getters e Setters
    public Integer getIdUsuario() {return idUsuario;}
    public void setIdUsuario(Integer idUsuario) {this.idUsuario = idUsuario;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}

    public Endereco getEndereco() {return endereco;}
    public void setEndereco(Endereco endereco) {this.endereco = endereco;}
}
