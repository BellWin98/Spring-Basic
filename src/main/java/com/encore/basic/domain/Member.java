package com.encore.basic.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor // 모든 매개변수를 넣은 생성자 (기본 생성자는 사라짐)
// Entity 어노테이션을 통해 MariaDB의 테이블 및 컬럼을 자동 생성
// class명은 테이블명, 변수명은 컬럼명과 동일
@Entity
// 기본 생성자가 반드시 필요하다
// 이유: DB 조회 후 Member 객체 자동 생성할 때, 리플렉션이 런타임 상황에서 Setter 없이도 데이터 세팅해줌
@NoArgsConstructor
public class Member {
    @Setter
    @Id // PK 설정
    // IDENTITY: Auto Increment 설정 / AUTO: JPA 구현체가 자동으로 적절한 키생성 전략 선택
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // String은 DB의 VARCHAR로 변환
    private String name;
    @Column(nullable = false, length = 50, unique = true)
    private String email;
    private String password;

    @Setter
    // name 옵션을 통해 DB의 컬럼명 별도 지정 가능
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
