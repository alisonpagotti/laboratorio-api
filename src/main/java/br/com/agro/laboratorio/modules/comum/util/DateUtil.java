package br.com.agro.laboratorio.modules.comum.util;

import br.com.agro.laboratorio.modules.comum.exception.ValidacaoException;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class DateUtil {

    public static void validarDataInicialPosteriorADataFinal(LocalDate dataInicial, LocalDate dataFinal) {
        if (hasDatas(dataInicial, dataFinal) && dataInicial.isAfter(dataFinal)) {
            throw new ValidacaoException("A data inicial não pode ser posterior a data final.");
        }
    }

    private static boolean hasDatas(LocalDate dataUm, LocalDate dataDois) {
        return dataUm != null && dataDois != null;
    }
}
