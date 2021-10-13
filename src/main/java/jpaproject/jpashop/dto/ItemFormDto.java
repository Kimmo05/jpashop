package jpaproject.jpashop.dto;

import jpaproject.jpashop.constant.ItemSellStatus;
import jpaproject.jpashop.domain.Item;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ItemFormDto {
    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemNm;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotBlank(message = "상품 상세는 필수 입력 값입니다.")
    private String itemDetail;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stockQuantity;

    private ItemSellStatus itemSellStatus;


    private List<Long> itemImgIds = new ArrayList<>();

//    private static ModelMapper modelMapper = new ModelMapper();
//
//    public Item createItem(){
//        return modelMapper.map(this, Item.class);
//    }
//
//    public static ItemFormDto of(Item item){
//        return modelMapper.map(item,ItemFormDto.class);
//    }
}
