package br.com.agro.laboratorio.modules.propriedade.predicate;

import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static br.com.agro.laboratorio.modules.propriedade.model.QPropriedade.propriedade;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PropriedadePredicateTest {

    @Test
    void comCodigoOuNome_deveRetornarPropriedadePredicate_seCodigoInformado() {
        assertEquals(
            new BooleanBuilder(propriedade.id.eq(Integer.valueOf("1234"))),
            new PropriedadePredicate().comCodigoOuNome("1234").build()
        );
    }

    @Test
    void comCodigoOuNome_deveRetornarPropriedadePredicate_seNomeInformado() {
        assertEquals(
            new BooleanBuilder(propriedade.nome.equalsIgnoreCase("Pessoa Um")),
            new PropriedadePredicate().comCodigoOuNome("Pessoa Um").build()
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void comCodigoOuNome_naoDeveRetornarPropriedadePredicate_seCodigoOuNomeNuloVazioOuEmBranco(String codigoOuNome) {
        assertEquals(
            new BooleanBuilder(),
            new PropriedadePredicate().comCodigoOuNome(codigoOuNome).build()
        );
    }
}