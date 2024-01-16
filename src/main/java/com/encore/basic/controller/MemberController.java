package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import com.encore.basic.dto.MemberResponse;
import com.encore.basic.dto.MemberSignUpRequest;
import com.encore.basic.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * 1. 회원 목록 조회 기능 구현
 *  - url: members
 *  - 화면: member/memberList -> 이름, email, password (테이블 구조 형식)
 *  - td는 3줄 정도 입력
 *
 * 2. 회원가입
 *   - url: GetMapping(member/create-screen), PostMapping(member/create)
 *   - 화면: member/member-create (화면은 form data 형식)
 */
@Controller
@Slf4j
@RequestMapping("/members")
//@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

//    @Autowired // 의존성 주입 방법 1 -> 필드 주입 방식
//    private MemberService memberService; // final 쓰면 초기화 해야 하므로 뺀다.

    // 의존성 주입방법 2: 생성자 주입 방식 -> 가장 많이 사용
    // final 키워드를 붙여서 재생성이 안되도록 설정

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    // 의존성 주입 방법 3: @RequiredArgsConstructor를 이용한 방식
//    @RequiredArgsConstructor: @NonNull 어노테이션이 붙어있는 필드
//    또는 초기화 되지 않은 final 필드를 대상으로 생성자 생성

    // 회원 목록 조회
    @GetMapping("/")
    public String getMembers(Model model){
        log.info("가입된 회원 목록 api 시작");
        List<MemberResponse> members = memberService.findMembers();
        model.addAttribute("members", members);
        log.info("가입된 회원 목록 api 종료");
        return "member/member-list";
    }

    // 회원 가입
    @GetMapping("/create")
    public String create(){
        log.info("회원가입 화면 리턴");
        return "member/member-create";
    }

    @PostMapping("/create")
    public String create(MemberSignUpRequest req){
        log.info("회원가입 시작");
        memberService.signUp(req);
        log.info("회원가입 완료");
        return "redirect:/members/";
    }

    @GetMapping("/member/find")
    public String findMemberDetails(@RequestParam(value = "id") int id, Model model){
        log.info("회원 상세조회 API Start PK id: " + id);
        try {
            MemberResponse member = memberService.findMember(id);
            log.info("회원 상세 조회 Service 통과");
            model.addAttribute("member", member);
            log.info("회원 상세조회 API End");
            return "/member/member-details";
        } catch (NoSuchElementException e){
            return "/404-error-page";
        }
    }
}
