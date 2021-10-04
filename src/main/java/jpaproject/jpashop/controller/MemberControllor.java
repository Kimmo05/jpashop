package jpaproject.jpashop.controller;

import jpaproject.jpashop.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MemberControllor {

        private final MemberServiceImpl memberService;

}
