package br.com.agro.laboratorio.modules.laboratorio.controller;

import br.com.agro.laboratorio.modules.laboratorio.dto.LaboratorioFiltros;
import br.com.agro.laboratorio.modules.laboratorio.dto.LaboratorioRequest;
import br.com.agro.laboratorio.modules.laboratorio.service.LaboratorioService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.agro.laboratorio.modules.comum.helper.ControllerHelper.convertObjectToJsonBytes;
import static br.com.agro.laboratorio.modules.laboratorio.helper.LaboratorioHelper.umLaboratorioRequest;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LaboratorioController.class)
class LaboratorioControllerTest {

    private static final String API_URL = "/api/laboratorios";

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private LaboratorioService service;

    @Test
    @SneakyThrows
    void cadastrar_deveRetornarBadRequest_seDadosObrigatoriosNaoPreenchidos() {
        mockMvc.perform(post(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(new LaboratorioRequest())))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$[*].message", containsInAnyOrder(
                "O campo nome é obrigatório."
            )));

        verify(service, never()).cadastrar(any(LaboratorioRequest.class));
    }

    @Test
    @SneakyThrows
    void cadastrar_deveRetornarRetornarOk_seDadosObrigatorioPreenchidos() {
        mockMvc.perform(post(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umLaboratorioRequest())))
            .andExpect(status().isOk());

        verify(service).cadastrar(umLaboratorioRequest());
    }

    @Test
    @SneakyThrows
    void listarTodos_deveRetornarOk_seChamado() {
        mockMvc.perform(get(API_URL))
            .andExpect(status().isOk());

        verify(service).listarTodos(any(LaboratorioFiltros.class));
    }

    @Test
    @SneakyThrows
    void editar_deveRetornarBadRequest_seDadosObrigatoriosNaoPreenchidos() {
        mockMvc.perform(put(API_URL + "/{id}/editar", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(new LaboratorioRequest())))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$[*].message", containsInAnyOrder(
                "O campo nome é obrigatório."
            )));

        verify(service, never()).editar(anyInt(), any(LaboratorioRequest.class));
    }

    @Test
    @SneakyThrows
    void editar_deveRetornarOk_seDadosObrigatoriosPreenchidos() {
        mockMvc.perform(put(API_URL + "/{id}/editar", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(umLaboratorioRequest())))
            .andExpect(status().isOk());

        verify(service).editar(anyInt(), any(LaboratorioRequest.class));
    }

    @Test
    @SneakyThrows
    void excluir_deveRetornarOk_seChamado() {
        mockMvc.perform(delete(API_URL + "/{id}", 1))
            .andExpect(status().isOk());

        verify(service).excluir(1);
    }
}