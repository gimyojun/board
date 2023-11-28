package com.ll.board.domain.member.member.controller;

import com.ll.board.domain.member.member.entity.Member;
import com.ll.board.domain.member.member.service.MemberService;
import com.ll.board.global.rq.Rq;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberService memberService;
    private final Rq rq;

    @Data
    public static class JoinForm {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }
    @GetMapping("/member/login")
    String showLogin() {
        return "member/member/login";
    }
    
    @GetMapping("/member/logout")
    String logout() {
        rq.removeSessionAttr("loginedMemberId");

        return rq.redirect("/article/list", "로그아웃 되었습니다.");
    }
    @GetMapping("member/join")
    String showJoin(){
        return "member/member/join";
    }

    @PostMapping("/member/join")
    String join(@Valid JoinForm joinForm) {
        Member member = memberService.join(joinForm.username, joinForm.password);

        return rq.redirect("/member/login","회원가입이 완료되었습니다");
    }


}


