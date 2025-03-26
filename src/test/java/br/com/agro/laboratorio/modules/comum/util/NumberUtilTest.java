package br.com.agro.laboratorio.modules.comum.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberUtilTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "abcde"})
    void isNumber_deveRetornarFalse_seValorNuloVazioEmBrancoOuApenasLetras(String valor) {
        assertFalse(NumberUtil.isNumber(valor));
    }

    @Test
    void isNumber_deveRetornarTrue_seValorNumerico() {
        assertTrue(NumberUtil.isNumber("1234"));
    }
}