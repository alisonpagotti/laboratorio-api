package br.com.agro.laboratorio.modules.pessoa.service;

import br.com.agro.laboratorio.modules.comum.exception.NotFoundException;
import br.com.agro.laboratorio.modules.comum.exception.ValidacaoException;
import br.com.agro.laboratorio.modules.laboratorio.service.LaboratorioService;
import br.com.agro.laboratorio.modules.pessoa.dto.PessoaResponse;
import br.com.agro.laboratorio.modules.pessoa.model.Pessoa;
import br.com.agro.laboratorio.modules.pessoa.repository.PessoaRepository;
import br.com.agro.laboratorio.modules.propriedade.service.PropriedadeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static br.com.agro.laboratorio.modules.comum.helper.DataHelper.umaData;
import static br.com.agro.laboratorio.modules.laboratorio.helper.LaboratorioHelper.umLaboratorio;
import static br.com.agro.laboratorio.modules.pessoa.helper.PessoaHelper.*;
import static br.com.agro.laboratorio.modules.propriedade.helper.PropriedadeHelper.umaPropriedade;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PessoaServiceTest {

    @InjectMocks
    private PessoaService service;
    @Mock
    private PessoaRepository repository;
    @Mock
    private PropriedadeService propriedadeService;
    @Mock
    private LaboratorioService laboratorioService;
    @Captor
    private ArgumentCaptor<Pessoa> captor;

    @Test
    void cadastrar_deveCadastrarPessoa_seTudoOk() {
        doReturn(List.of(umaPropriedade(), umaPropriedade()))
            .when(propriedadeService)
            .getPropriedades(List.of(1, 2));

        doReturn(umLaboratorio())
            .when(laboratorioService)
            .getLaboratorio(1);

        service.cadastrar(umaPessoaRequest());

        verify(propriedadeService).getPropriedades(List.of(1, 2));
        verify(laboratorioService).getLaboratorio(1);
        verify(repository).save(captor.capture());

        assertThat(captor.getValue())
            .extracting(
                Pessoa::getId,
                Pessoa::getNome,
                Pessoa::getDataInicial,
                Pessoa::getDataFinal,
                Pessoa::getPropriedades,
                Pessoa::getLaboratorio,
                Pessoa::getObservacoes
            )
            .containsExactly(
                null,
                "Pessoa Um",
                umaData(),
                umaData().plusDays(5),
                List.of(umaPropriedade(), umaPropriedade()),
                umLaboratorio(),
                "Uma observação da Pessoa Um"
            );
    }

    @Test
    void cadastrar_naoDeveCadastrarPessoa_seDataInicialPosteriorADataFinal() {
        var request = umaPessoaRequest();
        request.setDataInicial(umaData().plusDays(30));

        assertThatCode(() -> service.cadastrar(request))
            .isInstanceOf(ValidacaoException.class)
            .hasMessage("A data inicial não pode ser posterior a data final.");

        verify(propriedadeService, never()).getPropriedades(anyList());
        verify(laboratorioService, never()).getLaboratorio(anyInt());
        verify(repository, never()).save(any(Pessoa.class));
    }

    @Test
    void listarTodos_deveListarTodasPessoas_seCadastradas() {
        doReturn(List.of(umaPessoa()))
            .when(repository)
            .findAll(umaPessoaPredicate().build());

        assertThat(service.listarTodos(umaPessoaFiltros()))
            .extracting(
                PessoaResponse::getId,
                PessoaResponse::getNome,
                PessoaResponse::getDataInicial,
                PessoaResponse::getDataFinal,
                PessoaResponse::getPropriedades,
                PessoaResponse::getLaboratorio
            )
            .containsExactly(
                tuple(
                    1,
                    "Pessoa Um",
                    umaData(),
                    umaData().plusDays(5),
                    List.of(umaPropriedade(), umaPropriedade()),
                    umLaboratorio()
                )
            );

        verify(repository).findAll(umaPessoaPredicate().build());
    }

    @Test
    void editar_deveEditarPessoa_sePessoaCadastrada() {
        var request = umaPessoaRequest();
        request.setNome("Pessoa Um Editada");

        doReturn(Optional.of(umaPessoa()))
            .when(repository)
            .findById(1);

        service.editar(1, request);

        verify(repository).findById(1);
        verify(repository).save(captor.capture());

        assertEquals("Pessoa Um Editada", captor.getValue().getNome());
    }

    @Test
    void editar_deveLancarNotFoundExceptionENaoEditarPessoa_sePessoaNaoCadastrada() {
        doReturn(Optional.empty())
            .when(repository)
            .findById(1);

        assertThatCode(() -> service.editar(1, umaPessoaRequest()))
            .isInstanceOf(NotFoundException.class)
            .hasMessage("Pessoa não cadastrada!");

        verify(repository).findById(1);
        verify(repository, never()).save(any(Pessoa.class));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void editar_deveLancarValidacaoExceptionENaoEditarPessoa_seNomeNuloVazioOuEmBranco(String nome) {
        var request = umaPessoaRequest();
        request.setNome(nome);

        assertThatCode(() -> service.editar(1, request))
            .isInstanceOf(ValidacaoException.class)
            .hasMessage("O campo nome é obrigatório.");

        verify(repository, never()).findById(anyInt());
        verify(repository, never()).save(any(Pessoa.class));
    }

    @Test
    void excluir_deveExcluirPessoa_sePessoaCadastrada() {
        doReturn(Optional.of(umaPessoa()))
            .when(repository)
            .findById(1);

        service.excluir(1);

        verify(repository).findById(1);
        verify(repository).delete(umaPessoa());
    }
}