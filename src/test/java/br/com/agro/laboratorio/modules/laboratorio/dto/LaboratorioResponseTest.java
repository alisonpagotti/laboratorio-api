package br.com.agro.laboratorio.modules.laboratorio.dto;

import org.junit.jupiter.api.Test;

import static br.com.agro.laboratorio.modules.laboratorio.helper.LaboratorioHelper.umLaboratorio;
import static br.com.agro.laboratorio.modules.pessoa.helper.PessoaHelper.umaPessoa;
import static org.assertj.core.api.Assertions.assertThat;

class LaboratorioResponseTest {

    @Test
    void of_deveRetornarLaboratorioResponseComQtdPessoasMaiorQueZero_sePossuirPessoasVinculadas() {
        assertThat(LaboratorioResponse.of(umLaboratorio(umaPessoa(), umaPessoa())))
            .extracting(LaboratorioResponse::getId, LaboratorioResponse::getNome, LaboratorioResponse::getQtdPessoas)
            .containsExactly(1, "LABORATÓRIO UM", 2);
    }

    @Test
    void of_deveRetornarLaboratorioResponseComQtdPessoasZero_seNaoPossuirPessoasVinculadas() {
        assertThat(LaboratorioResponse.of(umLaboratorio()))
            .extracting(LaboratorioResponse::getId, LaboratorioResponse::getNome, LaboratorioResponse::getQtdPessoas)
            .containsExactly(1, "LABORATÓRIO UM", 0);
    }
}