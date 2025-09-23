package jpabook_web1_inf.jpashop_web1_inf;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jpabook_web1_inf.jpashop_web1_inf.domain.Address;
import jpabook_web1_inf.jpashop_web1_inf.domain.Member;
import jpabook_web1_inf.jpashop_web1_inf.domain.Order;
import jpabook_web1_inf.jpashop_web1_inf.domain.OrderItem;
import jpabook_web1_inf.jpashop_web1_inf.domain.iteam.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        public void dbInit1(){
            Member member = new Member();
            member.setName("userA");
            member.setAddress(new Address("서울", "1", "1111"));
            em.persist(member);

            Book book1 = new Book();
            book1.setName("JPA1 BOOK");
            book1.setPrice(10000);
            book1.setStockQuantity(100);
            em.persist(book1);


            Book book2 = new Book();
            book2.setName("JPA2 BOOK");
            book2.setPrice(20000);
            book2.setStockQuantity(100);
            em.persist(book2);

        }
    }
}
