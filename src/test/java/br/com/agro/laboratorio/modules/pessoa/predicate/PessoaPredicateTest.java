package br.com.agro.laboratorio.modules.pessoa.predicate;

import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static br.com.agro.laboratorio.modules.pessoa.model.QPessoa.pessoa;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PessoaPredicateTest {

    @Test
    void comCodigoOuNome_deveRetornarPessoaPredicate_seCodigoInformado() {
        assertEquals(
            new BooleanBuilder(pessoa.id.eq(Integer.valueOf("1234"))),
            new PessoaPredicate().comCodigoOuNome("1234").build()
        );
    }

    @Test
    void comCodigoOuNome_deveRetornarPessoaPredicate_seNomeInformado() {
        assertEquals(
            new BooleanBuilder(pessoa.nome.equalsIgnoreCase("Pessoa Um")),
            new PessoaPredicate().comCodigoOuNome("Pessoa Um").build()
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void comCodigoOuNome_naoDeveRetornarPessoaPredicate_seCodigoOuNomeNuloVazioOuEmBranco(String codigoOuNome) {
        assertEquals(
            new BooleanBuilder(),
            new PessoaPredicate().comCodigoOuNome(codigoOuNome).build()
        );
    }
}