package com.ljy.jpastudy.core.querydsl;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

abstract public class QuerydslSupport<T> extends QuerydslRepositorySupport {
    protected QuerydslSupport(Class<?> domainClass) {
        super(domainClass);
    }

    @Transactional
    public void save(T entity) {
        EntityManager entityManager = getEntityManager();
        if(entityManager.contains(entity)){
            entityManager.merge(entity);
            return;
        }
        entityManager.persist(entity);
    }
}
