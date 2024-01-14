package com.encore.basic.repository;

import com.encore.basic.domain.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {

    private final List<Member> members;

    public MemberRepository(){
        members = new ArrayList<>();
    }

    public void save(Member member){
        members.add(member);
    }

    public List<Member> findAll(){
        return members;
    }

}
