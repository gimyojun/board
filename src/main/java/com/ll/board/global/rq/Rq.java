package com.ll.board.global.rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

//기본적으로 빈은 어플리케이션 스코프지만 이런경우 명시적으로 리퀘스트 스코프다.
@RequestScope
@Component
public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;

    public Rq(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
    }
}
