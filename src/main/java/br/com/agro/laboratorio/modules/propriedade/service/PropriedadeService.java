package br.com.agro.laboratorio.modules.propriedade.service;

import br.com.agro.laboratorio.modules.comum.exception.NotFoundException;
import br.com.agro.laboratorio.modules.comum.exception.ValidacaoException;
import br.com.agro.laboratorio.modules.propriedade.dto.PropriedadeFiltros;
import br.com.agro.laboratorio.modules.propriedade.dto.PropriedadeRequest;
import br.com.agro.laboratorio.modules.propriedade.dto.PropriedadeResponse;
import br.com.agro.laboratorio.modules.propriedade.model.Propriedade;
import br.com.agro.laboratorio.modules.propriedade.repository.PropriedadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class PropriedadeService {

    private final PropriedadeRepository repository;

    public void cadastrar(PropriedadeRequest request) {
        repository.save(Propriedade.of(request));
    }

    public List<PropriedadeResponse> listarTodos(PropriedadeFiltros filtros) {
        return repository
            .findAll(filtros.toPredicate().build())
            .stream()
            .map(PropriedadeResponse::of)
            .toList();
    }

    public void editar(Integer id, PropriedadeRequest request) {
        var propriedades = getPropriedade(id);
        propriedades.setNome(request.getNome());

        repository.save(propriedades);
    }

    public void excluir(Integer id) {
        var propriedade = getPropriedade(id);
        validarPessoasVinculadas(propriedade);

        repository.delete(propriedade);
    }

    public List<Propriedade> getPropriedades(List<Integer> ids) {
        return repository.findAllById(ids);
    }

    private Propriedade getPropriedade(Integer id) {
        return repository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Propriedade não cadastrada!"));
    }

    private void validarPessoasVinculadas(Propriedade propriedade) {
        if (!isEmpty(propriedade.getPessoas())) {
            throw new ValidacaoException("Não foi possível excluir! A Propriedade está vinculada a pessoa(s).");
        }
    }
}
