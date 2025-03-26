package br.com.agro.laboratorio.modules.laboratorio.repository;

import br.com.agro.laboratorio.modules.laboratorio.model.Laboratorio;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.EntityManager;
import java.util.List;

import static br.com.agro.laboratorio.modules.laboratorio.model.QLaboratorio.laboratorio;
import static br.com.agro.laboratorio.modules.pessoa.model.QPessoa.pessoa;

@RequiredArgsConstructor
public class LaboratorioRepositoryImpl implements LaboratorioRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public List<Laboratorio> findAllAllByPredicateOrderByQtdPessoas(Predicate predicate) {
        return new JPAQueryFactory(entityManager)
            .select(laboratorio)
            .from(laboratorio)
            .leftJoin(laboratorio.pessoas, pessoa)
            .where(predicate)
            .groupBy(laboratorio)
            .orderBy(pessoa.count().desc())
            .fetch();
    }
}
