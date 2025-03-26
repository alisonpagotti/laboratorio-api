package br.com.agro.laboratorio.modules.comum.util;

import br.com.agro.laboratorio.modules.comum.exception.ValidacaoException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static br.com.agro.laboratorio.modules.comum.util.DateUtil.validarDataInicialPosteriorADataFinal;
import static br.com.agro.laboratorio.modules.comum.util.NumberUtil.DOIS;
import static org.assertj.core.api.Assertions.assertThatCode;

class DateUtilTest {

    @Test
    void validarDataInicialPosteriorADataFinal_deveLancarException_seDataInicialPosteriorADataFinal() {
        assertThatCode(() -> validarDataInicialPosteriorADataFinal(umaDataAtual(), umaDataFinalAnteriorADataInicial()))
            .isInstanceOf(ValidacaoException.class)
            .hasMessage("A data inicial não pode ser posterior a data final.");
    }

    @Test
    void validarDataInicialPosteriorADataFinal_naoDeveLancarException_seDataInicialAnterioADataFinal() {
        assertThatCode(() -> validarDataInicialPosteriorADataFinal(umaDataAtual(), umaDataAtualMais()))
            .doesNotThrowAnyException();
    }

    @Test
    void validarDataInicialPosteriorADataFinal_naoDeveLancarException_seDataInicialNula() {
        assertThatCode(() -> validarDataInicialPosteriorADataFinal(null, umaDataAtualMais()))
            .doesNotThrowAnyException();
    }

    @Test
    void validarDataInicialPosteriorADataFinal_naoDeveLancarException_seDataFinalNula() {
        assertThatCode(() -> validarDataInicialPosteriorADataFinal(umaDataAtual(), null))
            .doesNotThrowAnyException();
    }

    @Test
    void validarDataInicialPosteriorADataFinal_naoDeveLancarException_seDataInicialEFinalNulas() {
        assertThatCode(() -> validarDataInicialPosteriorADataFinal(null, null))
            .doesNotThrowAnyException();
    }

    private LocalDate umaDataAtual() {
        return LocalDate.now();
    }

    private LocalDate umaDataFinalAnteriorADataInicial() {
        return umaDataAtual().minusDays(1);
    }

    private LocalDate umaDataAtualMais() {
        return umaDataAtual().plusDays(DOIS);
    }
}