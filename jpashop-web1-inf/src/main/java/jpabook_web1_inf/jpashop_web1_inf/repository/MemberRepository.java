package jpabook_web1_inf.jpashop_web1_inf.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook_web1_inf.jpashop_web1_inf.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

// 자동으로 스프링빈에 등록 및 관리가 됨.
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    // Repository는 JPA를 사용하기 때문에
    // 자동으로 주입하도록 설정
    // @PersistenceContext
    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // 파라미터 바인딩해서 특정 회원 찾기
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = : name", Member.class)
                .setParameter("name" , name)
                .getResultList();
    }
}
