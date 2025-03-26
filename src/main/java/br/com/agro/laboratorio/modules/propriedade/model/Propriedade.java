package br.com.agro.laboratorio.modules.propriedade.model;

import br.com.agro.laboratorio.modules.pessoa.model.Pessoa;
import br.com.agro.laboratorio.modules.propriedade.dto.PropriedadeRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "PROPRIEDADE")
public class Propriedade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROPRIEDADE")
    @SequenceGenerator(name = "SEQ_PROPRIEDADE", sequenceName = "SEQ_PROPRIEDADE", allocationSize = 1)
    private Integer id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @JsonIgnore
    @ManyToMany(mappedBy = "propriedades", fetch = FetchType.LAZY)
    private List<Pessoa> pessoas;

    public static Propriedade of(PropriedadeRequest request) {
        return Propriedade
            .builder()
            .nome(request.getNome())
            .build();
    }
}
