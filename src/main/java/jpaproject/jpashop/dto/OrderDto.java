package jpaproject.jpashop.dto;

import jpaproject.jpashop.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class OrderDto {


    private Long id;

    @NotNull(message = "상품 아이디는 필수 입력 값입니다.")
    private Long order_itemId;
    private String name;
    private String itemNm;
    private OrderStatus orderStatus;
    @Min(value = 1, message = "최소 주문 수량은 1개 입니다.")
    @Max(value = 999, message = "최대 주문 수량은 999개 입니다.")
    private int count;
}
