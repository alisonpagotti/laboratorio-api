package br.com.agro.laboratorio.modules.laboratorio.dto;

import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.Test;

import static br.com.agro.laboratorio.modules.laboratorio.helper.LaboratorioHelper.umLaboratorioFiltros;
import static br.com.agro.laboratorio.modules.laboratorio.helper.LaboratorioHelper.umLaboratorioPredicate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LaboratorioFiltrosTest {

    @Test
    void toPredicate_deveMontarPredicate_sePossuirFiltros() {
        assertEquals(
            umLaboratorioFiltros().toPredicate().build(),
            umLaboratorioPredicate().build()
        );
    }

    @Test
    void toPredicate_naoDeveMontarPredicate_seNaoPossuirFiltros() {
        assertEquals(
            new LaboratorioFiltros().toPredicate().build(),
            new BooleanBuilder()
        );
    }
}