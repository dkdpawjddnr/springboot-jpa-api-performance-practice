package jpabook_web1_inf.jpashop_web1_inf.repository;

import jakarta.persistence.EntityManager;
import jpabook_web1_inf.jpashop_web1_inf.domain.iteam.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if(item.getId() == null){
            // id값이 없다는 것은 신규로 등록을 하는 것
            em.persist(item);
        } else {
            // 이미 DB에 등록되어 있는 것을 가져온 것, UPDATE로 이해하기
            em.merge(item);
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
