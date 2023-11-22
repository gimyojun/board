package com.ll.board.global.webMvc;

import com.ll.board.global.interceptor.NeedToAdminInterceptor;
import com.ll.board.global.interceptor.NeedToLoginInterceptor;
import com.ll.board.global.interceptor.NeedToLogoutInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final NeedToAdminInterceptor needToAdminInterceptor;
    private final NeedToLoginInterceptor needToLoginInterceptor;
    private final NeedToLogoutInterceptor needToLogoutInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(needToLogoutInterceptor)
                .addPathPatterns("/member/login")
                .addPathPatterns("/member/join")
                .addPathPatterns("/member/findUsername")
                .addPathPatterns("/member/findPassword");
        registry.addInterceptor(needToLoginInterceptor)
                .addPathPatterns("/adm/**")
                .addPathPatterns("/article/write")
                .addPathPatterns("/article/modify/**")
                .addPathPatterns("/article/delete/**");
        registry.addInterceptor(needToAdminInterceptor)
                .addPathPatterns("/adm/**");
    }


}
