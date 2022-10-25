package com.example.biblioteca.repo;

import com.example.biblioteca.model.Libro;
import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.tools.InvalidNumberOfArgumentException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LibrosRepo {
    private static File libros = new File(
            System.getProperty("user.fir") + "/src/main/java/com/example/biblioteca/repo/libros.txt"
    );

    private static List<Libro> librosApp = initLibros();

    private static List<Libro> initLibros() {
        List<Libro> res = null;

        try {
            res = Arrays.stream(Files.readString(libros.toPath())
                    .split("\n.\n"))
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

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return res;
    }


}
