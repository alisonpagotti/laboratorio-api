package br.com.agro.laboratorio.modules.propriedade.service;

import br.com.agro.laboratorio.modules.comum.exception.NotFoundException;
import br.com.agro.laboratorio.modules.comum.exception.ValidacaoException;
import br.com.agro.laboratorio.modules.propriedade.dto.PropriedadeResponse;
import br.com.agro.laboratorio.modules.propriedade.model.Propriedade;
import br.com.agro.laboratorio.modules.propriedade.repository.PropriedadeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static br.com.agro.laboratorio.modules.pessoa.helper.PessoaHelper.umaPessoa;
import static br.com.agro.laboratorio.modules.propriedade.helper.PropriedadeHelper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PropriedadeServiceTest {

    @InjectMocks
    private PropriedadeService service;
    @Mock
    private PropriedadeRepository repository;
    @Captor
    private ArgumentCaptor<Propriedade> captor;

    @Test
    void cadastrar_deveCadastrarPropriedade_seChamado() {
        service.cadastrar(umaPropriedadeRequest());

        verify(repository).save(captor.capture());

        assertEquals("Propriedade Um", captor.getValue().getNome());
    }

    @Test
    void listarTodos_deveListarTodasPropriedades_seCadastradas() {
        doReturn(List.of(umaPropriedade(), umaPropriedade()))
            .when(repository)
            .findAll(umaPropriedadePredicate().build());

        assertThat(service.listarTodos(umaPropriedadeFiltros()))
            .extracting(PropriedadeResponse::getId, PropriedadeResponse::getNome)
            .containsExactlyInAnyOrder(
                tuple(1, "Propriedade Um"),
                tuple(1, "Propriedade Um")
            );

        verify(repository).findAll(umaPropriedadePredicate().build());
    }

    @Test
    void editar_deveEditarPropriedade_seCadastrada() {
        var request = umaPropriedadeRequest();
        request.setNome("Propriedade Um Editada");

        doReturn(Optional.of(umaPropriedade()))
            .when(repository)
            .findById(1);

        service.editar(1, request);

        verify(repository).findById(1);
        verify(repository).save(captor.capture());

        assertEquals("Propriedade Um Editada", captor.getValue().getNome());
    }

    @Test
    void editar_deveLancarNotFoundException_sePropriedadeNaoCadastrada() {
        doReturn(Optional.empty())
            .when(repository)
            .findById(1);

        assertThatCode(() -> service.editar(1, umaPropriedadeRequest()))
            .isInstanceOf(NotFoundException.class)
            .hasMessage("Propriedade não cadastrada!");

        verify(repository).findById(1);
        verify(repository, never()).save(any(Propriedade.class));
    }

    @Test
    void excluir_deveExcluirPropriedade_sePropriedadeCadastrada() {
        doReturn(Optional.of(umaPropriedade()))
            .when(repository)
            .findById(1);

        service.excluir(1);

        verify(repository).findById(1);
        verify(repository).delete(umaPropriedade());
    }

    @Test
    void excluir_deveLancarNotFoundExceptionENaoExcluirPropriedade_sePropriedadeNaoCadastrada() {
        doReturn(Optional.empty())
            .when(repository)
            .findById(1);

        assertThatCode(() -> service.excluir(1))
            .isInstanceOf(NotFoundException.class)
            .hasMessage("Propriedade não cadastrada!");

        verify(repository).findById(1);
        verify(repository, never()).delete(any(Propriedade.class));
    }

    @Test
    void excluir_deveLancarValidacaoExceptionENaoExcluirPropriedade_sePropriedadeCadastradaVinculadoAPessoa() {
        doReturn(Optional.of(umaPropriedade(umaPessoa())))
            .when(repository)
            .findById(1);

        assertThatCode(() -> service.excluir(1))
            .isInstanceOf(ValidacaoException.class)
            .hasMessage("Não foi possível excluir! A Propriedade está vinculada a pessoa(s).");

        verify(repository).findById(1);
        verify(repository, never()).delete(any(Propriedade.class));
    }

    @Test
    void getPropriedade_deveRetornarPropriedade_seCadastrada() {
        doReturn(List.of(umaPropriedade()))
            .when(repository)
            .findAllById(List.of(1));

        assertEquals(List.of(umaPropriedade()), service.getPropriedades(List.of(1)));

        verify(repository).findAllById(List.of(1));
    }
}