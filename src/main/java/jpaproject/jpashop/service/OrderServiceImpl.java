package jpaproject.jpashop.service;

import jpaproject.jpashop.constant.OrderStatus;
import jpaproject.jpashop.domain.Order;
import jpaproject.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public List<Order> findAllOrders(){
        return orderRepository.findAll();
    }

    @Override
    public void cancelOrder(Long orderId) {

    }

    @Override
    public Long changeOrderStatus(Long orderItemId, OrderStatus orderStatus) {
        return null;
    }
}
