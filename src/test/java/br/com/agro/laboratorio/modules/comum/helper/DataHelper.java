package br.com.agro.laboratorio.modules.comum.helper;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class DataHelper {

    public static LocalDate umaData() {
        return LocalDate.now();
    }
}
