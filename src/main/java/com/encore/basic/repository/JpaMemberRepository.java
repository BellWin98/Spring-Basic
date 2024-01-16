package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaMemberRepository implements MemberRepository{
    @Override
    public Member save(Member member) {
        return member;
    }

    @Override
    public List<Member> findAll() {
        return null;
    }

    @Override
    public Optional<Member> findById(int id) {
        return Optional.empty();
    }
}
