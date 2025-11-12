package jpabook_web1_inf.jpashop_web1_inf.service.query;

import jpabook_web1_inf.jpashop_web1_inf.domain.OrderItem;
import lombok.Getter;

@Getter
public class OrderItemDto {

    private String itemName; // 상품 명
    private int orderPrice; // 주문 가격
    private int count; // 주문 수량

    public OrderItemDto(OrderItem orderItem) {
        this.itemName = orderItem.getItem().getName();
        this.orderPrice = orderItem.getOrderPrice();
        this.count = orderItem.getCount();
    }
}
