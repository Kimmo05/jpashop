package jpaproject.jpashop.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class AdminController {


    @GetMapping("admin/home")
    public String admin(Model model) {
        return "/admin/admin_main";
    }

    @GetMapping("admin/login")
    public String adminlogin(Model model) {
        return "admin/other/login";

    }
}