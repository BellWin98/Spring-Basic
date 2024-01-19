package com.encore.basic.servlet_jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Controller가 아닌 WebServlet을 통해 라우팅
// HttpServlet 기술을 상속 받아서 사용
// WebServlet + jsp 조합
@WebServlet("/hello-servlet-jsp-get")
public class HelloServletJspGet extends HttpServlet {

    // 방법 1
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 기본 패턴: req를 받아, res를 return하는 방식
        req.setAttribute("myData", "jsp test data");
        req.getRequestDispatcher("/WEB-INF/views/hello-jsp.jsp").forward(req, resp);
    }

    // 방법 2
    // service 메서드는 서블릿에 들어오는 모든 요청 (Get, Post, Put, Delete 등)을 처리
    // 메서드를 명시하지 않아도 자동 처리
    // 다만, 구체적으로 doGet, doPost 등의 메서드를 쓰는 것이 더 좋은 문법
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("myData", "jsp test data");
        req.getRequestDispatcher("/WEB-INF/views/hello-jsp.jsp").forward(req, resp);
    }
}
