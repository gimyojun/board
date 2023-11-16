package com.ll.board.domain.article.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Article {
    private long id;
    private String title;
    private String body;
    public Article(String title, String body){
        this.title=title;
        this.body=body;
    }
}
