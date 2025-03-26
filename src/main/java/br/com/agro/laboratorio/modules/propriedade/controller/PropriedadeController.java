package br.com.agro.laboratorio.modules.propriedade.controller;

import br.com.agro.laboratorio.modules.propriedade.dto.PropriedadeFiltros;
import br.com.agro.laboratorio.modules.propriedade.dto.PropriedadeRequest;
import br.com.agro.laboratorio.modules.propriedade.dto.PropriedadeResponse;
import br.com.agro.laboratorio.modules.propriedade.service.PropriedadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/propriedades")
public class PropriedadeController {

    private final PropriedadeService service;

    @PostMapping
    public void cadastrar(@RequestBody @Valid PropriedadeRequest request) {
        service.cadastrar(request);
    }

    @GetMapping
    public List<PropriedadeResponse> listarTodos(PropriedadeFiltros filtros) {
        return service.listarTodos(filtros);
    }

    @PutMapping("{id}/editar")
    public void editar(@PathVariable Integer id, @RequestBody @Valid PropriedadeRequest request) {
        service.editar(id, request);
    }

    @DeleteMapping("{id}")
    public void excluir(@PathVariable Integer id) {
        service.excluir(id);
    }
}
