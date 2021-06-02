package boot.helloboot.repository;

import boot.helloboot.domain.Member;

import java.util.List;
import java.util.Optional;

//2. 인터페이스 생성
public interface MemberRepository {

    Member save(Member member);

    //Oprional : null 반환 막아주는 기능
    Optional<Member> findById(Long id);

    Optional<Member> findByName(String name);

    List<Member> findAll(); //모든회원 리스트 반환



}
