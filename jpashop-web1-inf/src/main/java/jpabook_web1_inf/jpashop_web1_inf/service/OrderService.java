package jpabook_web1_inf.jpashop_web1_inf.service;

import jpabook_web1_inf.jpashop_web1_inf.domain.Delivery;
import jpabook_web1_inf.jpashop_web1_inf.domain.Member;
import jpabook_web1_inf.jpashop_web1_inf.domain.Order;
import jpabook_web1_inf.jpashop_web1_inf.domain.OrderItem;
import jpabook_web1_inf.jpashop_web1_inf.domain.iteam.Item;
import jpabook_web1_inf.jpashop_web1_inf.repository.ItemRepository;
import jpabook_web1_inf.jpashop_web1_inf.repository.MemberRepository;
import jpabook_web1_inf.jpashop_web1_inf.repository.OrderRepository;
import jpabook_web1_inf.jpashop_web1_inf.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        
        // 주문상품 생성
        OrderItem orderItem = OrderItem.creatrOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);
        
        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId){
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        order.cancel();
    }

    // 검색
    public List<Order> finaOrders(OrderSearch orderSearch){
        return orderRepository.findAllByCriteria(orderSearch);
    }

}
