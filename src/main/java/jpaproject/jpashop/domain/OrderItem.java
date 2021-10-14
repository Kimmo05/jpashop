package jpaproject.jpashop.domain;


import jpaproject.jpashop.constant.OrderStatus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_itemid")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item; //상품 아이디


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;  //주문아이디

    private int orderPrice; //주문가격

    private int count; //주문수량

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public void setItem(Item item) {
        this.item = item;
        item.getOrderItemList().add(this);
    }
    // 생성 메소드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice * count);
        orderItem.setCount(count);

        item.minusStockQuantity(count);

        return orderItem;
    }

    /* 비즈니스 로직 */

    public void itemCancel() {
        this.getItem().plusStockQuantity(count);
    }// 취소하면서 재고 복구

    public int getCalPrice() {
        return this.getOrderPrice() * this.getCount();
    }// 전체 가격조회
}
