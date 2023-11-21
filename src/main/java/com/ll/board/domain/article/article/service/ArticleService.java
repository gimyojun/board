package com.ll.board.domain.article.article.service;

import com.ll.board.domain.article.article.entity.Article;
import com.ll.board.domain.article.article.repository.ArticleRepository;
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

    public Article write(String title, String body) {
        Article article = new Article(title,body);
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

    public void delete(long id) {
        articleRepository.delete(id);
    }

    public void modify(long id, String title, String body) {
        articleRepository.modify(id,title,body);
    }
}
