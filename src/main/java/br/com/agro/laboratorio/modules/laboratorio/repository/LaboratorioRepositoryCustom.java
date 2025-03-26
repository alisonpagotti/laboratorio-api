package br.com.agro.laboratorio.modules.laboratorio.repository;

import br.com.agro.laboratorio.modules.laboratorio.model.Laboratorio;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface LaboratorioRepositoryCustom {

    public List<Laboratorio> findAllAllByPredicateOrderByQtdPessoas(Predicate predicate);
}
