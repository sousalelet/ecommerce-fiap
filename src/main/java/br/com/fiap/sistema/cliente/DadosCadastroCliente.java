package br.com.fiap.sistema.cliente;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//aqui nós declaramos todos os dados q sao necessários na tabela
//create - post
public record DadosCadastroCliente(

        @Size(min=3, max=100)
        @NotNull
        String nome,

        @NotNull
        @Email
        @Column(unique = true)
        @Size(max = 100)
        String email,

        @NotNull
        @Size(min = 11)
        @Column (unique = true)
        String cpf,

        @Size(max = 20)
        String telefone

) {
}
