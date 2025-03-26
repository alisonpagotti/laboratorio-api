package br.com.agro.laboratorio.modules.propriedade.dto;

import org.junit.jupiter.api.Test;

import static br.com.agro.laboratorio.modules.propriedade.helper.PropriedadeHelper.umaPropriedade;
import static org.assertj.core.api.Assertions.assertThat;

class PropriedadeResponseTest {

    @Test
    void of_deveRetornarPropriedadeResponse_seChamado() {
        assertThat(PropriedadeResponse.of(umaPropriedade()))
            .extracting(PropriedadeResponse::getId, PropriedadeResponse::getNome)
            .containsExactly(1, "Propriedade Um");
    }
}