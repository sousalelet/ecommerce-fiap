package br.com.fiap.sistema.cliente;

//read - GET
//declara as variaveis
public record DadosListagemCliente( Long id, String nome, String email) {

    public DadosListagemCliente(Cliente cliente) {
        this (cliente.getId(), cliente.getNome(), cliente.getEmail());
    }
}
