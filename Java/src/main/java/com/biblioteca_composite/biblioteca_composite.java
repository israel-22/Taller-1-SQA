package com.biblioteca_composite;

import java.util.ArrayList;
import java.util.List;

// Interfaz ComponenteBiblioteca
interface ComponenteBiblioteca {
    void mostrar(int nivel);
}

// Clase Archivo
class Archivo implements ComponenteBiblioteca {
    private String nombre;

    public Archivo(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void mostrar(int nivel) {
        System.out.println(" ".repeat(nivel) + "- " + this.nombre);
    }
}

// Clase Carpeta
class Carpeta implements ComponenteBiblioteca {
    private String nombre;
    private List<ComponenteBiblioteca> componentes;

    public Carpeta(String nombre) {
        this.nombre = nombre;
        this.componentes = new ArrayList<>();
    }

    public void agregar(ComponenteBiblioteca componente) {
        this.componentes.add(componente);
    }

    @Override
    public void mostrar(int nivel) {
        System.out.println(" ".repeat(nivel) + "+ [" + this.nombre + "]");
        for (ComponenteBiblioteca componente : componentes) {
            componente.mostrar(nivel + 1);
        }
    }
}

// Clase principal para probar el patrón Composite
public class biblioteca_composite {
    public static void main(String[] args) {
        // Archivos
        Archivo archivo1 = new Archivo("documentos.txt");
        Archivo archivo2 = new Archivo("imagenes.jpg");
        Archivo archivo3 = new Archivo("video.mp4");
        Archivo archivo4 = new Archivo("Libro.pdf");
        Archivo archivo5 = new Archivo("Autor.txt");
        Archivo archivo6 = new Archivo("Editorial.txt");
        Archivo archivo7 = new Archivo("Año de publicacion.txt");

        // Carpetas
        Carpeta carpeta1 = new Carpeta("Mis Documentos");
        carpeta1.agregar(archivo1);
        carpeta1.agregar(archivo2);

        Carpeta carpeta2 = new Carpeta("Videos");
        carpeta2.agregar(archivo3);

        Carpeta carpeta3 = new Carpeta("Informacion");
        carpeta3.agregar(archivo4);
        carpeta3.agregar(archivo5);
        carpeta3.agregar(archivo6);
        carpeta3.agregar(archivo7);

        // Carpeta principal
        Carpeta carpetaPrincipal = new Carpeta("/");
        carpetaPrincipal.agregar(carpeta1);
        carpetaPrincipal.agregar(carpeta2);
        carpetaPrincipal.agregar(carpeta3);

        // Mostrar la estructura
        carpetaPrincipal.mostrar(0);
    }
}
