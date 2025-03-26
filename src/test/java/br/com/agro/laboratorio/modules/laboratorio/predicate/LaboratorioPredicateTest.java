package br.com.agro.laboratorio.modules.laboratorio.predicate;

import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static br.com.agro.laboratorio.modules.comum.helper.DataHelper.umaData;
import static br.com.agro.laboratorio.modules.laboratorio.model.QLaboratorio.laboratorio;
import static br.com.agro.laboratorio.modules.pessoa.model.QPessoa.pessoa;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LaboratorioPredicateTest {

    @Test
    void comFaixaDataInicialPessoa_deveRetornarLaboratorioPredicate_seDataInicialComecoEDataInicialFimInformados() {
        assertEquals(
            new BooleanBuilder(pessoa.dataInicial.between(umaData(), umaData().plusDays(3))),
            new LaboratorioPredicate().comFaixaDataInicialPessoa(umaData(), umaData().plusDays(3)).build()
        );
    }

    @Test
    void comFaixaDataInicialPessoa_naoDeveRetornarLaboratorioPredicate_seDataInicialComecoNula() {
        assertEquals(
            new BooleanBuilder(),
            new LaboratorioPredicate().comFaixaDataInicialPessoa(null, umaData().plusDays(3)).build()
        );
    }

    @Test
    void comFaixaDataInicialPessoa_naoDeveRetornarLaboratorioPredicate_seDataInicialFimNula() {
        assertEquals(
            new BooleanBuilder(),
            new LaboratorioPredicate().comFaixaDataInicialPessoa(umaData(), null).build()
        );
    }

    @Test
    void comFaixaDataInicialPessoa_naoDeveRetornarLaboratorioPredicate_seDataInicialComecoEDataInicialFimNulas() {
        assertEquals(
            new BooleanBuilder(),
            new LaboratorioPredicate().comFaixaDataInicialPessoa(null, null).build()
        );
    }

    @Test
    void comFaixaDataFinalPessoa_deveRetornarLaboratorioPredicate_seDataFinalComecoEDataFinalFimInformados() {
        assertEquals(
            new BooleanBuilder(pessoa.dataFinal.between(umaData(), umaData().plusDays(3))),
            new LaboratorioPredicate().comFaixaDataFinalPessoa(umaData(), umaData().plusDays(3)).build()
        );
    }

    @Test
    void comFaixaDataFinalPessoa_naoDeveRetornarLaboratorioPredicate_seDataFinalComecoNula() {
        assertEquals(
            new BooleanBuilder(),
            new LaboratorioPredicate().comFaixaDataFinalPessoa(null, umaData().plusDays(3)).build()
        );
    }

    @Test
    void comFaixaDataFinalPessoa_naoDeveRetornarLaboratorioPredicate_seDataFinalFimNula() {
        assertEquals(
            new BooleanBuilder(),
            new LaboratorioPredicate().comFaixaDataFinalPessoa(umaData(), null).build()
        );
    }

    @Test
    void comFaixaDataFinalPessoa_naoDeveRetornarLaboratorioPredicate_seDataFinalComecoEDataFinalFimNulas() {
        assertEquals(
            new BooleanBuilder(),
            new LaboratorioPredicate().comFaixaDataFinalPessoa(null, null).build()
        );
    }

    @Test
    void comObservacoesPessoa_deveRetornarLaboratorioPredicate_seObservacoesInformada() {
        assertEquals(
            new BooleanBuilder(pessoa.observacoes.containsIgnoreCase("Uma observação")),
            new LaboratorioPredicate().comObservacoesPessoa("Uma observação").build()
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void comObservacoesPessoa_naoDeveRetornarLaboratorioPredicate_seObservacoesNulaVaziaOuEmBranco(String observacoes) {
        assertEquals(
            new BooleanBuilder(),
            new LaboratorioPredicate().comObservacoesPessoa(observacoes).build()
        );
    }

    @Test
    void comQtdMinimaPessoas_deveRetornarLaboratorioPredicate_seQtdInformada() {
        assertEquals(
            new BooleanBuilder(laboratorio.pessoas.size().goe(3)),
            new LaboratorioPredicate().comQtdMinimaPessoas(3).build()
        );
    }

    @Test
    void comQtdMinimaPessoas_naoDeveRetornarLaboratorioPredicate_seQtdNula() {
        assertEquals(
            new BooleanBuilder(),
            new LaboratorioPredicate().comQtdMinimaPessoas(null).build()
        );
    }
}