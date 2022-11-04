package com.example.biblioteca.repo;

import com.example.biblioteca.model.Libro;
import com.example.biblioteca.tools.InvalidNumberOfArgumentException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class testing {


    public static void main(String[] args) throws IOException {
        File asdf = new File("C:\\Users\\Usuario\\IdeaProjects\\Biblioteca\\src\\main\\resources\\html\\files\\downloads");


        System.out.println(asdf.toPath() + "\\" + "titulo1.txt");

        /*
        File usuarios = new File(
                System.getProperty("user.dir")
                        + "/src/main/java/com/example/biblioteca/repo/usuarios.txt"
        );

        List<Usuario> res = null;

        try {
            res = Files
                    .newBufferedReader(usuarios.toPath())
                    .lines()
                    .map(x -> Usuario.usuario(x.split(" ")))
                    .collect(Collectors.toList());
            System.out.println(res.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        /*File html = new File(
                System.getProperty("user.dir") + "/src/main/resources/html/LoginForm.html"
        );

        System.out.println(Files.readString(html.toPath()));*/

        /*
        File libros = new File(System.getProperty("user.dir") + "/src/main/java/com/example/biblioteca/repo/libros.txt");

        List<Libro> res = null;

        try {

            res = Arrays.stream(Files.readString(libros.toPath()).split("\r\n" +".\r\n"))
                    .map(x -> {
                            Libro aux = null;
                            try {
                                aux = Libro.libro(x.split("\n"));
                            } catch (InvalidNumberOfArgumentException e) {
                                System.err.println(e.getMessage());
                            }
                            return aux;
                        })
                    .collect(Collectors.toList());

            for (Libro x : res ) {
                System.out.println(x);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
         */



    }
}
