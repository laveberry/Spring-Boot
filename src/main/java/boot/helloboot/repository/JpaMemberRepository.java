package boot.helloboot.repository;

import boot.helloboot.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        //jpa가 insert쿼리 만들어서 집어넣고 setId 까지 해줌
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
//        List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
//        return result;
        //jpql(쿼리언어) : 테이블 대상이 아닌 객체(entitiy)대상으로 쿼리를 날림
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
