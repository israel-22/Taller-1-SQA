package com.conversacion_mediator;

import java.util.ArrayList;
import java.util.List;

public class ConversacionMediator {

    // Interfaz Mediator
    interface Mediator {
        void send(String message, User sender);
    }

    // Implementación del Mediador
    static class JokeRoom implements Mediator {  // Mediador que gestiona los chistes
        private List<User> users;

        public JokeRoom() {
            users = new ArrayList<>();
        }

        // Método addUser solo en JokeRoom
        public void addUser(User user) {
            users.add(user);
        }

        @Override
        public void send(String message, User sender) {
            for (User user : users) {
                if (user != sender) {
                    user.receive(message);
                }
            }
        }
    }

    // Clase Usuario
    static class User {
        private String name;
        private Mediator mediator;

        public User(String name, Mediator mediator) {
            this.name = name;
            this.mediator = mediator;
        }

        public void send(String message) {
            this.mediator.send(message, this);
        }

        public void receive(String message) {
            System.out.println(name + " recibe: " + message);
        }
    }

    // Clase principal para ejecutar el código
    public static void main(String[] args) {
        // Creando el mediador y los usuarios
        Mediator jokeRoom = new JokeRoom();
        User user1 = new User("Juan", jokeRoom);
        User user2 = new User("Pedro", jokeRoom);

        // Agregar los usuarios al mediador después de crearlos
        if (jokeRoom instanceof JokeRoom) {
            ((JokeRoom) jokeRoom).addUser(user1);
            ((JokeRoom) jokeRoom).addUser(user2);
        }

        // Enviando chistes
        user1.send("¿Por qué el libro de matemáticas se deprimió? Porque tenía demasiados problemas.");
        user2.send("¿Por qué los pájaros no usan Facebook? Porque ya tienen Twitter.");
    }
}

