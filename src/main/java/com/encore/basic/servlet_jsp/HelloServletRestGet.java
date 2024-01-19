package com.encore.basic.servlet_jsp;

import com.encore.basic.domain.Hello;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello-servlet-rest-get")
public class HelloServletRestGet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Hello hello = new Hello();
        hello.setName("hello");
        hello.setEmail("hello@naver.com");
        hello.setPassword("hello1234");

        // 응답 Header
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // 응답 Body
        PrintWriter out = resp.getWriter();
        // 객체를 JSON으로 직렬화
        out.print(mapper.writeValueAsString(hello));

        // 버퍼를 통해 조립이 이루어지므로, 버퍼를 비우는 과정
        out.flush();
    }
}
