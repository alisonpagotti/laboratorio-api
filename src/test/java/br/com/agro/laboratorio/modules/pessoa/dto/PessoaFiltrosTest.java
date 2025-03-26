package br.com.agro.laboratorio.modules.pessoa.dto;

import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.Test;

import static br.com.agro.laboratorio.modules.pessoa.helper.PessoaHelper.umaPessoaFiltros;
import static br.com.agro.laboratorio.modules.pessoa.helper.PessoaHelper.umaPessoaPredicate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PessoaFiltrosTest {

    @Test
    void toPredicate_deveMontarPredicate_sePossuirFiltros() {
        assertEquals(
            umaPessoaFiltros().toPredicate().build(),
            umaPessoaPredicate().build()
        );
    }

    @Test
    void toPredicate_naoDeveMontarPredicate_seNaoPossuirFiltros() {
        assertEquals(
            new PessoaFiltros().toPredicate().build(),
            new BooleanBuilder()
        );
    }
}