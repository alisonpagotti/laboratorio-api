package br.com.agro.laboratorio.modules.pessoa.repository;

import br.com.agro.laboratorio.modules.pessoa.model.Pessoa;
import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>, QuerydslPredicateExecutor<Pessoa> {

    List<Pessoa> findAll(Predicate predicate);
}
