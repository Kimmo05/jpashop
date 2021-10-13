package jpaproject.jpashop.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberControllor {



        @GetMapping("hello")
        public String hello(Model model) {
                model.addAttribute("data", "hello!!!");
                return "hello";
        }
}
