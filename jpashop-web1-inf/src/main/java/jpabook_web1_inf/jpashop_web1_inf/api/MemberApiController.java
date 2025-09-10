package jpabook_web1_inf.jpashop_web1_inf.api;

import jpabook_web1_inf.jpashop_web1_inf.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
}
