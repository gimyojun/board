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
    private final List<Article> articles = new ArrayList<>() {{
        add(new Article(1L, "제목 1", "내용 1"));
        add(new Article(2L, "제목 2", "내용 2"));
        add(new Article(3L, "제목 3", "내용 3"));
    }};
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

    public void delete(long id) {
        // articles.remove(id);
        // 이게 더 안전한듯?
        articles.removeIf(article -> article.getId() == id);
    }

    public void modify(long id, String title, String body) {
        Optional<Article> arti = articles.stream().filter(article -> article.getId() == id).findFirst();
        arti.get().setTitle(title);
        arti.get().setBody(body);


    }
}
