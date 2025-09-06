package jpabook_web1_inf.jpashop_web1_inf.service;

import jakarta.persistence.EntityManager;
import jpabook_web1_inf.jpashop_web1_inf.domain.iteam.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemUpdateTest {

    // 변경 감지 예시
    @Autowired
    EntityManager em;

    @Test
    public void updateTest() throws Exception{
        // 트랜잭션 안에서 데이터가 변경되면 update 쿼리가 나가서
        // 자동으로 DB에 반영을 함 = dirty checking(변경 감지)
        Book book = em.find(Book.class, 1L);

        // TX
        book.setName("ads");

        // TX commit
        // 변경 감지 == dirty checking
    }
}
