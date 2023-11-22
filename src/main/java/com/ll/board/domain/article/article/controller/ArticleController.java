package com.ll.board.domain.article.article.controller;

import com.ll.board.domain.article.article.entity.Article;
import com.ll.board.domain.article.article.service.ArticleService;
import com.ll.board.domain.member.member.service.MemberService;
import com.ll.board.global.rq.Rq;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    private final MemberService memberService;

    @Data
    public static class WriteForm {
        @NotBlank
        private String title;
        @NotBlank
        private String body;
    }
    @Data
    public static class ModifyForm {
        @NotBlank
        private String title;
        @NotBlank
        private String body;
    }

    @GetMapping("article/write")
    String showWrite(){
        if(!rq.isLogined()) throw new RuntimeException("로그인 후 이용해 주세요.");
        return "article/article/write";
    }

    @PostMapping("/article/write")
    String write(@Valid WriteForm writeForm) {
        if(!rq.isLogined()) throw new RuntimeException("로그인 후 이용해 주세요.");
        Article article = articleService.write(rq.getMember(), writeForm.title, writeForm.body);
        return rq.redirect("/article/list","%d번 게시물이 생성되었습니다".formatted(article.getId()));
    }

    @GetMapping("/article/list")
    String showList(Model model) {
        List<Article> articles = articleService.findAll();

        model.addAttribute("articles", articles);

        return "article/article/list";
    }


    @GetMapping("article/detail/{id}")
    String showDetail(Model model, @PathVariable long id) {
        Article article = articleService.findById(id).get();
        model.addAttribute("article", article);
        return "article/article/detail";
    }

    @GetMapping("article/modify/{id}")
    String modify(Model model, @PathVariable long id){
        if(!rq.isLogined()) throw new RuntimeException("로그인 후 이용해 주세요.");
        Article article = articleService.findById(id).get();
        model.addAttribute("article", article);
        return "article/article/modify";
    }

    //article/modify/{id} 이 주소에서 post 요청이 들어왔기 때문에
    @PostMapping("article/modify/{id}")
    String modify(@PathVariable long id, @Valid ModifyForm modifyForm){
        articleService.modify(id,modifyForm.title,modifyForm.body);

        return rq.redirect("/article/list", "%d번 게시물 수정되었습니다.".formatted(id));
    }

    @GetMapping("article/delete/{id}")
    String delete(@PathVariable long id){
        if(!rq.isLogined()) throw new RuntimeException("로그인 후 이용해 주세요.");
        articleService.delete(id);

        return rq.redirect("/article/list", "%d번 게시물 삭제되었습니다.".formatted(id));
    }

}


