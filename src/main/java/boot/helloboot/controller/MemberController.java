package boot.helloboot.controller;

import boot.helloboot.domain.Member;
import boot.helloboot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    //이렇게 쓰기보다는 스프링 컨테이너에 등록해서 쓰자 !
//    private final MemberService memberService = new MemberService();
//    방법1) 필드주입
//    @Autowired
    private final MemberService memberService;

//    방법2) 생성자 주입(젤 죠음), ApplicationContext에 올라가 있어야해서 Bean간 상호참조 막을 수 있음.
    /**
    * (required = false)은 의존성을 'Optional'로 설정
    * 주입받을 의존객체가 필수적이지 않을경우 false로 설정해 의존객체 주입받지 못하더라도 빈을 생성함
    * */
    @Autowired(required = false)
    public MemberController(MemberService memberService) {
        //memberService injection
        this.memberService = memberService;
        System.out.println("memberService = " + memberService.getClass());
    }

//    방법3) setter주입 : public으로 변경가능성 있어 별루
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

//    조회용
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

//    폼데이터 전송
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
