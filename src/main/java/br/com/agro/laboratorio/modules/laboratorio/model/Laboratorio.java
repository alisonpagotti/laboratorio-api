package br.com.agro.laboratorio.modules.laboratorio.model;

import br.com.agro.laboratorio.modules.laboratorio.dto.LaboratorioRequest;
import br.com.agro.laboratorio.modules.pessoa.model.Pessoa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LABORATORIO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Laboratorio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LABORATORIO")
    @SequenceGenerator(name = "SEQ_LABORATORIO", sequenceName = "SEQ_LABORATORIO", allocationSize = 1)
    private Integer id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "laboratorio", fetch = FetchType.LAZY)
    private List<Pessoa> pessoas;

    public static Laboratorio of(LaboratorioRequest request) {
        return Laboratorio
            .builder()
            .nome(request.getNome())
            .build();
    }
}
