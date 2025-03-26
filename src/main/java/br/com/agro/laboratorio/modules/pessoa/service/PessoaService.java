package br.com.agro.laboratorio.modules.pessoa.service;

import br.com.agro.laboratorio.modules.comum.exception.NotFoundException;
import br.com.agro.laboratorio.modules.comum.exception.ValidacaoException;
import br.com.agro.laboratorio.modules.laboratorio.service.LaboratorioService;
import br.com.agro.laboratorio.modules.pessoa.dto.PessoaFiltros;
import br.com.agro.laboratorio.modules.pessoa.dto.PessoaRequest;
import br.com.agro.laboratorio.modules.pessoa.dto.PessoaResponse;
import br.com.agro.laboratorio.modules.pessoa.model.Pessoa;
import br.com.agro.laboratorio.modules.pessoa.repository.PessoaRepository;
import br.com.agro.laboratorio.modules.propriedade.service.PropriedadeService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.agro.laboratorio.modules.comum.util.DateUtil.validarDataInicialPosteriorADataFinal;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;
    private final PropriedadeService propriedadeService;
    private final LaboratorioService laboratorioService;

    public void cadastrar(PessoaRequest request) {
        validarDataInicialPosteriorADataFinal(request.getDataInicial(), request.getDataFinal());
        var propriedades = propriedadeService.getPropriedades(request.getPropriedadesIds());
        var laboratorio = laboratorioService.getLaboratorio(request.getLaboratorioId());
        var pessoa = Pessoa.of(request, propriedades, laboratorio);

        repository.save(pessoa);
    }

    public List<PessoaResponse> listarTodos(PessoaFiltros filtros) {
        return repository
            .findAll(filtros.toPredicate().build())
            .stream()
            .map(PessoaResponse::of)
            .toList();
    }

    public void editar(Integer id, PessoaRequest request) {
        validarNomeEditado(request);
        var pessoa = getPessoa(id);
        pessoa.setNome(request.getNome());

        repository.save(pessoa);
    }

    public void excluir(Integer id) {
        repository.delete(getPessoa(id));
    }

    private Pessoa getPessoa(Integer id) {
        return repository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Pessoa não cadastrada!"));
    }

    private void validarNomeEditado(PessoaRequest request) {
        if (StringUtils.isBlank(request.getNome())) {
            throw new ValidacaoException("O campo nome é obrigatório.");
        }
    }
}
