package jpaproject.jpashop.repository;


import jpaproject.jpashop.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>,ItemRepositoryCustom {

    List<Item> findAllByItemIdx(Long itemIdx);
}
