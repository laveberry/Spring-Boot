package boot.helloboot.service;

import boot.helloboot.repository.MemberRepository;
import boot.helloboot.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//스프링 빈 직접등록을 위한 파일
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
//      구현체. 인터페이스는 new 안됨
        return new MemoryMemberRepository();
    }


}
