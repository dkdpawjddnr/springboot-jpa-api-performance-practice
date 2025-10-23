package jpabook_web1_inf.jpashop_web1_inf.api;

import jpabook_web1_inf.jpashop_web1_inf.domain.Address;
import jpabook_web1_inf.jpashop_web1_inf.domain.Order;
import jpabook_web1_inf.jpashop_web1_inf.domain.OrderStatus;
import jpabook_web1_inf.jpashop_web1_inf.repository.OrderRepository;
import jpabook_web1_inf.jpashop_web1_inf.repository.OrderSearch;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/*
 * xToOne(ManyToOne, OneToOne)
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByCriteria(new OrderSearch());
        for(Order order : all){
            order.getMember().getName(); // Lazy 강제 초기화
            order.getDelivery().getAddress(); // Lazy 강제 초기화
        }
        return all;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> orderV2(){
        // ORDER -> SQL 1번 -> 결과 : 주문수 2개
        // 1번 쿼리 결과로 추가로 2번 쿼리가 나가게됨 회원 N + 배송 N
        // -> N+1 문제
        List<Order> orders = orderRepository.findAllByCriteria(new OrderSearch());

        // 주문수가 2개라 2번 루프를 돌게 됨.
        // 첫 번째 루프가 돌 때, LAZY가 걸려있는 member, delivery
        // 두 번째 루프가 돌 때 또 LAZY 걸려있는 member, delivery 초기화 되야됨.
        // 총 쿼리가 5번 나가게 됨.
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return result;
    }

    // v2랑 결과는 똑같지만, 쿼리는 다르다.
    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> orderVs3(){
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return result;
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order){
            orderId = order.getId();
            name = order.getMember().getName(); // LAZY 초기화
            orderDate = order.getOrderDate();
            orderStatus = getOrderStatus();
            address = order.getDelivery().getAddress(); // LAZY 초기화
        }
    }
}
