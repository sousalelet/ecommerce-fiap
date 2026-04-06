package br.com.fiap.sistema.controller;

import br.com.fiap.sistema.cliente.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // POST /clientes — cria novo cliente
    @PostMapping
    @Transactional
    public void cadastrarCliente(@RequestBody @Valid DadosCadastroCliente dados) {
        clienteRepository.save(new Cliente(dados));
    }

    // GET /clientes — lista todos os ativos (paginado)
    @GetMapping
    public Page<DadosListagemCliente> listarClientes(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return clienteRepository.findAllByAtivoTrue(paginacao)
                .map(DadosListagemCliente::new);
    }

    // GET /clientes/{id} — detalha um cliente pelo id
    @GetMapping("/{id}")
    public DadosDetalhamentoCliente buscarPorId(@PathVariable Long id) {
        var cliente = clienteRepository.getReferenceById(id);
        return new DadosDetalhamentoCliente(cliente);
    }

    // PUT /clientes — atualiza campos enviados no body
    @PutMapping
    @Transactional
    public void atualizarCliente(@RequestBody @Valid DadosAtualizarCliente dados) {
        var cliente = clienteRepository.getReferenceById(dados.id());
        cliente.atualizarCategoria(dados);
    }

    // DELETE /clientes/{id} — soft delete (ativo = 0)
    @DeleteMapping("/{id}")
    @Transactional
    public void deletarCliente(@PathVariable Long id) {
        var cliente = clienteRepository.getReferenceById(id);
        cliente.excluirCliente();
    }
}
