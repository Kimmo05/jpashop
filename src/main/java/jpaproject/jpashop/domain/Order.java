package jpaproject.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Getter @Setter
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
