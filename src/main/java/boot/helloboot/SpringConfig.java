package boot.helloboot;

import boot.helloboot.aop.TimeTraceAop;
import boot.helloboot.repository.JdbcMemberRepository;
import boot.helloboot.repository.JdbcTemplateMemberRepository;
import boot.helloboot.repository.JpaMemberRepository;
import boot.helloboot.repository.MemberRepository;
import boot.helloboot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

//스프링 빈 직접등록을 위한 파일
@Configuration
public class SpringConfig {

    //하나일때만 Autowired 생략가능
//    private DataSource dataSource;

    //jpa
//    @Autowired
//    private EntityManager em;


    //데이터소스 주입받기
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository(){
//      구현체. 인터페이스는 new 안됨
        //메모리버전
//        return new MemoryMemberRepository();
//        여기만 조립(어쎔블리) 하여 코드 수정 가능
        //JDBC버전
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }

    //AOP 스프링빈 등록
//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }

}
