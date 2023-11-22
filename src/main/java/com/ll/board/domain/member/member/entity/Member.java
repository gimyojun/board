package com.ll.board.domain.member.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Member {
    private long id;
    private String username;
    private String password;
    public Member(String username, String password){
        this.username=username;
        this.password=password;
    }

    public boolean isAdmin() {
        if(username != null)
            return username.equals("admin");
        return false;
    }

    public List<String> getAuthorities() {
        if(isAdmin()){
            return List.of("ROLE_ADMIN");
        }
        return List.of("ROLE_MEMBER");

    }
}
