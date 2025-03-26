package br.com.agro.laboratorio.modules.propriedade.dto;

import br.com.agro.laboratorio.modules.propriedade.model.Propriedade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropriedadeResponse {

    private Integer id;
    private String nome;

    public static PropriedadeResponse of(Propriedade propriedade) {
        var response = new PropriedadeResponse();
        copyProperties(propriedade, response);
        return response;
    }
}
