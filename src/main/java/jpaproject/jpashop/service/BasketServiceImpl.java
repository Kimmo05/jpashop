package jpaproject.jpashop.service;

import jpaproject.jpashop.domain.Basket;
import jpaproject.jpashop.repository.BasketRepository;
import jpaproject.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BasketServiceImpl implements BasketService {
    private final BasketRepository basketRepository;
    private final ItemRepository itemRepository;

    @Override
    public List<Basket> findAllBasketByMemberId(Long id) {
        List<Basket> basketList = basketRepository.findAllByMemberId(id);
        return basketList;
    }

}
