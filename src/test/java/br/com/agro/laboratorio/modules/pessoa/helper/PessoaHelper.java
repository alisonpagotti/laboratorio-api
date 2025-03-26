package br.com.agro.laboratorio.modules.pessoa.helper;

import br.com.agro.laboratorio.modules.pessoa.dto.PessoaFiltros;
import br.com.agro.laboratorio.modules.pessoa.dto.PessoaRequest;
import br.com.agro.laboratorio.modules.pessoa.model.Pessoa;
import br.com.agro.laboratorio.modules.pessoa.predicate.PessoaPredicate;
import lombok.experimental.UtilityClass;

import java.util.List;

import static br.com.agro.laboratorio.modules.comum.helper.DataHelper.umaData;
import static br.com.agro.laboratorio.modules.laboratorio.helper.LaboratorioHelper.umLaboratorio;
import static br.com.agro.laboratorio.modules.propriedade.helper.PropriedadeHelper.umaPropriedade;

@UtilityClass
public class PessoaHelper {

    public static PessoaRequest umaPessoaRequest() {
        return PessoaRequest
            .builder()
            .nome("Pessoa Um")
            .dataInicial(umaData())
            .dataFinal(umaData().plusDays(5))
            .propriedadesIds(List.of(1, 2))
            .laboratorioId(1)
            .observacoes("Uma observação da Pessoa Um")
            .build();
    }

    public static PessoaFiltros umaPessoaFiltros() {
        return PessoaFiltros.builder().codigoOuNome("Pessoa Um").build();
    }

    public static PessoaPredicate umaPessoaPredicate() {
        return new PessoaPredicate().comCodigoOuNome("Pessoa Um");
    }

    public static Pessoa umaPessoa() {
        return Pessoa
            .builder()
            .id(1)
            .nome("Pessoa Um")
            .dataInicial(umaData())
            .dataFinal(umaData().plusDays(5))
            .propriedades(List.of(umaPropriedade(), umaPropriedade()))
            .laboratorio(umLaboratorio())
            .observacoes("Uma observação da Pessoa um")
            .build();
    }
}
