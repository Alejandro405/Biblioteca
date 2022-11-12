package com.example.biblioteca.repo;

import com.example.biblioteca.model.Libro;
import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.tools.InvalidNumberOfArgumentException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LibrosRepo {
    private static File libros = new File("C:\\Users\\Usuario\\IdeaProjects\\Biblioteca\\src\\main\\resources\\html\\files\\libros.txt");

    private final Set<Libro> librosApp;

    public LibrosRepo() {
        this.librosApp = initLibros();
    }

    private static Set<Libro> initLibros() {
        Set<Libro> res = null;

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
                    .collect(Collectors.toSet());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return res;
    }

    public Set<Libro> getLibros() {
        return librosApp;
    }
}
