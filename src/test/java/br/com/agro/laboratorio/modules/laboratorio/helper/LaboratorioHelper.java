package br.com.agro.laboratorio.modules.laboratorio.helper;

import br.com.agro.laboratorio.modules.laboratorio.dto.LaboratorioFiltros;
import br.com.agro.laboratorio.modules.laboratorio.dto.LaboratorioRequest;
import br.com.agro.laboratorio.modules.laboratorio.model.Laboratorio;
import br.com.agro.laboratorio.modules.laboratorio.predicate.LaboratorioPredicate;
import br.com.agro.laboratorio.modules.pessoa.model.Pessoa;
import lombok.experimental.UtilityClass;

import java.util.List;

import static br.com.agro.laboratorio.modules.comum.helper.DataHelper.umaData;

@UtilityClass
public class LaboratorioHelper {

    public static LaboratorioRequest umLaboratorioRequest() {
        return LaboratorioRequest.builder().nome("Laboratório Um").build();
    }

    public static LaboratorioFiltros umLaboratorioFiltros() {
        return LaboratorioFiltros
            .builder()
            .dataInicialComeco(umaData())
            .dataInicialFim(umaData().plusDays(5))
            .dataFinalComeco(umaData().plusDays(3))
            .dataFinalFim(umaData().plusDays(36))
            .observacoes("Uma observação laboratório")
            .qtdMinPessoas(2)
            .build();
    }

    public static LaboratorioPredicate umLaboratorioPredicate() {
        return new LaboratorioPredicate()
            .comFaixaDataInicialPessoa(umaData(), umaData().plusDays(5))
            .comFaixaDataFinalPessoa(umaData().plusDays(3), umaData().plusDays(36))
            .comObservacoesPessoa("Uma observação laboratório")
            .comQtdMinimaPessoas(2);
    }

    public static Laboratorio umLaboratorio(Pessoa... pessoa) {
        return Laboratorio
            .builder()
            .id(1)
            .nome("Laboratório Um")
            .pessoas(List.of(pessoa))
            .build();
    }
}
