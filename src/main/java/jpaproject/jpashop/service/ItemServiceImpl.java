package jpaproject.jpashop.service;

import jpaproject.jpashop.domain.Item;
import jpaproject.jpashop.domain.SearchItem;
import jpaproject.jpashop.dto.ItemDto;
import jpaproject.jpashop.dto.ItemPageDto;
import jpaproject.jpashop.repository.ItemRepository;
import jpaproject.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public Long saveItem(Item item) {
        itemRepository.save(item);

        return item.getId();
    }// 상품 저장
    //findAllItem
    @Override
    public Page<ItemDto> findAllItem(Pageable pageable) {
        return itemRepository.searchAllItem(pageable);
    }

    @Override
    public ItemPageDto findAllItemByPaging(Pageable pageable) {
        ItemPageDto itemPageDto = new ItemPageDto();
        Page<ItemDto> itemBoards = itemRepository.searchAllItem(pageable);

        int homeStartPage = Math.max(1, itemBoards.getPageable().getPageNumber() - 1);
        int homeEndPage = Math.min(itemBoards.getTotalPages(), itemBoards.getPageable().getPageNumber() + 3);

        itemPageDto.setItemBoards(itemBoards);
        itemPageDto.setHomeStartPage(homeStartPage);
        itemPageDto.setHomeEndPage(homeEndPage);

        return itemPageDto;
    }

    @Override
    public ItemPageDto findAllItemByConditionByPaging(SearchItem searchItem, Pageable pageable) {
        ItemPageDto itemPageDto = new ItemPageDto();
        Page<ItemDto> itemBoards = itemRepository.searchAllItemByCondition(searchItem, pageable);

        int homeStartPage = Math.max(1, itemBoards.getPageable().getPageNumber() - 1);
        int homeEndPage = Math.min(itemBoards.getTotalPages(), itemBoards.getPageable().getPageNumber() + 3);

        itemPageDto.setItemBoards(itemBoards);
        itemPageDto.setHomeStartPage(homeStartPage);
        itemPageDto.setHomeEndPage(homeEndPage);

        return itemPageDto;
    }

    @Override
    public Page<ItemDto> findAllItemByCondition(SearchItem searchItem, Pageable pageable) {
        return itemRepository.searchAllItemByCondition(searchItem, pageable);
    }

}
