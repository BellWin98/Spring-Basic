package com.encore.basic.repository;

import com.encore.basic.domain.Member;

import java.util.List;

public interface MemberRepository {

    void save(Member member);
    List<Member> findAll();

    Member findById(int id);
}
