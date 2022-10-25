package com.example.biblioteca.repo;

import com.example.biblioteca.model.Usuario;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class testing {


    public static void main(String[] args) {
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
        }
    }
}
