package br.com.agro.laboratorio.modules.comum.util;

import io.micrometer.common.util.StringUtils;
import lombok.experimental.UtilityClass;

import static org.springframework.util.ObjectUtils.isEmpty;

@UtilityClass
public class NumberUtil {

    public static final int ZERO = 0;
    public static final int DOIS = 2;
    private static final String NAO_NUMERICO = "\\D+";

    public static boolean isNumber(String codigoOuNome) {
        return !isEmpty(getOnlyNumbers(codigoOuNome));
    }

    private static String getOnlyNumbers(String value) {
        return StringUtils.isNotBlank(value)
            ? value.replaceAll(NAO_NUMERICO, "")
            : null;
    }
}
