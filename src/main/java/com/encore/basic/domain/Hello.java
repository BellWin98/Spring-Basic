package com.encore.basic.domain;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
@Data // Getter, Setter, toString, equals 등 사전 구현
public class Hello {
    private String name;
    private String email;
    private String password;
}
