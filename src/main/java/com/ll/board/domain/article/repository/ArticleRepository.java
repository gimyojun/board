package com.ll.board.domain.article.repository;

import com.ll.board.domain.article.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepository {
    private List<Article> articles = new ArrayList<>();

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
}
