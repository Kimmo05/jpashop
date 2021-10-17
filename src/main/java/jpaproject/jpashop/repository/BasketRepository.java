package jpaproject.jpashop.repository;


import jpaproject.jpashop.domain.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> findAllByMemberId(Long id); //장바구니 멤버 아이디
}
