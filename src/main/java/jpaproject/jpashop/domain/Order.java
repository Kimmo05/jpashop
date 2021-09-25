package jpaproject.jpashop.domain;



import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Getter @Setter

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //멤버와 다대일
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate; //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

public class Order {

    @Id @GeneratedValue
    @Column(name= "order_id") //주문아이디  =order_id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //지연로딩 //다대1
    @JoinColumn(name = "member_id")//member_id를 조인함
    private Member member;

    @OneToOne
    @JoinColumn(name = "delivery_ID")
    private Delivery delivery;

}
