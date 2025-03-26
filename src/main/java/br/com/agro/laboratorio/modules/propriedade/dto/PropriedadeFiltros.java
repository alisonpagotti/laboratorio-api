package br.com.agro.laboratorio.modules.propriedade.dto;

import br.com.agro.laboratorio.modules.propriedade.predicate.PropriedadePredicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropriedadeFiltros {

    private String codigoOuNome;

    public PropriedadePredicate toPredicate() {
        return new PropriedadePredicate()
            .comCodigoOuNome(codigoOuNome);
    }
}
