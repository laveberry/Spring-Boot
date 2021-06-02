package boot.helloboot.repository;

import boot.helloboot.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {

//    MemberRepository repository = new MemoryMemberRepository();
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트 끝날때마다 저장소 clean하여 순서 상관없이 작동되게함
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }


    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        //Optional에서는 get으로 값 꺼낼수 있음. get()은 좋은 코드는 아님
        Member result =  repository.findById(member.getId()).get();

        System.out.println("result = "+ (result == member)); //확인 test

        Assertions.assertEquals(member, result); //값 비교 테스트
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result); //assertThat 바로 사용하는법 확인하기
    }


    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //shift + F6 이름 동시변경
        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        Member result = repository.findByName("spring1").get(); //spring2 하면 다른 객체라고 에러남

        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(member1);
    }


    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
