package boot.helloboot.repository;

import boot.helloboot.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    //select m from member m where m.name = ?
    //인터페이스 이름만으로 개발 끝내기!
    @Override
    Optional<Member> findByName(String name);
}
