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

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private UserRepo userRepo;
    /**
     * Presentar la página de login al cliente
     * @param request Peticion de aceso
     * @param response Contenedor donde volcar la página HTML
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        File html = new File(
                request.getServletContext().getRealPath("./WEB-INF/classes/html/LoginForm.html")
        );

        response.getWriter().print(Files.readString(html.toPath()));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = Arrays.toString(request.getParameterValues("nombre"));
        String pass = Arrays.toString(request.getParameterValues("pass"));
        File htmlCatalogo = new File(request.getServletContext().getRealPath("./WEB-INF/classes/html/CatalogoLogged.html"));

        if (userRepo.get(usuario).equals(new Usuario(usuario, pass))){
            response.getWriter().print(Files.readString(htmlCatalogo.toPath()));
        } else{
            response.getWriter().print(Files.readString(htmlCatalogo.toPath()) + "\n<p style=\"color:#FF0000\";>Datos incorrectos</p>");
        }
    }
}
