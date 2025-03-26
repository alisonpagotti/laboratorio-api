package br.com.agro.laboratorio.modules.pessoa.dto;

import br.com.agro.laboratorio.modules.laboratorio.model.Laboratorio;
import br.com.agro.laboratorio.modules.pessoa.model.Pessoa;
import br.com.agro.laboratorio.modules.propriedade.model.Propriedade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaResponse {

    private Integer id;
    private String nome;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private List<Propriedade> propriedades;
    private Laboratorio laboratorio;
    private String observacoes;

    public static PessoaResponse of(Pessoa pessoa) {
        var response = new PessoaResponse();
        copyProperties(pessoa, response);
        return response;
    }
}
