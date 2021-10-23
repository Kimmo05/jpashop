package jpaproject.jpashop.service;

import jpaproject.jpashop.domain.Item;
import jpaproject.jpashop.domain.SearchItem;
import jpaproject.jpashop.dto.ItemDto;
import jpaproject.jpashop.dto.ItemPageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {
    Page<ItemDto> findAllItem(Pageable pageable);
    Page<ItemDto> findAllItemByCondition(SearchItem searchItem, Pageable pageable);
    ItemPageDto findAllItemByPaging(Pageable pageable);

    ItemPageDto findAllItemByConditionByPaging(SearchItem searchItem, Pageable pageable);

}
