package com.example.biblioteca;

import com.example.biblioteca.model.Libro;
import com.example.biblioteca.repo.LibrosRepo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(name = "SearchBookServlet", value = "/search")
public class SearchBookServlet extends HttpServlet {

    //localhost:8080/search?patr=asdfasdf
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LibrosRepo librosRepo = new LibrosRepo();
        Set<Libro> results = search(librosRepo, request.getParameter("patr"));

        response.getWriter().print(
                "<!Doctype Html>\n" +
                        "<html lang=\"en\">\n" +
                        "<Head>\n" +
                        "  <meta charset=\"UTF-8\">\n" +
                        "  <Title>\n" +
                        "    Make a Navigation Bar\n" +
                        "  </Title>\n" +
                        "\n" +
                        "  <style type=text/css>\n" +
                        "    body\n" +
                        "    {\n" +
                        "      height: 125vh;\n" +
                        "      margin-top: 80px;\n" +
                        "      padding: 30px;\n" +
                        "      background-size: cover;\n" +
                        "      font-family: sans-serif;\n" +
                        "    }\n" +
                        "    header {\n" +
                        "      background-color: orange;\n" +
                        "      position: fixed;\n" +
                        "      left: 0;\n" +
                        "      right: 0;\n" +
                        "      top: 5px;\n" +
                        "      height: 30px;\n" +
                        "      display: flex;\n" +
                        "      align-items: center;\n" +
                        "      box-shadow: 0 0 25px 0 black;\n" +
                        "    }\n" +
                        "    header * {\n" +
                        "      display: inline;\n" +
                        "    }\n" +
                        "    header li {\n" +
                        "      margin: 20px;\n" +
                        "    }\n" +
                        "    header li a {\n" +
                        "      color: blue;\n" +
                        "      text-decoration: none;\n" +
                        "    }\n" +
                        "  </style>\n" +
                        "</Head>\n" +
                        "<Body>\n" +
                        "  <header>\n" +
                        "    <nav>\n" +
                        "      <ul>\n" +
                        "        <li>\n" +
                        "          <p>Usuario: Franco\n" +
                        "        </li>\n" +
                        "        <li>\n" +
                        "          <a href=\"/logout\"> Cerrar Sesión </a>\n" +
                        "        </li>\n" +
                        "        <li>\n" +
                        "          <form method=\"get\" action=\"http://localhost:8080/Biblioteca_war_exploded/search\">\n" +
                        "            <input type=\"text\" placeholder=\"Search Here\" name=\"patr\">\n" +
                        "            <button type=\"submit\">\n" +
                        "              <span>\uD83D\uDD0D</span>\n" +
                        "            </button>\n" +
                        "          </form>\n" +
                        "        </li>\n" +
                        "      </ul>\n" +
                        "    </nav>\n" +
                        "  </header>\n" +
                        "  <h1>Catálogo Digital</h1>"
        );

        for (Libro x : results) {
            response.getWriter().print(
                    "<div>\n" +
                    "        <h2>" + x.getTitulo() + "</h2>\n" +
                    "        <p>\n" +
                    "            <ul>\n" +
                    "                <li>" + x.getAutor() + "</li>\n" +
                    "                <li>" + x.getResumen() + "Titulo</li>\n" +
                    "                <li> " + "<a href=\"http://localhost:8080/Biblioteca_war_exploded/download?file=" + x.getFile() + "\"> Descarga Fichero </a>" + "</li>" +
                    "            </ul>\n" +
                    "</div>"
            );
        }

        // <a href="download?file=">Login</a>
        //<A HREF=\"" + request.getServletContext().getRealPath("./files/downloads/" + x.getFile()) +"\">Descarga</a>"

        response.getWriter().print(
                "</Body>\n" +
                        "</Html>"
        );
    }

    private Set<Libro> search(LibrosRepo librosRepo, String patr) {

        return librosRepo.getLibros()
                         .stream()
                         .filter(x -> x.getTitulo().contains(patr))
                         .collect(Collectors.toSet());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
