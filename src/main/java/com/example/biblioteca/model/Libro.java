package com.example.biblioteca.model;

import com.example.biblioteca.tools.InvalidNumberOfArgumentException;

import java.util.Objects;

public class Libro {
    private String titulo;
    private String autor;
    private String file;
    private String resumen;

    public Libro(String titulo, String autor, String file, String resumen) {
        this.titulo = titulo;
        this.autor = autor;
        this.file = file;
        this.resumen = resumen;
    }

    public Libro(String titulo, String autor, String file) {
        this.titulo = titulo;
        this.autor = autor;
        this.file = file;
    }

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public Libro() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro)) return false;
        Libro libro = (Libro) o;
        return Objects.equals(titulo, libro.titulo) && Objects.equals(file, libro.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, autor, file, resumen);
    }

    /**
     * Instancia un ojeto de la clase libro através de un array de datos: 0 -> Título, 1 -> Autor, 2 -> fichero, 3-> resumen ,
     * @param split Array contenedor de los atributos del libro
     * @return objeto instaciado con la informacion contenida en aplit
     */
    public static Libro libro(String[] split) throws InvalidNumberOfArgumentException {
        if (split.length != 4)
            throw new InvalidNumberOfArgumentException("[ERROR]\tEl numero de datos aportados es incorrect. Se requieren cuatro atributos [título, autos, fichero, resumen]");
        return new Libro(split[0], split[1], split[2], split[3]);
    }
}
