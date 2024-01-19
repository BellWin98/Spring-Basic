package com.encore.basic.controller;

import com.encore.basic.common.ResponseConstants;
import com.encore.basic.domain.Member;
import com.encore.basic.dto.MemberRequest;
import com.encore.basic.dto.MemberResponse;
import com.encore.basic.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Api(tags = "회원 API")
@RequestMapping("/rest/members")
public class MemberRestController {

    private final MemberService memberService;

    @Autowired
    public MemberRestController(MemberService memberService){
        this.memberService = memberService;
    }

    // 회원 목록 조회
    @GetMapping("/")
    public List<MemberResponse> getMembers(){
        return memberService.findMembers();
    }

    @PostMapping("/create")
    public String create(@RequestBody MemberRequest req){
        try {
            memberService.signUp(req);
            return "ok";
        } catch (DataIntegrityViolationException e){
            return "이메일 중복 에러";
        }
    }

    @PatchMapping("/member/update")
    public MemberResponse update(@RequestBody MemberRequest req){
        memberService.update(req);
        return memberService.findMember(req.getId());
    }

    @DeleteMapping("/member/delete/{id}")
    public String delete(@PathVariable int id){
        memberService.delete(id);
        return "ok";
    }

    @GetMapping("/member/find/{id}")
    public ResponseEntity<Map<String, Object>> findMemberDetails(@PathVariable int id){
        try{
            return ResponseConstants.success(HttpStatus.OK, memberService.findMember(id));
        } catch (EntityNotFoundException e){
            e.printStackTrace();
            return ResponseConstants.fail(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
