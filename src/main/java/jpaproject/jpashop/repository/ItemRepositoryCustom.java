package jpaproject.jpashop.repository;

import jpaproject.jpashop.domain.SearchItem;
import jpaproject.jpashop.dto.ItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {
    //모든 상품 찾음
    Page<ItemDto> searchAllItem(Pageable pageable);

    Page<ItemDto> searchAllItemByCondition(SearchItem searchItem, Pageable pageable);
}
