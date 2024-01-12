package com.encore.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class MemberController {

    // 회원 목록 조회
    @GetMapping("/members")
    public String getMembers(){
        return "member-list";
    }

    // 회원 가입
    @GetMapping("/member/create-screen")
    public String createScreen(){
        return "member-create";
    }

    @PostMapping("member/create")
    @ResponseBody
    public String create(){
        return "OK";
    }
}
