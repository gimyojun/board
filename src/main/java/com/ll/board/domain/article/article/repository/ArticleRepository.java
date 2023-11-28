package com.ll.board.domain.article.article.repository;

import com.ll.board.domain.article.article.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {
    private final List<Article> articles = new ArrayList<>();


    public Article save(Article article){

        article.setId(articles.size()+1L);
        articles.add(article);

        return article;
    }

    public Article findLastArticle() {
        return articles.getLast();

    }

    public List<Article> findAll() {
        return articles;
    }

    public Optional<Article> findById(long id) {
        return articles.stream()
                .filter(article -> article.getId() == id)
                //처음으로 찾으면 리턴
                .findFirst();
    }

    public void delete(Article article) {
        articles.remove(article);
    }

}
