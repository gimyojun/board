package com.ll.board.global.rq;

import com.ll.board.domain.member.member.entity.Member;
import com.ll.board.domain.member.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

//기본적으로 빈은 어플리케이션 스코프지만 이런경우 명시적으로 리퀘스트 스코프다.
@RequestScope
@Component
@Getter
public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private MemberService memberService;
    private Member member;

    public Rq(HttpServletRequest req, HttpServletResponse resp, MemberService memberService) {
        this.req = req;
        this.resp = resp;
        this.memberService=memberService;
    }


    public String redirect(String path, String msg) {
        msg = URLEncoder.encode(msg, StandardCharsets.UTF_8);

        return "redirect:" + path + "?msg=" + msg;
    }

    private Long getMemberId() {
        return Optional
                //req는 현재 요청에 대한 프록시
                .ofNullable(req.getSession().getAttribute("loginedMemberId"))
                .map(id -> (long) id)
                .orElse(0L);
    }

    public boolean isLogined(){
        return getMemberId() > 0;
    }
    public Member getMember() {
        if (!isLogined()) {
            return null;
        }
        if(member==null){
            member = memberService.findById(getMemberId()).get();
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

        return getMember().isAdmin();
    }
}
