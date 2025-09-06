package jpabook_web1_inf.jpashop_web1_inf.repository;

import jpabook_web1_inf.jpashop_web1_inf.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

    private String memberName; // 회원 이름
    private OrderStatus orderStatus; // 주문 상태
}
