package com.example.biblioteca.repo;

import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.tools.DuplicatedUserException;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class UserRepo {
    private static final File usuarios = new File("C:\\Users\\Usuario\\IdeaProjects\\Biblioteca\\src\\main\\java\\com\\example\\biblioteca\\repo\\usuarios.txt");
    /**
     * System.getProperty("user-dir")
     *                     + "src/main/java/com/example/biblioteca/repo/usuarios.txt"
     */

    // Conjunto de usuarios
    private Set<Usuario> usuariosApp;

    public UserRepo() {
        usuariosApp = initUsers();
    }

    // Diccionario para los datos de las sesiones --> <Usuario, Set<Atributos>> --> Set<Atributos> solo contiene booleano para el iniciado de sesión del usuario
    // private static Map<String, Boolean> usuariosLogeados = new HashMap<>();
    // Dicha información se almacena en las sesiones Http

    public static Set<Usuario> initUsers() {
        Set<Usuario> res = null;

        try {
            res = Files.newBufferedReader(usuarios.toPath())
                    .lines()
                    .map(x -> Usuario.usuario(x.split(" ")))
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return res;
    }

    public void addUser(String name, String pass) throws DuplicatedUserException {
        addUser(new Usuario(name, pass));
    }

    public void addUser(Usuario newUser)  {
        //if (usuariosApp.contains(newUser))
          //  throw new DuplicatedUserException("[ERROR] El usuario indicado ya pertenece al sistema");

        usuariosApp.add(newUser);

        // Actualizar fichero (opcional)
    }

    public Usuario get(String nick) {
        Usuario res = null;

        boolean encontrado = false;
        Iterator<Usuario> itr = this.usuariosApp.iterator();
        while(!encontrado && itr.hasNext()){
            res = itr.next();
            if (res.getUserName().equals(nick))
                encontrado = true;
        }
        return res;
    }

    public boolean contains(Usuario usuario) {
        return usuariosApp.contains(usuario);
    }

    public boolean contains(String nick) {
        return contains(new Usuario(nick));
    }

    public boolean saveUsers(){
        boolean res = true;
        try (PrintWriter outFile = new PrintWriter(new FileOutputStream(usuarios))){
            for (Usuario x:
                 usuariosApp) {
                outFile.println(x.getUserName() + " " + x.getPass());
            }
        } catch (FileNotFoundException e) {
            res = false;
        }


        return res;
    }
}
