package br.com.agro.laboratorio.modules.comum.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;

public class ControllerHelper {

    @SneakyThrows
    public static byte[] convertObjectToJsonBytes(Object object) {
        return getMapper().writeValueAsBytes(object);
    }

    private static ObjectMapper getMapper() {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}
