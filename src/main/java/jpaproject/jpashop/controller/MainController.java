package jpaproject.jpashop.controller;

import jpaproject.jpashop.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final MemberServiceImpl memberServiceImpl;

    @GetMapping("/")
    public String getMainPage(Model model){

        return "main/index";
    }
}
