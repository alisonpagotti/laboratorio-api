package br.com.agro.laboratorio.modules.laboratorio.dto;

import br.com.agro.laboratorio.modules.laboratorio.model.Laboratorio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static br.com.agro.laboratorio.modules.comum.util.NumberUtil.ZERO;
import static org.springframework.util.ObjectUtils.isEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LaboratorioResponse {

    private Integer id;
    private String nome;
    private Integer qtdPessoas;

    public static LaboratorioResponse of(Laboratorio laboratorio) {
        return LaboratorioResponse
            .builder()
            .id(laboratorio.getId())
            .nome(laboratorio.getNome().toUpperCase())
            .qtdPessoas(getQtdPessoas(laboratorio))
            .build();
    }

    private static Integer getQtdPessoas(Laboratorio laboratorio) {
        return !isEmpty(laboratorio.getPessoas())
            ? laboratorio.getPessoas().size()
            : ZERO;
    }
}
