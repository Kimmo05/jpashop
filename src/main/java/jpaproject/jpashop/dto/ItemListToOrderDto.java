package jpaproject.jpashop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemListToOrderDto {
    //아이템리스트
    private List<Long> itemList;
    private List<Integer> itemCountList;
}
