package boot.helloboot.service;

import boot.helloboot.domain.Member;
import boot.helloboot.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


//스프링용 테스트 선언
/**
 * @SpringBootTest : 컨테이너와 테스트 동시 실행
 * @Transactional을 통해서 테스트 끝나면 롤백 해줌. 데이터 반영안되고 지워짐
 */
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    //테스트는 편하게 Autowired 쓰면됨
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    @Commit
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }
}