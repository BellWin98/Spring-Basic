package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import com.encore.basic.dto.MemberSignUpRequest;
import com.encore.basic.repository.MemberRepository;
import com.encore.basic.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
public class MemberController {

    private final MemberService memberService;

    public MemberController() {
        this.memberService = new MemberService();
    }

    // 회원 목록 조회
    @GetMapping("/members")
    public String getMembers(Model model){
        log.info("가입된 회원 목록 api 시작");
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        log.info("가입된 회원 목록 api 종료");
        return "member/member-list";
    }

    // 회원 가입
    @GetMapping("/member/create-screen")
    public String createScreen(){
        log.info("회원가입 화면 리턴");
        return "member/member-create";
    }

    @PostMapping("/member/create")
    @ResponseBody
    public String create(MemberSignUpRequest req){
        log.info("회원가입 시작");
        Member member = memberService.signUp(req);
        log.info("회원가입 완료");
        return "회원가입 성공! " + member.getName() + "님 환영합니다.";
    }
}
