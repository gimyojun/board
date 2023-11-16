package com.ll.board.domain.article.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.board.domain.article.entity.Article;
import com.ll.board.domain.article.service.ArticleService;
import com.ll.board.global.RsData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    //생성자가 하나인 경우 생성자에 @Autowired 생략 가능
    //@Autowired
    private final ArticleService articleService;
    //@RequiredArgsConstructor로 대체할 수 있다.
//    public ArticleController(ArticleService articleService) {
//        this.articleService = articleService;
//    }

    @GetMapping("article/write")
    String showWrite(){
        return "article/write";
    }

    @PostMapping("/article/write")
    @ResponseBody
    RsData write(
            String title,
            String body
    ) {
        if (title == null || title.trim().length() == 0) {
            throw new IllegalArgumentException("제목을 입력해주세요.");
        }

        if (body == null || body.trim().length() == 0) {
            throw new IllegalArgumentException("내용을 입력해주세요.");
        }

        Article article = articleService.write(title, body);
        RsData<Article> rs = new RsData<>(
                "S-1",
                "%d 번 게시물이 작성되었습니다".formatted(article.getId()),
                article
        );

        return rs;
    }
    @PostMapping("article/write2")
    @SneakyThrows
    void write2(HttpServletRequest req,  HttpServletResponse resp) {
        String title = req.getParameter("title");
        String body = req.getParameter("body");

        Article article = articleService.write(title, body);
        RsData<Article> rs = new RsData<>(
                "S-1",
                "%d 게시물이 생성되었습니다.".formatted(article.getId()),
                article
        );

        ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(objectMapper.writeValueAsString(rs));
    }

    @GetMapping("/article/articleServicePointer")
    @ResponseBody
    String articleServicePointer() {
        return articleService.toString();
    }

    @GetMapping("/article/httpServletRequestPointer")
    @ResponseBody
    String httpServletRequestPointer(HttpServletRequest req) {
        return req.toString();
    }

    @GetMapping("/article/httpServletResponsePointer")
    @ResponseBody
    String httpServletResponsePointer(HttpServletResponse resp) {
        return resp.toString();
    }

    @GetMapping("article/lastArticle")
    @ResponseBody
    Article findLastArticle(){
        return articleService.findLastArticle();
    }

    @GetMapping("article/getArticles")
    @ResponseBody
    List<Article> findAll(){
        return articleService.findAll();
    }


}


