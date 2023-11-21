package com.ll.board.domain.member.member.controller;

import com.ll.board.domain.member.member.entity.Member;
import com.ll.board.domain.member.member.service.MemberService;
import com.ll.board.global.rq.Rq;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
    public static class LoginForm {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }
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
    @PostMapping("/member/login")
    String login(@Valid LoginForm loginForm, HttpServletRequest req, HttpServletResponse response) {
        //옵셔널로 반환된 객체에 get했을 때 id 없으면 에러
        Member member = memberService.findByUsername(loginForm.username).get();
        //비번 에러
        if (!member.getPassword().equals(loginForm.password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        //쿠키생성
        Cookie cookie = new Cookie("loginedMemberId",member.getId() + "");
        cookie.setPath("/");
        response.addCookie(cookie);

        HttpSession session = req.getSession();
        session.setAttribute("loginedMemberId", member.getId());;


        return rq.redirect("/article/list", "로그인이 완료되었습니다.");
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


