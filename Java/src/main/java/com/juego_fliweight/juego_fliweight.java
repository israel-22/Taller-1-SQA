package com.juego_fliweight;
import java.util.HashMap;
import java.util.Map;

// Clase TipoEnemigo
class TipoEnemigo {
    private String nombre;
    private String region;
    private String textura;
    private String sonido;

    public TipoEnemigo(String nombre, String region, String textura, String sonido) {
        this.nombre = nombre;
        this.region = region;
        this.textura = textura;
        this.sonido = sonido;
    }

    public void mostrar(int x, int y) {
        System.out.println("Mostrando enemigo " + this.nombre + " en (" + x + ", " + y + ") la región del individuo es " 
            + this.region + " con textura " + this.textura + " y sonido " + this.sonido);
    }
}

// Clase TipoAmigo
class TipoAmigo {
    private String nombre;
    private String region;
    private String textura;
    private String sonido;

    public TipoAmigo(String nombre, String region, String textura, String sonido) {
        this.nombre = nombre;
        this.region = region;
        this.textura = textura;
        this.sonido = sonido;
    }

    public void mostrar(int x, int y) {
        System.out.println("Mostrando amigo " + this.nombre + " en (" + x + ", " + y + ") la región del individuo es " 
            + this.region + " con textura " + this.textura + " y sonido " + this.sonido);
    }
}

// Clase FabricaEnemigos
class FabricaEnemigos {
    private static Map<String, TipoEnemigo> tipos = new HashMap<>();

    public static TipoEnemigo obtenerTipo(String nombre, String region, String textura, String sonido) {
        if (!tipos.containsKey(nombre)) {
            System.out.println("Creando nuevo tipo de enemigo: " + nombre);
            tipos.put(nombre, new TipoEnemigo(nombre, region, textura, sonido));
        }
        return tipos.get(nombre);
    }
}

// Clase FabricaAmigos
class FabricaAmigos {
    private static Map<String, TipoAmigo> tipos = new HashMap<>();

    public static TipoAmigo obtenerTipo(String nombre, String region, String textura, String sonido) {
        if (!tipos.containsKey(nombre)) {
            System.out.println("Creando nuevo tipo de amigo: " + nombre);
            tipos.put(nombre, new TipoAmigo(nombre, region, textura, sonido));
        }
        return tipos.get(nombre);
    }
}

// Clase Enemigo
class Enemigo {
    private TipoEnemigo tipo;
    private int x, y;

    public Enemigo(TipoEnemigo tipo, int x, int y) {
        this.tipo = tipo;
        this.x = x;
        this.y = y;
    }

    public void mostrar() {
        tipo.mostrar(this.x, this.y);
    }
}

// Clase Amigo
class Amigo {
    private TipoAmigo tipo;
    private int x, y;

    public Amigo(TipoAmigo tipo, int x, int y) {
        this.tipo = tipo;
        this.x = x;
        this.y = y;
    }

    public void mostrar() {
        tipo.mostrar(this.x, this.y);
    }
}

public class juego_fliweight {
    public static void main(String[] args) {
        // Creando los tipos de enemigos
        TipoEnemigo tipoChoro = FabricaEnemigos.obtenerTipo("Choro", "Guayakill", "Choro.png", "sonido_choro.mp3");
        TipoEnemigo tipoChulquero = FabricaEnemigos.obtenerTipo("Chulquero", "Ibarra", "Chulquero.jpg", "sonido_moto_chulquero.mp3");
        TipoEnemigo tipoNarco = FabricaEnemigos.obtenerTipo("Narco", "Colombiano", "Narco.png", "sonido_narco.mp3");

        // Creando enemigos con sus tipos y posiciones
        Enemigo[] enemigos = {
            new Enemigo(tipoChoro, 10, 20),
            new Enemigo(tipoChoro, 50, 20),
            new Enemigo(tipoChulquero, 30, 40),
            new Enemigo(tipoChulquero, 0, 0),
            new Enemigo(tipoNarco, 0, 0),
        };

        // Mostrando los enemigos
        for (Enemigo enemigo : enemigos) {
            enemigo.mostrar();
        }

        // Creando los tipos de amigos
        TipoAmigo tipoChapa = FabricaAmigos.obtenerTipo("Chapa", "Latacunga", "Chapa.png", "sonido_chapa.mp3");
        TipoAmigo tipoMilico = FabricaAmigos.obtenerTipo("Milico", "Latacunga", "Milico.png", "sonido_milico.mp3");
        TipoAmigo tipoGuardia = FabricaAmigos.obtenerTipo("Guardia", "Latacunga", "Guardia.png", "sonido_guardia.mp3");

        // Creando amigos con sus tipos y posiciones
        Amigo[] amigos = {
            new Amigo(tipoChapa, 10, 20),
            new Amigo(tipoChapa, 50, 20),
            new Amigo(tipoMilico, 30, 40),
            new Amigo(tipoGuardia, 0, 0),
        };

        // Mostrando los amigos
        for (Amigo amigo : amigos) {
            amigo.mostrar();
        }
    }
}
