package boot.helloboot.repository;

import boot.helloboot.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//3. 인터페이스 구현 클래스
//@Repository
public class MemoryMemberRepository implements MemberRepository{

    //static으로 선언
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
       return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    //클리어 메소드 생성
    public void clearStore(){
        store.clear();
    }
}
