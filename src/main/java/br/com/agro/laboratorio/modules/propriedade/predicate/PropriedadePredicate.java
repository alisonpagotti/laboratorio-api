package br.com.agro.laboratorio.modules.propriedade.predicate;

import br.com.agro.laboratorio.modules.comum.predicate.PredicateBase;

import static br.com.agro.laboratorio.modules.comum.util.NumberUtil.isNumber;
import static br.com.agro.laboratorio.modules.propriedade.model.QPropriedade.propriedade;
import static io.micrometer.common.util.StringUtils.isNotBlank;

public class PropriedadePredicate extends PredicateBase {

    public PropriedadePredicate comCodigoOuNome(String codigoOuNome) {
        if (isNotBlank(codigoOuNome)) {
            if (isNumber(codigoOuNome)) {
                builder.and(propriedade.id.eq(Integer.valueOf(codigoOuNome)));
            } else {
                builder.and(propriedade.nome.equalsIgnoreCase(codigoOuNome));
            }
        }
        return this;
    }
}
