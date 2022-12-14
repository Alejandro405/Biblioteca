package com.example.biblioteca;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "DownloadServlet", value = "/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * La peticion contie:
         *      Un parametro con el nombre del fichero /download?file=tituloX.txt
         */
        String fileParth = "C:\\";

        String fileName = request.getParameter("file");
        String filepath2 = request.getServletContext().getRealPath("./");

        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment; filename\"" + fileName + "\"");

        //C:\Users\Usuario\IdeaProjects\Biblioteca\target\Biblioteca-1.0-SNAPSHOT\Resources\files\downloads\titulo1.txt
        try (FileInputStream srcData = new FileInputStream(filepath2 + "\\Resources\\files\\downloads\\" + fileName)) {
            response.getWriter()
                    .write(new String(srcData.readAllBytes(), StandardCharsets.UTF_8));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
