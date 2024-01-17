package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcMemberRepository implements MemberRepository{

    // DataSource는 DB와 JDBC에서 사용하는 DB 연결 드라이버 객체
    @Autowired
    private DataSource dataSource;

    @Override
    public Member save(Member member) {
        // Checked Exception으로 예외를 강제함
        try {
            Connection conn = dataSource.getConnection();
            // ?에 실제 데이터 삽입 (첫번째 파라미터: 1번)
            String sql = "insert into member(name, email, password) values(?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            // preparedStatement에 데이터 세팅
            preparedStatement.setString(1, member.getName());
            preparedStatement.setString(2, member.getEmail());
            preparedStatement.setString(3, member.getPassword());
            // DB에 반영
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = new ArrayList<>();
        try {
            Connection conn = dataSource.getConnection();
            String sql = "select * from member";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                LocalDateTime now = resultSet.getTimestamp("createdAt").toLocalDateTime();
                Member member = Member.builder()
                        .name(name)
                        .email(email)
                        .password(password)
                        .build();
                member.setId(id);
                member.setCreatedAt(now);
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public Optional<Member> findById(int id) {
        Member member = null;
        try {
            Connection conn = dataSource.getConnection();
            String sql = "select * from member where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next(); // 커서 이동
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            LocalDateTime now = resultSet.getTimestamp("createdAt").toLocalDateTime();
            member = Member.builder()
                    .name(name)
                    .email(email)
                    .password(password)
                    .build();
            member.setCreatedAt(now);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(member);
    }

    @Override
    public void delete(Member member) {

    }
}
