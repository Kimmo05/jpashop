package jpaproject.jpashop.service;

import jpaproject.jpashop.domain.Item;
import jpaproject.jpashop.repository.ItemRepository;
import jpaproject.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;



}
