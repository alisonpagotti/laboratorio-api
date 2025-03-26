package br.com.agro.laboratorio.modules.laboratorio.model;

import org.junit.jupiter.api.Test;

import static br.com.agro.laboratorio.modules.laboratorio.helper.LaboratorioHelper.umLaboratorioRequest;
import static org.assertj.core.api.Assertions.assertThat;

class LaboratorioTest {

    @Test
    void of_deveRetornarLaboratorio_seChamado() {
        assertThat(Laboratorio.of(umLaboratorioRequest()))
            .extracting(Laboratorio::getId, Laboratorio::getNome, Laboratorio::getPessoas)
            .containsExactly(null, "Laboratório Um", null);
    }
}