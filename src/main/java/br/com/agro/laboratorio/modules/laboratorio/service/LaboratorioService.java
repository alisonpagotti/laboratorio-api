package br.com.agro.laboratorio.modules.laboratorio.service;

import br.com.agro.laboratorio.modules.comum.exception.NotFoundException;
import br.com.agro.laboratorio.modules.comum.exception.ValidacaoException;
import br.com.agro.laboratorio.modules.laboratorio.dto.LaboratorioFiltros;
import br.com.agro.laboratorio.modules.laboratorio.dto.LaboratorioRequest;
import br.com.agro.laboratorio.modules.laboratorio.dto.LaboratorioResponse;
import br.com.agro.laboratorio.modules.laboratorio.model.Laboratorio;
import br.com.agro.laboratorio.modules.laboratorio.repository.LaboratorioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class LaboratorioService {

    private final LaboratorioRepository repository;

    public void cadastrar(LaboratorioRequest request) {
        repository.save(Laboratorio.of(request));
    }

    public List<LaboratorioResponse> listarTodos(LaboratorioFiltros filtros) {
        return repository
            .findAllAllByPredicateOrderByQtdPessoas(filtros.toPredicate().build())
            .stream()
            .map(LaboratorioResponse::of)
            .toList();
    }

    public void editar(Integer id, LaboratorioRequest request) {
        var laboratorio = getLaboratorio(id);
        laboratorio.setNome(request.getNome());

        repository.save(laboratorio);
    }

    public void excluir(Integer id) {
        var laboratorio = getLaboratorio(id);
        validarPessoasVinculadas(laboratorio);

        repository.delete(laboratorio);
    }

    public Laboratorio getLaboratorio(Integer id) {
        return repository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Laboratório não cadastrado!"));
    }

    private void validarPessoasVinculadas(Laboratorio laboratorio) {
        if (!isEmpty(laboratorio.getPessoas())) {
            throw new ValidacaoException("Não foi possível excluir! O Laboratório está vinculado a pessoa(s).");
        }
    }
}
