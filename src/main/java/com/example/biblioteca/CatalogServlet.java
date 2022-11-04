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
                    "<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <title>Title</title>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "    <h1>Catálogo Digital</h1>\n"
            );

            for (Libro x : librosRepo.getLibros()) {
                out.print(
                        "<div>\n" +
                                "        <h2>" + x.getTitulo() + "</h2>\n" +
                                "        <p>\n" +
                                "            <ul>\n" +
                                "                <li>" + x.getAutor() + "</li>\n" +
                                "                <li>" + x.getResumen() + "Titulo</li>\n" +
                                "                <li><A HREF=\"" + request.getServletContext().getRealPath("./files/downloads/" + x.getFile()) +"\">Descarga</a>" +
                                "            </ul>\n" +
                                "</div>"
                );
            }

            // "                <li><A HREF=\"" + downloadsDirectory.toPath() + "\\"+ x.getFile() +"\">Descarga</a>"

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
