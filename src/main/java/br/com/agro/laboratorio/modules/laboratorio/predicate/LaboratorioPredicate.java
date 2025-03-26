package br.com.agro.laboratorio.modules.laboratorio.predicate;

import br.com.agro.laboratorio.modules.comum.predicate.PredicateBase;

import java.time.LocalDate;

import static br.com.agro.laboratorio.modules.laboratorio.model.QLaboratorio.laboratorio;
import static br.com.agro.laboratorio.modules.pessoa.model.QPessoa.pessoa;
import static io.micrometer.common.util.StringUtils.isNotBlank;

public class LaboratorioPredicate extends PredicateBase {

    public LaboratorioPredicate comFaixaDataInicialPessoa(LocalDate dataInicialComeco, LocalDate dataInicialFim) {
        if (dataInicialComeco != null && dataInicialFim != null) {
            builder.and(pessoa.dataInicial.between(dataInicialComeco, dataInicialFim));
        }
        return this;
    }

    public LaboratorioPredicate comFaixaDataFinalPessoa(LocalDate dataFinalComeco, LocalDate dataFinalFim) {
        if (dataFinalComeco != null && dataFinalFim != null) {
            builder.and(pessoa.dataFinal.between(dataFinalComeco, dataFinalFim));
        }
        return this;
    }

    public LaboratorioPredicate comObservacoesPessoa(String observacoes) {
        if (isNotBlank(observacoes)) {
            builder.and(pessoa.observacoes.containsIgnoreCase(observacoes));
        }
        return this;
    }

    public LaboratorioPredicate comQtdMinimaPessoas(Integer qtd) {
        if (qtd != null) {
            builder.and(laboratorio.pessoas.size().goe(qtd));
        }
        return this;
    }
}
