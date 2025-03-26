package br.com.agro.laboratorio.modules.pessoa.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static br.com.agro.laboratorio.modules.comum.helper.DataHelper.umaData;
import static br.com.agro.laboratorio.modules.laboratorio.helper.LaboratorioHelper.umLaboratorio;
import static br.com.agro.laboratorio.modules.pessoa.helper.PessoaHelper.umaPessoaRequest;
import static br.com.agro.laboratorio.modules.propriedade.helper.PropriedadeHelper.umaPropriedade;
import static org.assertj.core.api.Assertions.assertThat;

class PessoaTest {

    @Test
    void of_deveRetornarPessoa_seChamado() {
        assertThat(Pessoa.of(umaPessoaRequest(), List.of(umaPropriedade()), umLaboratorio()))
            .extracting(
                Pessoa::getId,
                Pessoa::getNome,
                Pessoa::getDataInicial,
                Pessoa::getDataFinal,
                Pessoa::getPropriedades,
                Pessoa::getLaboratorio,
                Pessoa::getObservacoes
            )
            .containsExactly(
                null,
                "Pessoa Um",
                umaData(),
                umaData().plusDays(5),
                List.of(umaPropriedade()),
                umLaboratorio(),
                "Uma observação da Pessoa Um"
            );
    }
}