package jpaproject.jpashop.controller;


import jpaproject.jpashop.domain.DeliveryAddress;
import jpaproject.jpashop.domain.Member;
import jpaproject.jpashop.dto.AddressDto;
import jpaproject.jpashop.dto.MemberFormDto;
import jpaproject.jpashop.service.DeliveryAddressServiceImpl;
import jpaproject.jpashop.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberControllor {

        private final MemberServiceImpl memberServiceImpl;


        @GetMapping(value = "/register")
        public String memberForm()
        {
                return "admin/other/register";
        } //회원등록 를 누르면 회원가입페이지로 감


        @PostMapping("main/register")
        public String doRegisterPage(MemberFormDto memberFormDto) {
                Long memberId = memberServiceImpl.joinUser(memberFormDto);


                return "redirect:/admin/login";
        }

        @ResponseBody
        @PostMapping("/register/doublecheck")
        public String idDoubleCheckPage(@RequestParam(value = "registerId") String registerId) {
                if (memberServiceImpl.doubleCheckId(registerId)) {
                        return "사용할 수 없는 아이디입니다.";
                } else {
                        return "사용할 수 있는 아이디입니다.";
                }
        } //중복 체크 부분 아직 사용안함

        @GetMapping("admin/login")
        public String adminlogin(HttpServletRequest request, @RequestParam(value = "error", required = false) String error, Model model) {
                String referer = request.getHeader("Referer");
                if (referer != null) {
                        request.getSession().setAttribute("prevPage", referer);
                } else {
                        referer = "http://localhost:8080/admin/admin_main";
                        request.getSession().setAttribute("prevPage", referer);
                }
                model.addAttribute("error", error);
                return "admin/other/login";
        }
        @GetMapping("/defaultUrl")
        public String loginRedirectPage(HttpServletRequest request) {
                String referer = request.getHeader("Referer");

                referer = "http://localhost:8080/admin/home";
                request.getSession().setAttribute("prevPage", referer);

                return "redirect:/admin/home";
        }

}
