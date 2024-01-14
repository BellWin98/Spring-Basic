package com.encore.basic.dto;

import lombok.Data;

@Data
public class MemberSignUpRequest {
    private String name;
    private String email;
    private String password;
}
