package com.encore.basic.servlet_jsp;

import com.encore.basic.domain.Hello;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello-servlet-rest-post")
public class HelloServletRestPost extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        Hello hello = mapper.readValue(req.getReader(), Hello.class);

        System.out.println(hello);

        // 응답 Header
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        // 응답 Body
        PrintWriter out = resp.getWriter();
        out.print("ok");

        // 버퍼를 통해 조립이 이루어지므로, 버퍼를 비우는 과정
        out.flush();
    }
}
