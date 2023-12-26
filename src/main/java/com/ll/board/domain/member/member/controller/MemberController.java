package com.ll.board.domain.member.member.controller;

import com.ll.board.domain.member.member.entity.Member;
import com.ll.board.domain.member.member.service.MemberService;
import com.ll.board.global.rq.Rq;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("isAnonymous()")
    @GetMapping("/member/login")
    String showLogin() {
        return "member/member/login";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("member/join")
    String showJoin(){
        return "member/member/join";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/member/join")
    String join(@Valid JoinForm joinForm) {
        Member member = memberService.join(joinForm.username, joinForm.password);

        //null인 경우는 이미 해당 username으로 회원이 있는경우다.
        if(member == null){
            return rq.historyBack("이미 존재하는 회원입니다.");
        }
        return rq.redirect("/member/login","회원가입이 완료되었습니다");
    }


}


