package jpaproject.jpashop.service;

import jpaproject.jpashop.domain.Basket;

import java.util.List;

public interface BasketService {
    List<Basket> findAllBasketByMemberId(Long id);


}
