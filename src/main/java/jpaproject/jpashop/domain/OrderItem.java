package jpaproject.jpashop.domain;


import jpaproject.jpashop.constant.OrderStatus;
import jpaproject.jpashop.exception.OutOfStockException;
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
    @JoinColumn(name = "item_ID")
    private Item item;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문가격

    private int count; //주문수량




}
