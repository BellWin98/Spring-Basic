package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Spring Data Jpa의 기본 기능을 쓰기 위해 JpaRepository 상속 필요
// 상속 시 Entity명과 해당 Entity의 PK 타입을 명시
public interface SpringDataJpaMemberRepository extends MemberRepository, JpaRepository<Member, Integer> {

}
