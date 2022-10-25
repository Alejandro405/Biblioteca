package com.example.biblioteca.repo;

import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.tools.DuplicatedUserException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class UserRepo {
    private static final File usuarios = new File(
            System.getProperty("user-dir")
                    + "src/main/java/com/example/biblioteca/repo/usuarios.txt"
    );

    // Conjunto de usuarios
    private static List<Usuario> usuariosApp = initUsers();

    // Diccionario para los datos de las sesiones --> <Usuario, Set<Atributos>> --> Set<Atributos> solo contiene booleano para el iniciado de sesión del usuario
    // private static Map<String, Boolean> usuariosLogeados = new HashMap<>();
    // Dicha información se almacena en las sesiones Http

    private static List<Usuario> initUsers() {
        List<Usuario> res = null;

        try {
            res = Files.newBufferedReader(usuarios.toPath())
                    .lines()
                    .map(x -> Usuario.usuario(x.split(" "))).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return res;
    }

    public void addUser(String name, String pass) throws DuplicatedUserException {
        addUser(new Usuario(name, pass));
    }

    public void addUser(Usuario newUser) throws DuplicatedUserException {
        if (usuariosApp.contains(newUser))
            throw new DuplicatedUserException("[ERROR] El usuario indicado ya pertenece al sistema");

        usuariosApp.add(newUser);

        // Actualizar fichero (opcional)
    }

}
