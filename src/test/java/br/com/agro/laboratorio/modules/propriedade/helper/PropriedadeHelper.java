package br.com.agro.laboratorio.modules.propriedade.helper;

import br.com.agro.laboratorio.modules.pessoa.model.Pessoa;
import br.com.agro.laboratorio.modules.propriedade.dto.PropriedadeFiltros;
import br.com.agro.laboratorio.modules.propriedade.dto.PropriedadeRequest;
import br.com.agro.laboratorio.modules.propriedade.model.Propriedade;
import br.com.agro.laboratorio.modules.propriedade.predicate.PropriedadePredicate;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class PropriedadeHelper {

    public static PropriedadeRequest umaPropriedadeRequest() {
        return PropriedadeRequest.builder().nome("Propriedade Um").build();
    }

    public static PropriedadeFiltros umaPropriedadeFiltros() {
        return PropriedadeFiltros.builder().codigoOuNome("Propriedade Um").build();
    }

    public static PropriedadePredicate umaPropriedadePredicate() {
        return new PropriedadePredicate().comCodigoOuNome("Propriedade Um");
    }

    public static Propriedade umaPropriedade(Pessoa... pessoa) {
        return Propriedade
            .builder()
            .id(1)
            .nome("Propriedade Um")
            .pessoas(List.of(pessoa))
            .build();
    }


}
