package com.example.biblioteca;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet(name = "HomeServlet", value = "/")
public class HomeServlet extends HttpServlet {
    private static final File index = new File("C:\\Users\\Usuario\\IdeaProjects\\Biblioteca\\src\\main\\webapp\\index.jsp");
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        response.getWriter().print(Files.readString(index.toPath()));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
