package jpaproject.jpashop.controller;

import jpaproject.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MemberControllor {

        private final MemberService memberService;

}
