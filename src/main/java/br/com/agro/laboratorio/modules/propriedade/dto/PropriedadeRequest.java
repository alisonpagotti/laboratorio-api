package br.com.agro.laboratorio.modules.propriedade.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropriedadeRequest {

    @NotBlank
    private String nome;
}
