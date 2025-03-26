package br.com.agro.laboratorio.modules.pessoa.controller;

import br.com.agro.laboratorio.modules.pessoa.dto.PessoaFiltros;
import br.com.agro.laboratorio.modules.pessoa.dto.PessoaRequest;
import br.com.agro.laboratorio.modules.pessoa.dto.PessoaResponse;
import br.com.agro.laboratorio.modules.pessoa.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/pessoas")
public class PessoaController {

    private final PessoaService service;

    @PostMapping
    public void cadastrar(@RequestBody @Valid PessoaRequest request) {
        service.cadastrar(request);
    }

    @GetMapping
    public List<PessoaResponse> listarTodos(PessoaFiltros filtros) {
        return service.listarTodos(filtros);
    }

    @PutMapping("{id}/editar")
    public void editar(@PathVariable Integer id, @RequestBody PessoaRequest request) {
        service.editar(id, request);
    }

    @DeleteMapping("{id}")
    public void excluir(@PathVariable Integer id) {
        service.excluir(id);
    }
}
