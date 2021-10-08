package jpaproject.jpashop.service;

import jpaproject.jpashop.domain.Item;

public interface ItemService {
    Long saveItem(Item item);
//    상품 저장하는 메소드

    Long changeItemStatusSoldOut(String idx, String color);
//    상품 상태 품절로 변경하는 메소드

    Long changeItemStatusOnSale(String idx, String color);
//    상품 상태 판매중으로 변경하는 메소드

    Long deleteItemById(String idx, String color);
//    Pk 이용해서 아이템 삭제하는 메소드


}
