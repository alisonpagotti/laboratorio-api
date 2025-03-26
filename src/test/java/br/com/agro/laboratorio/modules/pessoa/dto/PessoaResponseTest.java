package br.com.agro.laboratorio.modules.pessoa.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static br.com.agro.laboratorio.modules.comum.helper.DataHelper.umaData;
import static br.com.agro.laboratorio.modules.laboratorio.helper.LaboratorioHelper.umLaboratorio;
import static br.com.agro.laboratorio.modules.pessoa.helper.PessoaHelper.umaPessoa;
import static br.com.agro.laboratorio.modules.propriedade.helper.PropriedadeHelper.umaPropriedade;
import static org.assertj.core.api.Assertions.assertThat;

class PessoaResponseTest {

    @Test
    void of_deveRetornarPessoaResponse_seChamado() {
        assertThat(PessoaResponse.of(umaPessoa()))
            .extracting(
                PessoaResponse::getId,
                PessoaResponse::getNome,
                PessoaResponse::getDataInicial,
                PessoaResponse::getDataFinal,
                PessoaResponse::getPropriedades,
                PessoaResponse::getLaboratorio
            )
            .containsExactly(
                1,
                "Pessoa Um",
                umaData(),
                umaData().plusDays(5),
                List.of(umaPropriedade(), umaPropriedade()),
                umLaboratorio()
            );
    }
}