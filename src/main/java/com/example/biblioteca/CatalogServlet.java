package com.example.biblioteca;

import com.example.biblioteca.model.Libro;
import com.example.biblioteca.repo.LibrosRepo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CatalogServlet", value = "/catalogo")
public class CatalogServlet extends HttpServlet {
    private static final File downloadsDirectory = new File("C:\\Users\\Usuario\\IdeaProjects\\Biblioteca\\src\\main\\resources\\html\\files\\downloads");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("logg") != null) {
            mostrarPaginaDescarga(request, response);
        } else {
            mostrarPaginaBis(request, response);
        }
    }

    private void mostrarPaginaBis(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            LibrosRepo librosRepo = new LibrosRepo();
            out.print(
                    "<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <title>Title</title>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "    <h1>Catálogo Digital</h1>\n" +
                            "    <div>\n" +
                            "        <h2>Libro 1:</h2>"
            );

            for (Libro x : librosRepo.getLibros()) {
                out.print(
                        "<div>\n" +
                        "        <h2>" + x.getTitulo() + "</h2>\n" +
                        "        <p>\n" +
                        "            <ul>\n" +
                        "                <li>" + x.getAutor() + "</li>\n" +
                        "                <li>" + x.getResumen() + "Titulo</li>\n" +
                        "                <li><a href=\"login\">Login</a></li>\n" +
                        "            </ul>\n" +
                        "</div>"
                );
            }

            out.print(
                            "</body>\n" +
                            "</html>"
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void mostrarPaginaDescarga(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()){
            LibrosRepo librosRepo = new LibrosRepo();
            out.print(
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
                            "          <a href=\"/download?file\"> Descarga Fichero </a>\n" +
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
                            "    <h1>Cat'alogo Digital</h1>"
            );

            for (Libro x : librosRepo.getLibros()) {
                out.print(
                        "<div>\n" +
                        "        <h2>" + x.getTitulo() + "</h2>\n" +
                        "        <p>\n" +
                        "            <ul>\n" +
                        "                <li>" + x.getAutor() + "</li>\n" +
                        "                <li>" + x.getResumen() + "Titulo</li>\n" +
                        "                <li> " + "<a href=\"/download?file=" + x.getFile() + "\"> Descarga Fichero </a>" + "</li>" +
                        "            </ul>\n" +
                        "</div>"
                );
            }

            // <a href="download?file=">Login</a>
            //<A HREF=\"" + request.getServletContext().getRealPath("./files/downloads/" + x.getFile()) +"\">Descarga</a>"

            out.print(
                    "</body>\n" +
                            "</html>"
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        mostrarPaginaBis(request, response);
    }
}
