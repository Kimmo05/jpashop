package jpaproject.jpashop.service;

import jpaproject.jpashop.constant.OrderStatus;
import jpaproject.jpashop.domain.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAllOrders();
    // 모든 주문내역 조회


    void cancelOrder(Long orderId);
//    주문 취소하는 메소드

    Long changeOrderStatus(Long orderItemId, OrderStatus orderStatus);
//    주문 상태 변경하는 메소드


}
