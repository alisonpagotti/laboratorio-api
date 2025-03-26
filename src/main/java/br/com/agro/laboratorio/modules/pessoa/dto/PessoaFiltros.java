package br.com.agro.laboratorio.modules.pessoa.dto;

import br.com.agro.laboratorio.modules.pessoa.predicate.PessoaPredicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaFiltros {

    private String codigoOuNome;

    public PessoaPredicate toPredicate() {
        return new PessoaPredicate()
            .comCodigoOuNome(codigoOuNome);
    }
}
