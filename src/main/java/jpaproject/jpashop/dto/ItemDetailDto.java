package jpaproject.jpashop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemDetailDto {
    //아이템 상세정보
    private Long itemIdx;
    private String imgMainUrl;
    private String itemName;
    private int price;
    private double mileage;
    private String itemInfo;
    private List<String> colorList;
    private String fabric;
    private String model;
    private List<Long> itemId;
    private List<String> imgUrlList;
}
