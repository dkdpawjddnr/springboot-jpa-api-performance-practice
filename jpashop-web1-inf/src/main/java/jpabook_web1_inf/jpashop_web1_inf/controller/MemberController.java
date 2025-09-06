package jpabook_web1_inf.jpashop_web1_inf.controller;

import jakarta.validation.Valid;
import jpabook_web1_inf.jpashop_web1_inf.domain.Address;
import jpabook_web1_inf.jpashop_web1_inf.domain.Member;
import jpabook_web1_inf.jpashop_web1_inf.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        //Controller 에서 view로 넘어갈 때 이 데이터를 싣어서 나간다.
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result){
        // @Valid MemberForm에 있는 @NotEmpty 같은거를 벨리데이션 해주는 기능
        // 이름이 없으면
        // BindingResult 만약에 오류가 있으면 오류가 담겨서 return이 실행이 된다.
        // @Valid를 하고 에러가 있으면 BindingResult에 폼에 끌고가서 쓸 수 있게 해준다.
        if(result.hasErrors()){
            return "members/createMemberForm";
        }
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        // 멤버들을 model에 담아서 화면에 넘겨준다.
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}


