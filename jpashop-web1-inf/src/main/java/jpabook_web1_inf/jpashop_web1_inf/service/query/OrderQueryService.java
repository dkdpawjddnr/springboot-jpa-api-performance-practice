package jpabook_web1_inf.jpashop_web1_inf.service.query;

import jpabook_web1_inf.jpashop_web1_inf.api.OrderApiController;
import jpabook_web1_inf.jpashop_web1_inf.domain.Order;
import jpabook_web1_inf.jpashop_web1_inf.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderQueryService {
    private final OrderRepository orderRepository;

    public List<OrderDto> ordersV3(){
        List<Order> orders = orderRepository.findAllWithItem();

        List<OrderDto> result = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());

        return result;
    }
}
