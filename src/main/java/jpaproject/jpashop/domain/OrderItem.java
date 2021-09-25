package jpaproject.jpashop.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


import lombok.Getter;

import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_itemid")
    private Long id;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name ="order_id")
    private Order order;

    private int orderPrice; //주문가격
    private int ordercount ; //주문수량

@Table(name="order_item")
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "order_id")
    private Order order;

    private int orderPrice;
    private  int count;

    private OrderStatus orderStatus;

}
