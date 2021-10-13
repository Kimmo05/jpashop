package jpaproject.jpashop.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    public OrderRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }
}
