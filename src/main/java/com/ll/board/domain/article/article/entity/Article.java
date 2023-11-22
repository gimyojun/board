package com.ll.board.domain.article.article.entity;

import com.ll.board.domain.member.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Article {
    private long id;
    private String title;
    private String body;
    private Member author;
    public Article(Member author, String title, String body){
        this.title=title;
        this.body=body;
        this.author=author;
    }
}
