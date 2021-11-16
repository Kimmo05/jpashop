package kr.co.pjshop.controller;


import kr.co.pjshop.dto.MemberDto;
import kr.co.pjshop.dto.MemberFormDto;
import kr.co.pjshop.dto.MemberPageDto;
import kr.co.pjshop.entity.Member;
import kr.co.pjshop.entity.SearchMember;
import kr.co.pjshop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

    @PostMapping(value = "/new")
    public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "member/memberForm";
        }

        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String loginMember(){
        return "/member/memberLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "/member/memberLoginForm";
    }

    @GetMapping("/admin/memberList")
    public String pageList(Model model, @PageableDefault(size = 4) Pageable pageable, SearchMember searchMember) {
        MemberPageDto memberPageDto = new MemberPageDto();

        if (searchMember.getSearchKeyword() == null) {
            memberPageDto = memberService.findAllMemberByPaging(pageable);
        } else {
            memberPageDto = memberService.findAllMemberByConditionByPaging(searchMember, pageable);
        }

        int homeStartPage = memberPageDto.getHomeStartPage();
        int homeEndPage = memberPageDto.getHomeEndPage();
        Page<MemberDto> memberBoards = memberPageDto.getMemberBoards();

        model.addAttribute("startPage", homeStartPage);
        model.addAttribute("endPage", homeEndPage);
        model.addAttribute("memberList", memberBoards);
        model.addAttribute("searchCondition", searchMember.getSearchCondition());
        model.addAttribute("searchKeyword", searchMember.getSearchKeyword());

        return "member/memberList";
    }

    @GetMapping("/admin/memberEdit")
    public String memberEdit(){
        return "member/memberEdit";
    }
}