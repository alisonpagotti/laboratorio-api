package br.com.agro.laboratorio.modules.pessoa.predicate;

import br.com.agro.laboratorio.modules.comum.predicate.PredicateBase;

import static br.com.agro.laboratorio.modules.comum.util.NumberUtil.isNumber;
import static br.com.agro.laboratorio.modules.pessoa.model.QPessoa.pessoa;
import static io.micrometer.common.util.StringUtils.isNotBlank;

public class PessoaPredicate extends PredicateBase {

    public PessoaPredicate comCodigoOuNome(String codigoOuNome) {
        if (isNotBlank(codigoOuNome)) {
            if (isNumber(codigoOuNome)) {
                builder.and(pessoa.id.eq(Integer.valueOf(codigoOuNome)));
            } else {
                builder.and(pessoa.nome.equalsIgnoreCase(codigoOuNome));
            }
        }
        return this;
    }
}
