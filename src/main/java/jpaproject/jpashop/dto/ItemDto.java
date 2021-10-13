package jpaproject.jpashop.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ItemDto {
    private Long id;

    private String itemNm;

    private Integer price;

    private String itemDetail;

    private String saleStatus;
    private String CategoryFirst;
    private String CategorySecond;
    private String imgUrl;
    private String color;

}
