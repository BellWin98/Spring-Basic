package com.encore.basic.service;

import com.encore.basic.domain.Member;
import com.encore.basic.dto.MemberSignUpRequest;
import com.encore.basic.repository.MemberRepository;

import java.util.List;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(){
        this.memberRepository = new MemberRepository();
    }

    public Member signUp(MemberSignUpRequest req){
        Member member = new Member(req.getName(), req.getEmail(), req.getPassword());
        memberRepository.save(member);

        return member;
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
}
