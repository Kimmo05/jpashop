package jpaproject.jpashop.controller;


import jpaproject.jpashop.domain.SearchItem;
import jpaproject.jpashop.domain.SearchMember;
import jpaproject.jpashop.dto.ItemDto;
import jpaproject.jpashop.dto.ItemPageDto;
import jpaproject.jpashop.dto.MemberDto;
import jpaproject.jpashop.dto.MemberPageDto;
import jpaproject.jpashop.service.ItemServiceImpl;
import jpaproject.jpashop.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AdminController {
    private final MemberServiceImpl memberServiceImpl;
    private final ItemServiceImpl itemServiceImpl;
    @GetMapping("admin/home")
    public String admin(Model model) {
        return "/admin/admin_main";
    }

    /////////////////////////////////////////
    //회원관련 컨트롤러
    @GetMapping("/admin/userList")
    public String pageList(Model model, @PageableDefault(size = 4) Pageable pageable, SearchMember searchMember) {
        MemberPageDto memberPageDto = new MemberPageDto();

        if (searchMember.getSearchKeyword() == null) {
            memberPageDto = memberServiceImpl.findAllMemberByPaging(pageable);
        } else {
            memberPageDto = memberServiceImpl.findAllMemberByConditionByPaging(searchMember, pageable);
        }

        int homeStartPage = memberPageDto.getHomeStartPage();
        int homeEndPage = memberPageDto.getHomeEndPage();
        Page<MemberDto> memberBoards = memberPageDto.getMemberBoards();

        model.addAttribute("startPage", homeStartPage);
        model.addAttribute("endPage", homeEndPage);
        model.addAttribute("memberList", memberBoards);
        model.addAttribute("searchCondition", searchMember.getSearchCondition());
        model.addAttribute("searchKeyword", searchMember.getSearchKeyword());

        return "admin/other/userList";
    }
    @GetMapping("/admin/userList/user/{id}")
    public String pageUser(@PathVariable Long id, Model model) {
        model.addAttribute("Member", memberServiceImpl.findMemberById(id));

        return "admin/admin_user";
    }
    ////////////////////////////////////////
    ////////////////////////////////////////
    //상품 관련 컨트롤러
    @GetMapping("/admin/itemList")
    public String itemListPage(Model model, @PageableDefault(size = 5) Pageable pageable, SearchItem searchItem) {
        ItemPageDto itemPageDto = new ItemPageDto();
        if (searchItem.getItem_name() == null) {
            itemPageDto = itemServiceImpl.findAllItemByPaging(pageable);
        } else {
            itemPageDto = itemServiceImpl.findAllItemByConditionByPaging(searchItem, pageable);
        }
        Page<ItemDto> itemBoards = itemPageDto.getItemBoards();
        int homeStartPage = itemPageDto.getHomeStartPage();
        int homeEndPage = itemPageDto.getHomeEndPage();

        model.addAttribute("productList", itemBoards);
        model.addAttribute("startPage", homeStartPage);
        model.addAttribute("endPage", homeEndPage);

        model.addAttribute("saleStatus", searchItem.getSalestatus());
        model.addAttribute("firstCategory", searchItem.getCmode());
        model.addAttribute("itemName", searchItem.getItem_name());

        return "admin/admin_Goodslist";
    }
    ////////////////////////////////////////
    ////////////////////////////////////////
}