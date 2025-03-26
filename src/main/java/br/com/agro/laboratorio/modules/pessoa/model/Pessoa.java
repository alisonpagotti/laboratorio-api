package br.com.agro.laboratorio.modules.pessoa.model;

import br.com.agro.laboratorio.modules.laboratorio.model.Laboratorio;
import br.com.agro.laboratorio.modules.pessoa.dto.PessoaRequest;
import br.com.agro.laboratorio.modules.propriedade.model.Propriedade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PESSOA")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PESSOA")
    @SequenceGenerator(name = "SEQ_PESSOA", sequenceName = "SEQ_PESSOA", allocationSize = 1)
    private Integer id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "DATA_INICIAL", nullable = false)
    private LocalDate dataInicial;

    @Column(name = "DATA_FINAL", nullable = false)
    private LocalDate dataFinal;

    @JoinTable(
        name = "PESSOA_PROPRIEDADE",
        joinColumns = {
            @JoinColumn(name = "FK_PESSOA", foreignKey = @ForeignKey(name = "FK_PESSOA_ID"), referencedColumnName = "id")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "FK_PROPRIEDADE", foreignKey = @ForeignKey(name = "FK_PROPRIEDADE_ID"), referencedColumnName = "id")
        }
    )
    @ManyToMany
    private List<Propriedade> propriedades;

    @JoinColumn(name = "FK_LABORATORIO", referencedColumnName = "ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Laboratorio laboratorio;

    @Column(name = "OBSERVACOES", length = 100)
    private String observacoes;

    public static Pessoa of(PessoaRequest request, List<Propriedade> propriedades, Laboratorio laboratorio) {
        return Pessoa
            .builder()
            .nome(request.getNome())
            .dataInicial(request.getDataInicial())
            .dataFinal(request.getDataFinal())
            .propriedades(propriedades)
            .laboratorio(laboratorio)
            .observacoes(request.getObservacoes())
            .build();
    }
}
