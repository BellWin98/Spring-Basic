package com.encore.basic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class MemberResponse {
    private int id;
    private String name;
    private String email;
    private String password;
    private String now;
}
