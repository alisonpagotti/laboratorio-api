package br.com.agro.laboratorio.modules.propriedade.dto;

import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.Test;

import static br.com.agro.laboratorio.modules.propriedade.helper.PropriedadeHelper.umaPropriedadeFiltros;
import static br.com.agro.laboratorio.modules.propriedade.helper.PropriedadeHelper.umaPropriedadePredicate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PropriedadeFiltrosTest {

    @Test
    void toPredicate_deveMontarPredicate_sePossuirFiltros() {
        assertEquals(
            umaPropriedadeFiltros().toPredicate().build(),
            umaPropriedadePredicate().build()
        );
    }

    @Test
    void toPredicate_naoDeveMontarPredicate_seNaoPossuirFiltros() {
        assertEquals(
            new PropriedadeFiltros().toPredicate().build(),
            new BooleanBuilder()
        );
    }
}