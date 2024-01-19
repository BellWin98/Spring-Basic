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

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 기본 패턴: req를 받아, res를 return하는 방식
        req.setAttribute("myData", "jsp test data");
        req.getRequestDispatcher("/WEB-INF/views/hello-jsp.jsp").forward(req, resp);
    }
}
