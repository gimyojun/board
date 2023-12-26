package com.ll.board.global.initData;

import com.ll.board.domain.article.article.service.ArticleService;
import com.ll.board.domain.member.member.entity.Member;
import com.ll.board.domain.member.member.service.MemberService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotProd {
    //스프링 실행될때 딱 한번만 실행됨
    @Bean
    public ApplicationRunner initNotProd(
            MemberService memberService,
            ArticleService articleService
    ) {
        return args -> {
            Member memberAdmin = memberService.join("admin", "123").getData();
            Member memberUser1 = memberService.join("user1", "123").getData();
            Member memberUser2 = memberService.join("user2", "123").getData();

            articleService.write(memberAdmin, "제목 1","내용 1");
            articleService.write(memberUser1, "제목 2","내용 2");
            articleService.write(memberUser1, "제목 2-1","내용 2-1");
            articleService.write(memberUser2, "제목 3","내용 3");
        };

    }
}
