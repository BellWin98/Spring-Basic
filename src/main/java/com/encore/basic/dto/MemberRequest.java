package com.encore.basic.dto;

import lombok.Data;

@Data
public class MemberRequest {
    private int id;
    private String name;
    private String email;
    private String password;
}
