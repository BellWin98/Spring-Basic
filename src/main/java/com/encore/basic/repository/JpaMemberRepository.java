package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaMemberRepository implements MemberRepository{

    private final EntityManager entityManager;

    // EntityManager는 JPA의 핵심 클래스 (객체)
    // Entity의 생명주기를 관리, 데이터베이스와의 모든 상호작용을 책임짐
    // 엔티티를 대상으로 CRUD 하는 기능을 제공
    public JpaMemberRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Member save(Member member) {
        // persist: 전달된 엔티티(Member)가 EntityManager의 관리 상태가 되도록 만들어주고,
        // 트랜잭션이 커밋될 때 데이터베이스에 저장 (Insert 기능)
        // Service단에서 객체 Update할 때 기존 객체를 findById하면 entityManager의 persist가 pk의 변경사항을 인지
        // persist는 단순히 insert가 아니라는 것 알아두기
        entityManager.persist(member);
        return member;
    }

    @Override
    public List<Member> findAll() {

        // jpql: jpa의 객체 지향 쿼리 문법
        // 장점: DB에 따라 문법이 달라지지 않는 객체 지향 언어. 컴파일 타임에서 문법 Check
        // 단점: DB 고유의 기능과 성능을 극대화하기는 어려움.
        return entityManager.createQuery("select m from Member m", Member.class).getResultList();
    }

    @Override
    public Optional<Member> findById(int id) {
        // find메서드는 pk를 매개변수로 준다.
        Member member = entityManager.find(Member.class, id);

        // Member가 null이면 Service단에서 예외 처리
            return Optional.ofNullable(member);
    }

    @Override
    public void delete(Member member) {
        // delete의 경우 remove 메서드 사용
        // update의 경우 merge 메서드 사용
    }

    // PK외의 컬럼으로 조회할 때
    public List<Member> findByName(String name){
        List<Member> members = entityManager
                .createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return members;
    }
}
