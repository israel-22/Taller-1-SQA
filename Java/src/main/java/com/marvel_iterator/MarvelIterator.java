package com.marvel_iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Clase Pelicula
class Pelicula {
    private String titulo;
    private int anio;

    public Pelicula(String titulo, int anio) {
        this.titulo = titulo;
        this.anio = anio;
    }

    @Override
    public String toString() {
        return this.titulo + " (" + this.anio + ")";
    }
}

// Clase PeliculaIterator: Implementación de Iterator
class PeliculaIterator implements Iterator<Pelicula> {
    private List<Pelicula> peliculas;
    private int index;

    public PeliculaIterator(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < peliculas.size();
    }

    @Override
    public Pelicula next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }
        Pelicula pelicula = peliculas.get(index);
        index++;
        return pelicula;
    }
}

// Clase Cine: Implementa Iterable
class Cine implements Iterable<Pelicula> {
    private List<Pelicula> peliculas;

    public Cine() {
        this.peliculas = new ArrayList<>();
    }

    public void agregarPelicula(Pelicula pelicula) {
        peliculas.add(pelicula);
    }

    @Override
    public Iterator<Pelicula> iterator() {
        return new PeliculaIterator(peliculas);
    }
}

// Clase principal para ejecutar el código
public class MarvelIterator {
    public static void main(String[] args) {
        // Creación de la colección de películas del UCM
        Cine cine = new Cine();
        cine.agregarPelicula(new Pelicula("Iron Man", 2008));
        cine.agregarPelicula(new Pelicula("Capitán América: El primer vengador", 2011));
        cine.agregarPelicula(new Pelicula("Los Vengadores", 2012));
        cine.agregarPelicula(new Pelicula("Guardians of the Galaxy", 2014));
        cine.agregarPelicula(new Pelicula("Avengers: Endgame", 2019));

        // Iterando sobre las películas y mostrando la lista
        for (Pelicula pelicula : cine) {
            System.out.println(pelicula);
        }
    }
}
