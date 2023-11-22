package com.ll.board.domain.article.article.entity;

import com.ll.board.domain.member.member.entity.Member;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Article {
    @EqualsAndHashCode.Include
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
