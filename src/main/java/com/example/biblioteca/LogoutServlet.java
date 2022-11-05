package com.example.biblioteca;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * response.setContentType("text/html");
         *         response.setCharacterEncoding("utf-8");
         *
         *         response.getWriter().print(
         *                 "<!DOCTYPE html>\n" +
         *                 "<html>\n" +
         *                 "<head>\n" +
         *                 "    <title>JSP - Hello World</title>\n" +
         *                 "</head>\n" +
         *                 "<body>\n" +
         *                 "    <h1>\n" +
         *                 "        <%= \"Bienvenido al catálogo de la biblioteca digital DST\" %>\n" +
         *                 "    </h1>\n" +
         *                 "    <br/>\n" +
         *                 "    <a href=\"login\">Login</a>\n" +
         *                 "    <a href=\"register\">Register</a>\n" +
         *                 "</body>\n" +
         *                 "</html>"
         *         );
         */
        request.getSession().removeAttribute("logg");
        request.getRequestDispatcher("/").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
