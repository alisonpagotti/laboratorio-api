package br.com.agro.laboratorio.modules.propriedade.controller;

import br.com.agro.laboratorio.modules.propriedade.dto.PropriedadeFiltros;
import br.com.agro.laboratorio.modules.propriedade.dto.PropriedadeRequest;
import br.com.agro.laboratorio.modules.propriedade.service.PropriedadeService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.agro.laboratorio.modules.comum.helper.ControllerHelper.convertObjectToJsonBytes;
import static br.com.agro.laboratorio.modules.propriedade.helper.PropriedadeHelper.umaPropriedadeRequest;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PropriedadeController.class)
class PropriedadeControllerTest {

    private static final String API_URL = "/api/propriedades";

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private PropriedadeService service;

    @Test
    @SneakyThrows
    void cadastrar_deveRetornarBadRequest_seDadosObrigatoriosNaoPreenchidos() {
        mockMvc.perform(post(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(new PropriedadeRequest())))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$[*].message", containsInAnyOrder(
                "O campo nome é obrigatório."
            )));

        verify(service, never()).cadastrar(any(PropriedadeRequest.class));
    }

    @Test
    @SneakyThrows
    void cadastrar_deveRetornarRetornarOk_seDadosObrigatorioPreenchidos() {
        mockMvc.perform(post(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umaPropriedadeRequest())))
            .andExpect(status().isOk());

        verify(service).cadastrar(umaPropriedadeRequest());
    }

    @Test
    @SneakyThrows
    void listarTodos_deveRetornarOk_seChamado() {
        mockMvc.perform(get(API_URL))
            .andExpect(status().isOk());

        verify(service).listarTodos(any(PropriedadeFiltros.class));
    }

    @Test
    @SneakyThrows
    void editar_deveRetornarBadRequest_seDadosObrigatoriosNaoPreenchidos() {
        mockMvc.perform(put(API_URL + "/{id}/editar", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(new PropriedadeRequest())))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$[*].message", containsInAnyOrder(
                "O campo nome é obrigatório."
            )));

        verify(service, never()).editar(anyInt(), any(PropriedadeRequest.class));
    }

    @Test
    @SneakyThrows
    void editar_deveRetornarOk_seDadosObrigatoriosPreenchidos() {
        mockMvc.perform(put(API_URL + "/{id}/editar", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umaPropriedadeRequest())))
            .andExpect(status().isOk());

        verify(service).editar(anyInt(), any(PropriedadeRequest.class));
    }

    @Test
    @SneakyThrows
    void excluir_deveRetornarOk_seChamado() {
        mockMvc.perform(delete(API_URL + "/{id}", 1))
            .andExpect(status().isOk());

        verify(service).excluir(1);
    }
}