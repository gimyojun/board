package com.ll.board.global.rq;

import com.ll.board.domain.member.member.entity.Member;
import com.ll.board.domain.member.member.service.MemberService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

//기본적으로 빈은 어플리케이션 스코프지만 이런경우 명시적으로 리퀘스트 스코프다.
@RequestScope
@Component
@Getter
@RequiredArgsConstructor
public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final MemberService memberService;
    private Member member;
    private User user;

    @PostConstruct
    public void init(){
        //스프링 스큐리티
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal() instanceof User){
            this.user = (User) authentication.getPrincipal();
        }
    }


    public String redirect(String path, String msg) {
        msg = URLEncoder.encode(msg, StandardCharsets.UTF_8);

        return "redirect:" + path + "?msg=" + msg;
    }

    private String getMemberUserName() {
        return user.getUsername();
    }

    public boolean isLogined(){
        return user != null;
    }
    public Member getMember() {
        if (!isLogined()) {
            return null;
        }
        if(member==null){
            member = memberService.findByUsername(getMemberUserName()).get();
        }
        return member;
    }

    public void setSesstionAttr(String name, Object value) {
        req.getSession().setAttribute(name, value);

    }
    public <T> T getSesstionAttr(String name) {
        return (T) req.getSession().getAttribute(name);
    }
    public void removeSessionAttr(String name) {
        req.getSession().removeAttribute(name);
    }

    public boolean isAdmin() {
        Member tmp = getMember();

        if(tmp == null)
            return false;

        return user.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }
}
