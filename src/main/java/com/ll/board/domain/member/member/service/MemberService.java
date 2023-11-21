package com.ll.board.domain.member.member.service;

import com.ll.board.domain.member.member.entity.Member;
import com.ll.board.domain.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member join(String username, String password) {
        Member member = new Member(username,password);
        return memberRepository.save(member);
    }
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
    public Optional<Member> findById(long id) {
        return memberRepository.findById(id);
    }

    public void delete(long id) {
        memberRepository.delete(id);
    }

    public void modify(long id, String username, String password) {
        Member member = findById(id).get();
        member.setUsername(username);
        member.setPassword(password);
    }
}
