package jpabook_web1_inf.jpashop_web1_inf.repository.order.simplequery;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    // 재사용은 어렵지만, 복잡한 조인 쿼리를 가지고 DTO를 쿼리에서 직접 뽑아야 할때 따로 뽑아내서 사용한다.
    // Repository는 Entity만 조회하는 게 좋음. 그래서 따로 뽑음.
    public List<OrderSimpleQueryDto> findOrderDtos() {
        return em.createQuery(
                        "select new jpabook_web1_inf.jpashop_web1_inf.repository.order.simplequery.OrderSimpleQueryDto(" +
                                " o.id, m.name, o.orderDate, o.status, d.address)" +
                                " from Order o" +
                                " join o.member m" +
                                " join o.delivery d"
                        , OrderSimpleQueryDto.class)
                .getResultList();
    }
}
