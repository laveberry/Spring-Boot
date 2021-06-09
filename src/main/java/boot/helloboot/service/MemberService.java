package boot.helloboot.service;

import boot.helloboot.domain.Member;
import boot.helloboot.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 서비스 클래스 : 비지니스에 가까운 설계
 * 리포지토리 : 단순히 기계적으로 개발스럽게
 * */
// 서비스를 통해 멤버가입, 리포지토리 저장 및 꺼내올 수 있음
//@Service
public class MemberService { //alt + enter : 테스트코드 생성

//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //MemberRepository를 외부에서 넣어줌 : DI(Dependency Injection)
    private final MemberRepository memberRepository;

    //constructor 생성
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원가입
     * */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원X
//        Optional<Member> result =  memberRepository.findByName(member.getName());
//        //Optional로 감싸서 ifPresent 사용가능
//        result.ifPresent(m ->{
//            throw new IllegalStateException("이미 존재하는 회원입니다");
//        });

        validateDuplicateMember(member); //Refactor : 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).
                ifPresent(m ->{
                   throw new IllegalStateException("이미 존재하는 회원입니다");
               });
    }

    /**
     * 전체회원조회
     * */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
