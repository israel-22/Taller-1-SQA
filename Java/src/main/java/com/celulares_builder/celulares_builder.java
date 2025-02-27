package com.celulares_builder;

import java.util.ArrayList;
import java.util.List;

// Clase Item que representa un artículo (como el modelo de celular, color, etc.)
class Item {
    private String name;
    private double cost;

    public Item(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return name + ": " + cost;
    }
}

// Clase Celular que representa el objeto que se está construyendo
class Celular {
    private List<Item> items = new ArrayList<>();

    public void add(Item item) {
        items.add(item);
    }

    public double getTotalCost() {
        double total = 0;
        for (Item item : items) {
            total += item.getCost();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder("Celular:\n");
        for (Item item : items) {
            text.append(item).append("\n");
        }
        text.append("Total cost: ").append(String.format("%.2f", getTotalCost()));
        return text.toString();
    }
}

// Clase abstracta CelularBuilder para construir diferentes tipos de celulares
abstract class CelularBuilder {
    protected Celular celular = new Celular();

    public abstract void addMainItem();
    public abstract void addGamaItem();
    public abstract void addModeloItem();
    public abstract void addColorItem();

    public Celular getCelular() {
        return celular;
    }

    public void reset() {
        celular = new Celular();
    }
}

// Implementación concreta del builder para un celular de gama alta
class StandardCelularBuilder extends CelularBuilder {
    @Override
    public void addMainItem() {
        celular.add(new Item("Marca Iphone", 600));
    }

    @Override
    public void addGamaItem() {
        celular.add(new Item(" Gama Alta", 300));
    }

    @Override
    public void addModeloItem() {
        celular.add(new Item("Generación 14", 200));
    }

    @Override
    public void addColorItem() {
        celular.add(new Item("Color blanco", 300));
    }
}

// Implementación concreta del builder para un celular de calidad precio
class CalidadPrecioBuilder extends CelularBuilder {
    @Override
    public void addMainItem() {
        celular.add(new Item("Marca Alcatel", 68.95));
    }

    @Override
    public void addGamaItem() {
        celular.add(new Item(" Gama Media", 25.76));
    }

    @Override
    public void addModeloItem() {
        celular.add(new Item("Modelo Pixy 4", 19.99));
    }

    @Override
    public void addColorItem() {
        celular.add(new Item("Color Rojo Nacarado", 2.99));
    }
}

// Clase CelularDirector que utiliza el builder para construir el celular
class CelularDirector {
    private CelularBuilder builder;

    public CelularDirector(CelularBuilder builder) {
        this.builder = builder;
    }

    public void constructCelular() {
        builder.reset();
        builder.addMainItem();
        builder.addGamaItem();
        builder.addModeloItem();
        builder.addColorItem();
    }
}

public class celulares_builder {
    public static void main(String[] args) {
        // Listas para almacenar los celulares construidos
        List<Celular> gamaAltaCelulares = new ArrayList<>();
        List<Celular> calidadPrecioCelulares = new ArrayList<>();

        // Construcción de celulares de gama alta
        StandardCelularBuilder standardBuilder = new StandardCelularBuilder();
        CelularDirector standardDirector = new CelularDirector(standardBuilder);

        for (int i = 0; i < 3; i++) {
            standardDirector.constructCelular();
            gamaAltaCelulares.add(standardBuilder.getCelular());
        }

        // Construcción de celulares de calidad precio
        CalidadPrecioBuilder calidadPrecioBuilder = new CalidadPrecioBuilder();
        CelularDirector calidadPrecioDirector = new CelularDirector(calidadPrecioBuilder);

        for (int i = 0; i < 3; i++) {
            calidadPrecioDirector.constructCelular();
            calidadPrecioCelulares.add(calidadPrecioBuilder.getCelular());
        }

        // Imprimir la lista de celulares de gama alta
        System.out.println("\n=== Celulares de Gama Alta ===");
        for (Celular celular : gamaAltaCelulares) {
            System.out.println(celular);
            System.out.println("-".repeat(30));
        }

        // Imprimir la lista de celulares de calidad precio
        System.out.println("\n=== Celulares de Calidad Precio ===");
        for (Celular celular : calidadPrecioCelulares) {
            System.out.println(celular);
            System.out.println("-".repeat(30));
        }
    }
}