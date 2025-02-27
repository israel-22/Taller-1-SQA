package Aeronave_prototype;


// Clase base abstracta para aeronaves militares
abstract class Aeronave implements Cloneable {
    protected String nacion;
    protected String tipo;

    public Aeronave(String nacion, String tipo) {
        this.nacion = nacion;
        this.tipo = tipo;
    }

    @Override
    public Aeronave clone() {
        try {
            return (Aeronave) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar el objeto Aeronave", e);
        }
    }

    @Override
    public String toString() {
        return "Nación: " + nacion + ", Tipo: " + tipo;
    }
}

// Clase concreta para modelos de aeronaves
class Modelo extends Aeronave {
    private String modelo;
    private String funcion;

    public Modelo(String nacion, String tipo, String modelo, String funcion) {
        super(nacion, tipo);
        this.modelo = modelo;
        this.funcion = funcion;
    }

    @Override
    public Modelo clone() {
        return (Modelo) super.clone();
    }

    @Override
    public String toString() {
        return super.toString() + ", Modelo: " + modelo + ", Función: " + funcion;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }
}

// Clase concreta para especificaciones técnicas
class Especificaciones extends Aeronave {
    private int velocidad;
    private String armamento;
    private int capacidad;

    public Especificaciones(String nacion, String tipo, int velocidad, String armamento, int capacidad) {
        super(nacion, tipo);
        this.velocidad = velocidad;
        this.armamento = armamento;
        this.capacidad = capacidad;
    }

    @Override
    public Especificaciones clone() {
        return (Especificaciones) super.clone();
    }

    @Override
    public String toString() {
        return super.toString() + ", Velocidad: " + velocidad + " km/h, Armamento: " + armamento + ", Capacidad: " + capacidad + " personas";
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public void setArmamento(String armamento) {
        this.armamento = armamento;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}

// Clase principal
public class Main {
    public static void main(String[] args) {
        Modelo avionPrototipo = new Modelo("EE.UU.", "Avión de combate", "F-22 Raptor", "Superioridad aérea");
        Modelo helicopteroPrototipo = new Modelo("Rusia", "Helicóptero de combate", "Ka-52 Alligator", "Ataque y reconocimiento");

        Modelo avion1 = avionPrototipo.clone();
        avion1.setModelo("F-35 Lightning II");
        avion1.setFuncion("Ataque multirrol");

        Modelo helicoptero1 = helicopteroPrototipo.clone();
        helicoptero1.setModelo("Mi-28 Havoc");
        helicoptero1.setFuncion("Apoyo terrestre");

        Especificaciones especificacionesAvion = new Especificaciones("EE.UU.", "Avión de combate", 2410, "Misiles AIM-120", 1);
        Especificaciones especificacionesHelicoptero = new Especificaciones("Rusia", "Helicóptero de combate", 320, "Cañón 30mm, cohetes", 2);

        Especificaciones especificacionesAvion1 = especificacionesAvion.clone();
        especificacionesAvion1.setVelocidad(1900);
        especificacionesAvion1.setArmamento("Misiles AIM-9, bombas guiadas");

        Especificaciones especificacionesHelicoptero1 = especificacionesHelicoptero.clone();
        especificacionesHelicoptero1.setCapacidad(3);

        System.out.println("------------Aeronaves----------------\n");
        System.out.println(avion1);
        System.out.println(helicoptero1);
        System.out.println("\n-------------Características---------------\n");
        System.out.println(especificacionesAvion1);
        System.out.println(especificacionesHelicoptero1);
        System.out.println("\n--------------------Fin del programa----------------\n");
    }
}
