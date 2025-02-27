package com.joyeria_state;

// Interfaz State
interface State {
    State selectItem();
    State addToCart();
    State makePayment();
}

// Clase SinSeleccionar: el cliente aún no ha seleccionado un artículo
class SinSeleccionar implements State {
    @Override
    public State selectItem() {
        System.out.println("Joya o reloj seleccionado");
        return new Seleccionado();
    }

    @Override
    public State addToCart() {
        System.out.println("Selecciona un artículo antes de agregarlo al carrito");
        return this;
    }

    @Override
    public State makePayment() {
        System.out.println("No puedes realizar el pago sin seleccionar un artículo");
        return this;
    }
}

// Clase Seleccionado: el cliente ha seleccionado un artículo
class Seleccionado implements State {
    @Override
    public State selectItem() {
        System.out.println("Ya has seleccionado un artículo");
        return this;
    }

    @Override
    public State addToCart() {
        System.out.println("Artículo añadido al carrito");
        return new CarritoConContenido();
    }

    @Override
    public State makePayment() {
        System.out.println("Debes añadir el artículo al carrito antes de pagar");
        return this;
    }
}

// Clase CarritoConContenido: el cliente ha añadido el artículo al carrito
class CarritoConContenido implements State {
    @Override
    public State selectItem() {
        System.out.println("Ya has agregado un artículo al carrito");
        return this;
    }

    @Override
    public State addToCart() {
        System.out.println("Ya tienes artículos en el carrito");
        return this;
    }

    @Override
    public State makePayment() {
        System.out.println("Pago realizado con éxito");
        return new PagoRealizado();
    }
}

// Clase PagoRealizado: el cliente ha completado el pago
class PagoRealizado implements State {
    @Override
    public State selectItem() {
        System.out.println("El pago ya ha sido realizado, no puedes seleccionar más artículos");
        return this;
    }

    @Override
    public State addToCart() {
        System.out.println("El pago ya ha sido realizado, no puedes agregar más artículos");
        return this;
    }

    @Override
    public State makePayment() {
        System.out.println("Ya se ha realizado el pago");
        return this;
    }
}

// Clase Store: gestiona el estado del cliente
class Store {
    private State state;

    public Store() {
        this.state = new SinSeleccionar();  // El cliente empieza sin seleccionar nada
    }

    public void selectItem() {
        state = state.selectItem();
    }

    public void addToCart() {
        state = state.addToCart();
    }

    public void makePayment() {
        state = state.makePayment();
    }
}

// Clase principal para ejecutar el código
public class JoyeriaState {
    public static void main(String[] args) {
        // Creando una instancia de la tienda
        Store store = new Store();

        // Simulando las acciones del cliente
        store.selectItem();  // Selecciona un artículo
        store.addToCart();  // Añade al carrito
        store.makePayment();  // Realiza el pago
        store.selectItem();  // Intentar seleccionar un artículo después de pagar
        store.addToCart();  // Intentar agregar algo al carrito después de pagar
    }
}
