package br.com.agro.laboratorio.modules.laboratorio.repository;

import br.com.agro.laboratorio.modules.laboratorio.model.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, Integer>,
                                               QuerydslPredicateExecutor<Laboratorio>,
                                               LaboratorioRepositoryCustom {
}
