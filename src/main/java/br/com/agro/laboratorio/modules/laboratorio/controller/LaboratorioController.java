package br.com.agro.laboratorio.modules.laboratorio.controller;

import br.com.agro.laboratorio.modules.laboratorio.dto.LaboratorioFiltros;
import br.com.agro.laboratorio.modules.laboratorio.dto.LaboratorioRequest;
import br.com.agro.laboratorio.modules.laboratorio.dto.LaboratorioResponse;
import br.com.agro.laboratorio.modules.laboratorio.service.LaboratorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/laboratorios")
public class LaboratorioController {

    private final LaboratorioService service;

    @PostMapping
    public void cadastrar(@RequestBody @Valid LaboratorioRequest request) {
        service.cadastrar(request);
    }

    @GetMapping
    public List<LaboratorioResponse> listarTodos(LaboratorioFiltros filtros) {
        return service.listarTodos(filtros);
    }

    @PutMapping("{id}/editar")
    public void editar(@PathVariable Integer id, @RequestBody @Valid LaboratorioRequest request) {
        service.editar(id, request);
    }

    @DeleteMapping("{id}")
    public void excluir(@PathVariable Integer id) {
        service.excluir(id);
    }
}
