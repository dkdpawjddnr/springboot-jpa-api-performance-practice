package jpabook_web1_inf.jpashop_web1_inf.repository;

import jpabook_web1_inf.jpashop_web1_inf.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //select m from Member m where m.name = ?
    //메서드 명을 읽고 알아서 jpa가 처리해줌
    public List<Member> findByName(String name);
}
