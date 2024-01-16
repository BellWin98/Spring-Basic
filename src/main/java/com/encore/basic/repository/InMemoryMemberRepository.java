package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryMemberRepository implements MemberRepository{
    private static int id;
    private final List<Member> members;

    public InMemoryMemberRepository(){
        members = new ArrayList<>();
    }

    public Member save(Member member){
        id += 1;
        LocalDateTime now = LocalDateTime.now();
        member.setId(id);
        member.setCreatedAt(now);
        members.add(member);
        return member;
    }

    public List<Member> findAll(){
        return members;
    }

    public Optional<Member> findById(int id){
        for (Member member : members){
            if (member.getId() == id){
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }
}
