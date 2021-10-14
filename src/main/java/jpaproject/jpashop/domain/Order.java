package jpaproject.jpashop.domain;




import jpaproject.jpashop.constant.DeliveryStatus;

import jpaproject.jpashop.constant.OrderStatus;
import jpaproject.jpashop.exception.DeliveryException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")
@Getter @Setter

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseTime{

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();


    private LocalDate orderDate; //주문일


    private String payment; // 지불금액
    private int totalPrice;  //총가격

    public void setMember(Member member) {
        this.member = member;
        member.getOrderList().add(this);
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItemList.add(orderItem);
        orderItem.setOrder(this);
    }


    /* 비즈니스 로직 */

    public void orderCancel() {
        if (this.delivery.getStatus() == DeliveryStatus.COMPLETE) { //배송상태가 완료일경우
            throw new DeliveryException("이미 배송완료된 상품입니다.");
        } else {
//            this.setOrderStatus(OrderStatus.CANCEL);
            this.delivery.setStatus(DeliveryStatus.CANCEL); //아닐경우 CANCEL로 변경

            for (OrderItem orderItem : orderItemList) {
                orderItem.itemCancel();
            }
        }
    }// 주문 취소

    public int getCalTotalPrice() { //총가격
        int totalPrice = 0;
        for (OrderItem orderItem : orderItemList) {
            totalPrice += orderItem.getCalPrice();
        }
        return totalPrice;
    }// 전체 가격 조회
}

