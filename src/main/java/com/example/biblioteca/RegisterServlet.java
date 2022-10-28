package com.example.biblioteca;

import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.repo.UserRepo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    private static UserRepo userRepo;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        File html = new File(
                request.getServletContext().getRealPath("./WEB-INF/classes/html/RegisterForm.html")
        );

        response.getWriter().print(Files.readString(html.toPath()));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nick = Arrays.toString(request.getParameterValues("nick"));
        String pass = Arrays.toString(request.getParameterValues("pass"));
        String nombre = Arrays.toString(request.getParameterValues("nombre"));
        String rol = Arrays.toString(request.getParameterValues("rol"));
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        File htmlReg = new File(
                request.getServletContext().getRealPath("./WEB-INF/classes/html/RegisterForm.html")
        );


        if (nick.equals("") || pass.equals("")) {
            response.getWriter().print(Files.readString(htmlReg.toPath()) + "\n<p style=\"color:#FF0000\";>Usuario y contrasenia campos obligatorios</p>");
        } else if (userRepo.contains(nick)){
            response.getWriter().print(Files.readString(htmlReg.toPath()) + "\n<p style=\"color:#FF0000\";>Usuario ya registrado</p>");
        } else {
            response.getWriter().print(Files.readString(htmlReg.toPath()));
            userRepo.addUser(new Usuario(nick, pass, nombre, rol));
        }
    }
}
