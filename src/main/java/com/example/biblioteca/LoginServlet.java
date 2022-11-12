package com.example.biblioteca;

import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.repo.UserRepo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    //private final UserRepo userRepo = new UserRepo();
    /**
     * Presentar la página de login al cliente
     * @param request Petici'on de aceso
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
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        UserRepo userRepo = new UserRepo();
        String usuario = request.getParameter("nombre");//Arrays.toString(request.getParameterValues("nombre"));
        String pass = request.getParameter("pass");//Arrays.toString(request.getParameterValues("pass"));
        File htmlCatalogo = new File(request.getServletContext().getRealPath("./WEB-INF/classes/html/CatalogoLogged.html"));

        try {
            if (userRepo.get(usuario).equals(new Usuario(usuario, pass))){
                request.getSession(true).setAttribute("logg", usuario);
                request.getRequestDispatcher("catalogo").forward(request, response);

                //response.getWriter().print(Files.readString(htmlCatalogo.toPath()));
            }
        } catch (NullPointerException e) {
            response.getWriter().print(Files.readString(htmlCatalogo.toPath()) + "\n<p style=\"color:#FF0000\";>Datos incorrectos</p>");
        }
    }
}
