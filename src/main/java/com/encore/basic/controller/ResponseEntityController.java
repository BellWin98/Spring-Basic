package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequestMapping("/response/entity")
@RestController
public class ResponseEntityController {

    // @ResponseStatus 어노테이션 방식
    @GetMapping("/response-status1")
    // 간편하지만 유연성은 떨어진다.
    @ResponseStatus(HttpStatus.CREATED)
    public String responseStatus1(){
        return "OK";
    }

    @GetMapping("/response-status2")
    @ResponseStatus(HttpStatus.CREATED)
    public Member responseStatus2(){
        return Member.builder()
                .name("테스트")
                .email("test@naver.com")
                .password("testPassword")
                .build();
    }

    // ResponseEntity 객체를 직접 생성한 방식
    @GetMapping("/custom1")
    public ResponseEntity<Member> custom1(){
        Member member = Member.builder()
                .name("테스트")
                .email("test@naver.com")
                .password("testPassword")
                .build();
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    // ResponseEntity<String>일 경우 text/html로 설정 (활용도 떨어짐)
    @GetMapping("/custom2")
    public ResponseEntity<String> custom2(){
        String html = "<h1>없는 ID 입니다.</h1>";
        return new ResponseEntity<>(html, HttpStatus.NOT_FOUND);
    }

    // map형태의 메시지 커스텀
    @GetMapping("/map-custom1")
    public ResponseEntity<Map<String, String>> customMap1(){
        // 객체는 JSON으로 직렬화 된다.
        Map<String, String> body = new HashMap<>();
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        body.put("status", String.valueOf(httpStatus.value()));
        body.put("error message", httpStatus.getReasonPhrase());
        return new ResponseEntity<>(body, httpStatus);
    }

    // status 201, message: 객체
    @GetMapping("/map-custom2")
    public ResponseEntity<Map<String, Object>> customMap2(){
        // 객체는 JSON으로 직렬화 된다.
        Map<String, Object> body = new HashMap<>();
        Member member = Member.builder()
                .name("테스트2")
                .email("test2@naver.com")
                .password("test2Password")
                .build();
        HttpStatus httpStatus = HttpStatus.CREATED;
        body.put("status", String.valueOf(httpStatus.value()));
        body.put("data", member);
        return new ResponseEntity<>(body, httpStatus);
    }

    // 메서드 체이닝 방식: ResponseEntity의 클래스 메서드 사용
    @GetMapping("/chaining1")
    public ResponseEntity<Member> chaining1(){
        Member member = Member.builder()
                .name("테스트3")
                .email("test3@naver.com")
                .password("test3Password")
                .build();

        return ResponseEntity.ok(member);
    }

    @GetMapping("/chaining2")
    public ResponseEntity<String> chaining2(){
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/chaining3")
    public ResponseEntity<Member> chaining3(){
        Member member = Member.builder()
                .name("테스트3")
                .email("test3@naver.com")
                .password("test3Password")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }
}
