package br.com.agro.laboratorio.modules.propriedade.model;

import org.junit.jupiter.api.Test;

import static br.com.agro.laboratorio.modules.propriedade.helper.PropriedadeHelper.umaPropriedadeRequest;
import static org.assertj.core.api.Assertions.assertThat;

class PropriedadeTest {

    @Test
    void of_deveRetornarPropriedade_seChamado() {
        assertThat(Propriedade.of(umaPropriedadeRequest()))
            .extracting(Propriedade::getId, Propriedade::getNome, Propriedade::getPessoas)
            .containsExactly(null, "Propriedade Um", null);
    }
}