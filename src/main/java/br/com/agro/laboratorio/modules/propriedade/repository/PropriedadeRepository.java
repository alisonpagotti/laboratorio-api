package br.com.agro.laboratorio.modules.propriedade.repository;

import br.com.agro.laboratorio.modules.propriedade.model.Propriedade;
import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropriedadeRepository extends JpaRepository<Propriedade, Integer>, QuerydslPredicateExecutor<Propriedade> {

    List<Propriedade> findAll(Predicate predicate);
}
