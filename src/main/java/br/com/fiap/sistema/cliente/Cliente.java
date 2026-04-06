package br.com.fiap.sistema.cliente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table (name = "clientes") //para referenciar q a tabela com o nome do banco
@Entity (name = "Cliente") //para falar qual eh a entidade do codigo q eh a tabela
@Getter //faz todos os getters
@Setter //faz todos os setters
@NoArgsConstructor //faz o construtor sem argumentos
@AllArgsConstructor // faz o construtor com todos os argumentos


public class Cliente {

    @Id //fala q eh a chave primaria o id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //o proprio banco de dados vai gerar um valor pro id (chave primaria)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private boolean ativo;

    //construtor que vai ser usado no post (cadastro) referenciar o dto como dados
    //nao por o ID pq vai gerar sozinho
    public Cliente(DadosCadastroCliente dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.ativo = true;
    }

    //metodo do PUT - atualiza so oq veio preenchido
    public void atualizarCategoria (@Valid DadosAtualizarCliente dados){
        if (dados.nome() != null && !dados.nome().isBlank())
            this.nome = dados.nome();
    }
}
