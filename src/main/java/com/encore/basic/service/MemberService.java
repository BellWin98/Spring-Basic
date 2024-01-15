package com.encore.basic.service;

import com.encore.basic.domain.Member;
import com.encore.basic.dto.MemberResponse;
import com.encore.basic.dto.MemberSignUpRequest;
import com.encore.basic.repository.InMemoryMemberRepository;
import com.encore.basic.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service // 이 어노테이션을 통해 싱글톤 컴포넌트로 생성됨. -> 스프링 빈으로 등록됨
// 스프링 빈 (Bean): 스프링이 생성하고 관리하는 객체를 의미
//    1. 제어의 역전(IOC: Inversion Of Control)
//    2. IOC 컨테이너가 스프링 빈을 관리
public class MemberService {

    private static int id;

//    @Autowired // 의존성 주입 방법 1
//    private MemberRepository memberRepository; // final 쓰면 초기화 해야 하므로 뺀다.

    // 생성자 주입 방식: 의존성 주입 방법 2
    // 인터페이스 - 구현체 방식
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(InMemoryMemberRepository inMemoryMemberRepository){
        this.memberRepository = inMemoryMemberRepository;
    }

        public void signUp(MemberSignUpRequest req){
        LocalDateTime now = LocalDateTime.now();
        id += 1;
        Member member = Member.builder()
                .id(id)
                .name(req.getName())
                .email(req.getEmail())
                .password(req.getPassword())
                .createdAt(now)
                .build();
        memberRepository.save(member);
    }

    public List<MemberResponse> findMembers(){
        List<Member> members = memberRepository.findAll();
        List<MemberResponse> memberResponses = new ArrayList<>();
        for (Member member : members){
            MemberResponse memberResponse = MemberResponse.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .email(member.getEmail())
                    .build();
            memberResponses.add(memberResponse);
        }

        return memberResponses;
    }

    public MemberResponse findMember(int id){
        Member member = memberRepository.findById(id);

        return MemberResponse.builder()
                .name(member.getName())
                .email(member.getEmail())
                .password(member.getPassword())
                .now(member.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }
}
