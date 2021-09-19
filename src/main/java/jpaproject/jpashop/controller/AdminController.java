package jpaproject.jpashop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {


    @GetMapping("/admin/main")
    public String getMemberMainPage(Model model){
        model.addAttribute("memberForm",new MemberForm());
        return "admin/admin_main";
    }
}
