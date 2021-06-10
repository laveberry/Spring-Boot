package boot.helloboot;

import boot.helloboot.repository.JdbcMemberRepository;
import boot.helloboot.repository.MemberRepository;
import boot.helloboot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//스프링 빈 직접등록을 위한 파일
@Configuration
public class SpringConfig {

    private DataSource dataSource;
    //데이터소스 주입받기
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
//      구현체. 인터페이스는 new 안됨
        //메모리버전
//        return new MemoryMemberRepository();
//        여기만 조립(어쎔블리) 하여 코드 수정 가능
        //JDBC버전
        return new JdbcMemberRepository(dataSource);
    }

}
