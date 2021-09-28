package jpaproject.jpashop.repository;


import jpaproject.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

        private final EntityManager em;

        public void save(Member member) {em.persist((member));}

        public Member findOne(Long id) {
                return  em.find(Member.class, id);}

        public List<Member> findAll() {
        return em.createQuery("select m from Member m",Member.class).getResultList();
        }  //전체를 찾음
        public List<Member> findByName(String name){
                return em.createQuery("SELECT  m from Member  m where m.name = :name", Member.class)
                        .setParameter("name", name)
                        .getResultList();
                                //이름으로 찾음
        }
}
