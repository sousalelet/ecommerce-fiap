package br.com.fiap.sistema.cliente;

import jakarta.validation.constraints.Size;

//validar o tamanho e o dado e enviar
//update - PUT
//cpf nao pode ser alterado entao nao o instaciamos aqui
public record DadosAtualizarCliente(
        Long id,

        @Size(min=3, max=100)
        String nome,

        @Size(max = 100)
        String email,

        @Size(max = 20)
        String telefone
) {
}
