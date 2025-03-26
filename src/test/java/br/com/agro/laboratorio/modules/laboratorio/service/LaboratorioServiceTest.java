package br.com.agro.laboratorio.modules.laboratorio.service;

import br.com.agro.laboratorio.modules.comum.exception.NotFoundException;
import br.com.agro.laboratorio.modules.comum.exception.ValidacaoException;
import br.com.agro.laboratorio.modules.laboratorio.dto.LaboratorioResponse;
import br.com.agro.laboratorio.modules.laboratorio.model.Laboratorio;
import br.com.agro.laboratorio.modules.laboratorio.repository.LaboratorioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static br.com.agro.laboratorio.modules.laboratorio.helper.LaboratorioHelper.*;
import static br.com.agro.laboratorio.modules.pessoa.helper.PessoaHelper.umaPessoa;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LaboratorioServiceTest {

    @InjectMocks
    private LaboratorioService service;
    @Mock
    private LaboratorioRepository repository;
    @Captor
    private ArgumentCaptor<Laboratorio> captor;

    @Test
    void cadastrar_deveCadastrarLaboratorio_seChamado() {
        service.cadastrar(umLaboratorioRequest());

        verify(repository).save(captor.capture());

        assertEquals("Laboratório Um", captor.getValue().getNome());
    }

    @Test
    void listarTodos_deveListarTodosLaboratorios_seCadastrados() {
        doReturn(List.of(umLaboratorio(), umLaboratorio()))
            .when(repository)
            .findAllAllByPredicateOrderByQtdPessoas(umLaboratorioPredicate().build());

        assertThat(service.listarTodos(umLaboratorioFiltros()))
            .extracting(LaboratorioResponse::getId, LaboratorioResponse::getNome, LaboratorioResponse::getQtdPessoas)
            .containsExactlyInAnyOrder(
                tuple(1, "LABORATÓRIO UM", 0),
                tuple(1, "LABORATÓRIO UM", 0)
            );

        verify(repository).findAllAllByPredicateOrderByQtdPessoas(umLaboratorioPredicate().build());
    }

    @Test
    void editar_deveEditarLaboratorio_seCadastrado() {
        var request = umLaboratorioRequest();
        request.setNome("Laboratório Um Editado");

        doReturn(Optional.of(umLaboratorio()))
            .when(repository)
            .findById(1);

        service.editar(1, request);

        verify(repository).findById(1);
        verify(repository).save(captor.capture());

        assertEquals("Laboratório Um Editado", captor.getValue().getNome());
    }

    @Test
    void editar_deveLancarNotFoundException_seLaboratorioNaoCadastrado() {
        doReturn(Optional.empty())
            .when(repository)
            .findById(1);

        assertThatCode(() -> service.editar(1, umLaboratorioRequest()))
            .isInstanceOf(NotFoundException.class)
            .hasMessage("Laboratório não cadastrado!");

        verify(repository).findById(1);
        verify(repository, never()).save(any(Laboratorio.class));
    }

    @Test
    void excluir_deveExcluirLaboratorio_seLaboratorioCadastrado() {
        doReturn(Optional.of(umLaboratorio()))
            .when(repository)
            .findById(1);

        service.excluir(1);

        verify(repository).findById(1);
        verify(repository).delete(umLaboratorio());
    }

    @Test
    void excluir_deveLancarNotFoundExceptionENaoExcluirLaboratorio_seLaboratorioNaoCadastrado() {
        doReturn(Optional.empty())
            .when(repository)
            .findById(1);

        assertThatCode(() -> service.excluir(1))
            .isInstanceOf(NotFoundException.class)
            .hasMessage("Laboratório não cadastrado!");

        verify(repository).findById(1);
        verify(repository, never()).delete(any(Laboratorio.class));
    }

    @Test
    void excluir_deveLancarValidacaoExceptionENaoExcluirLaboratorio_seLaboratorioCadastradoVinculadoAPessoa() {
        doReturn(Optional.of(umLaboratorio(umaPessoa())))
            .when(repository)
            .findById(1);

        assertThatCode(() -> service.excluir(1))
            .isInstanceOf(ValidacaoException.class)
            .hasMessage("Não foi possível excluir! O Laboratório está vinculado a pessoa(s).");

        verify(repository).findById(1);
        verify(repository, never()).delete(any(Laboratorio.class));
    }

    @Test
    void getLaboratorio_deveRetornarLaboratorio_seCadastrado() {
        doReturn(Optional.of(umLaboratorio()))
            .when(repository)
            .findById(1);

        assertEquals(umLaboratorio(), service.getLaboratorio(1));

        verify(repository).findById(1);
    }
}