package com.ll.board.domain.article.article.service;

import com.ll.board.domain.article.article.entity.Article;
import com.ll.board.domain.article.article.repository.ArticleRepository;
import com.ll.board.domain.member.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    //생성자 하나니까 @Autowired 생략가능
    //@Autowired
    private final ArticleRepository articleRepository;
    //오토와이어링 파이널 쓰려면
//    public ArticleService(ArticleRepository articleRepository) {
//        this.articleRepository = articleRepository;
//    }

    public Article write(Member author, String title, String body) {
        Article article = new Article(author,title,body);
        //레포지토리에서 완성된 Article을 리턴해야한다
        return articleRepository.save(article);
    }

    public Article findLastArticle() {
        return articleRepository.findLastArticle();
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Optional<Article> findById(long id) {
        return articleRepository.findById(id);
    }

    public void delete(Article article) {
        articleRepository.delete(article);
    }

    public void modify(Article article, String title, String body) {
        article.setTitle(title);
        article.setBody(body);
    }

    public boolean canModify(Member actor, Article article) {
        if (actor == null) return false;
        return article.getAuthor().equals(actor);
    }

    public boolean canDelete(Member actor, Article article) {
        if(actor.isAdmin()) return true;
        if(actor==null) return false;

        return article.getAuthor().equals(actor);
    }
}
