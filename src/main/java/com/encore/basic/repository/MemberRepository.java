package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository   {

    Member save(Member member);
    List<Member> findAll();

    Optional<Member> findById(int id);

    void delete(Member member);
}
