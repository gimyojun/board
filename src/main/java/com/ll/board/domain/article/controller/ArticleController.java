package com.ll.board.domain.article.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.board.domain.article.entity.Article;
import com.ll.board.domain.article.service.ArticleService;
import com.ll.board.global.RsData;
import com.ll.board.global.rq.Rq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    //Rq는 리퀘스트 스코프라서 매번 변경되는데 context가 가능한이유는 프록시. 즉 대리자여서 그렇다
    //어플리케이션 시작할 때 딱한번 들어간다.
    //매번 달라진다는게 매번 새로운객체가 들어간다는 뜻이다
    //하나가 내가 요청을 할때마다 달라진다? 얘는 대리자 이다
    private final Rq rq;

    @Data
    public static class WriteForm {
        @NotBlank
        private String title;
        @NotBlank
        private String body;
    }

    @GetMapping("article/write")
    String showWrite(){
        return "article/write";
    }

    @PostMapping("/article/write")
    String write(@Valid WriteForm writeForm) {
        Article article = articleService.write(writeForm.title, writeForm.body);
        //이부분부터는 그냥 보여주기 식이다.
        String msg = "id %d, article created".formatted(article.getId());

        return "redirect:/article/list?msg=" + msg;
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

    @GetMapping("article/list")
    String showList(Model model){
        List<Article> articles = articleService.findAll();
        model.addAttribute("articles",articles);

        return "article/list";
    }
    @GetMapping("article/detail/{id}")
    String showDetail(Model model, @PathVariable long id){
        Article article = articleService.findById(id).get();
        model.addAttribute("article", article);
        return "article/detail";
    }

}


