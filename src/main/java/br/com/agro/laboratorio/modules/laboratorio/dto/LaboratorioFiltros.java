package br.com.agro.laboratorio.modules.laboratorio.dto;

import br.com.agro.laboratorio.modules.laboratorio.predicate.LaboratorioPredicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LaboratorioFiltros {

    private LocalDate dataInicialComeco;
    private LocalDate dataInicialFim;
    private LocalDate dataFinalComeco;
    private LocalDate dataFinalFim;
    private String observacoes;
    private Integer qtdMinPessoas;

    public LaboratorioPredicate toPredicate() {
        return new LaboratorioPredicate()
            .comFaixaDataInicialPessoa(dataInicialComeco, dataInicialFim)
            .comFaixaDataFinalPessoa(dataFinalComeco, dataFinalFim)
            .comObservacoesPessoa(observacoes)
            .comQtdMinimaPessoas(qtdMinPessoas);
    }
}
