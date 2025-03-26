package br.com.agro.laboratorio.modules.pessoa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaRequest {

    @NotBlank
    private String nome;
    @NotNull
    @FutureOrPresent
    private LocalDate dataInicial;
    @NotNull
    @FutureOrPresent
    private LocalDate dataFinal;
    @NotEmpty
    private List<Integer> propriedadesIds;
    @NotNull
    private Integer laboratorioId;
    private String observacoes;
}
