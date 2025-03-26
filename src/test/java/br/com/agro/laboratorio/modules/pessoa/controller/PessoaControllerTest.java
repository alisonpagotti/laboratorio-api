package br.com.agro.laboratorio.modules.pessoa.controller;

import br.com.agro.laboratorio.modules.pessoa.dto.PessoaFiltros;
import br.com.agro.laboratorio.modules.pessoa.dto.PessoaRequest;
import br.com.agro.laboratorio.modules.pessoa.service.PessoaService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.agro.laboratorio.modules.comum.helper.ControllerHelper.convertObjectToJsonBytes;
import static br.com.agro.laboratorio.modules.pessoa.helper.PessoaHelper.umaPessoaRequest;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PessoaController.class)
class PessoaControllerTest {
    private static final String API_URL = "/api/pessoas";

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private PessoaService service;

    @Test
    @SneakyThrows
    void cadastrar_deveRetornarBadRequest_seDadosObrigatoriosNaoPreenchidos() {
        mockMvc.perform(post(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(new PessoaRequest())))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$[*].message", containsInAnyOrder(
                "O campo nome é obrigatório.",
                "O campo dataInicial é obrigatório.",
                "O campo dataFinal é obrigatório.",
                "O campo propriedadesIds é obrigatório.",
                "O campo laboratorioId é obrigatório."
            )));

        verify(service, never()).cadastrar(any(PessoaRequest.class));
    }

    @Test
    @SneakyThrows
    void cadastrar_deveRetornarRetornarOk_seDadosObrigatorioPreenchidos() {
        mockMvc.perform(post(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umaPessoaRequest())))
            .andExpect(status().isOk());

        verify(service).cadastrar(umaPessoaRequest());
    }

    @Test
    @SneakyThrows
    void listarTodos_deveRetornarOk_seChamado() {
        mockMvc.perform(get(API_URL))
            .andExpect(status().isOk());

        verify(service).listarTodos(any(PessoaFiltros.class));
    }

    @Test
    @SneakyThrows
    void editar_deveRetornarOk_seDadosObrigatoriosPreenchidos() {
        mockMvc.perform(put(API_URL + "/{id}/editar", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umaPessoaRequest())))
            .andExpect(status().isOk());

        verify(service).editar(anyInt(), any(PessoaRequest.class));
    }

    @Test
    @SneakyThrows
    void excluir_deveRetornarOk_seChamado() {
        mockMvc.perform(delete(API_URL + "/{id}", 1))
            .andExpect(status().isOk());

        verify(service).excluir(1);
    }
}