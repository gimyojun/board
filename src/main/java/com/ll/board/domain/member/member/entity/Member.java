package com.ll.board.domain.member.member.entity;

import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Member {
    @EqualsAndHashCode.Include
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

    public List<SimpleGrantedAuthority> getAuthorities() {
        if(isAdmin()){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_MEMBER"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_MEMBER"));

    }
}
