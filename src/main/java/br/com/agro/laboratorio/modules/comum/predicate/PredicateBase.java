package br.com.agro.laboratorio.modules.comum.predicate;

import com.querydsl.core.BooleanBuilder;

public class PredicateBase {

    protected BooleanBuilder builder;

    public PredicateBase() {
        this.builder = new BooleanBuilder();
    }

    public BooleanBuilder build() {
        return this.builder;
    }
}
