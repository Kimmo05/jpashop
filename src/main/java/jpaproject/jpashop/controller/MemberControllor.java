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

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberControllor {

        private final MemberServiceImpl memberServiceImpl;


        @GetMapping(value = "/register")
        public String memberForm(Model model)
        {
                return "admin/other/register";
        } //회원등록 를 누르면 회원가입페이지로 감

        @PostMapping("main/register")
        public String doRegisterPage(@ModelAttribute @Valid MemberFormDto memberFormDto) {

              memberServiceImpl.signUp(memberFormDto);
              //signup 메소드

                return "redirect:/main/login"; //회원가입하면 로그인화면으로
        }


        @GetMapping("/main/login")
        public String getSignInPage() {
                return "admin/other/login";
        }

        // signIn 처리는 Spring security
        @GetMapping("/main/signInError")
        public String getSignInPageWithError(Model model) {
                model.addAttribute("loginError", "true");
                return "admin/other/signIn";
        }
        //로그인 컨트롤러 부분

        @ResponseBody
        @PostMapping("/main/register/doublecheck")
        public String idDoubleCheckPage(@RequestParam(value = "registerId") String registerId) {
                if (memberServiceImpl.doubleCheckId(registerId)) {
                        return "사용할 수 없는 아이디입니다.";
                } else {
                        return "사용할 수 있는 아이디입니다.";
                }
        } //중복 체크 부분 아직 사용안함
}
