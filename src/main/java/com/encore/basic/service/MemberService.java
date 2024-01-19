package com.encore.basic.service;

import com.encore.basic.domain.Member;
import com.encore.basic.dto.MemberResponse;
import com.encore.basic.dto.MemberRequest;
import com.encore.basic.repository.MemberRepository;
import com.encore.basic.repository.SpringDataJpaMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


// 스프링 빈 (Bean): 스프링이 생성하고 관리하는 객체를 의미
//    1. 제어의 역전(IOC: Inversion Of Control)
//    2. IOC 컨테이너가 스프링 빈을 관리
@Service // 이 어노테이션을 통해 싱글톤 컴포넌트로 생성됨. -> 스프링 빈으로 등록됨
public class MemberService {

//    @Autowired // 의존성 주입 방법 1
//    private MemberRepository memberRepository; // final 쓰면 초기화 해야 하므로 뺀다.

    // 생성자 주입 방식: 의존성 주입 방법 2
    // 인터페이스 - 구현체 방식
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(SpringDataJpaMemberRepository springDataJpaMemberRepository){
        this.memberRepository = springDataJpaMemberRepository;
    }

    // @Transactional 어노테이션을 클래스 단위로 붙이면 모든 메서드에 각각 Transaction 적용
    // Transactional을 적용하면 한 메서드 단위로 트랜잭션 지정
    @Transactional
    public void signUp(MemberRequest req) throws IllegalArgumentException, DataIntegrityViolationException {
        Member member = Member.builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(req.getPassword())
                .build();
        memberRepository.save(member);
        // 트랜잭션 테스트
//        Member member = Member.builder()
//                .name(req.getName())
//                .email(req.getEmail())
//                .password(req.getPassword())
//                .build();
//        memberRepository.save(member);
//        if (member.getName().contains("kim")){
//            throw new IllegalArgumentException();
//        }
    }

    @Transactional
    public void update(MemberRequest req) throws EntityNotFoundException{
        Member member = memberRepository.findById(req.getId()).orElseThrow(EntityNotFoundException::new);
        member.updateMember(req.getName(), req.getPassword());
        memberRepository.save(member);
    }

    public void delete(int id) throws EntityNotFoundException{
        Member member = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        memberRepository.delete(member);
    }

    public List<MemberResponse> findMembers(){
        List<Member> members = memberRepository.findAll();
        List<MemberResponse> memberResponses = new ArrayList<>();
        for (Member member : members){
            MemberResponse memberResponse = MemberResponse.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .email(member.getEmail())
                    .password(member.getPassword())
                    .now(member.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .build();
            memberResponses.add(memberResponse);
        }

        return memberResponses;
    }

    public MemberResponse findMember(int id) throws EntityNotFoundException{
        // orElseThrow를 통해 Optional 객체에서 값을 꺼냄
        // HTTP 문서에다가 예외 문구를 넣어 주어야 웹페이지 상에 보여진다. (ResponseEntity 클래스 활용: 상태 코드 처리)
        // 자바에서 발생하는 모든 예외는 500코드(서버 에러)로 반환됨. 따라서, 적절한 에러 코드를 반환해야함.
        Member member = memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("검색하신 ID의 Member가 없습니다."));

        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .password(member.getPassword())
                .now(member.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }
}
