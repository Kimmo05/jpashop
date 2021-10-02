package jpaproject.jpashop.repository;


import jpaproject.jpashop.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByItemNm(String itemNm); //상품 찾기

}
