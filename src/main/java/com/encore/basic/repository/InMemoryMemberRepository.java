package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryMemberRepository implements MemberRepository{

    private final List<Member> members;

    public InMemoryMemberRepository(){
        members = new ArrayList<>();
    }

    public void save(Member member){
        members.add(member);
    }

    public List<Member> findAll(){
        return members;
    }

    public Member findById(int id){
        for (Member member : members){
            if (member.getId() == id){
                return member;
            }
        }
        return null;
    }
}
