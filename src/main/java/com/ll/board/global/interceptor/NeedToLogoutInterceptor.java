package com.ll.board.global.interceptor;

import com.ll.board.global.rq.Rq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class NeedToLogoutInterceptor implements HandlerInterceptor {
    private final Rq rq;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(rq.isLogined()){
           throw new RuntimeException("로그 아웃 후 이용해주세요");
        }



        return true;
    }

}