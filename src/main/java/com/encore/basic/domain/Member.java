package com.encore.basic.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor // 모든 매개변수를 넣은 생성자 (기본 생성자는 사라짐)
public class Member {
    private int id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdAt;
}
