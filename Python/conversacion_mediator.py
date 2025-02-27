from abc import ABC, abstractmethod

class Mediator(ABC):
    @abstractmethod
    def send(self, message, sender):
        pass

class JokeRoom(Mediator):  # Mediador que gestiona los chistes
    def __init__(self):
        self.users = []

    def add_user(self, user):
        self.users.append(user)

    def send(self, message, sender):
        for user in self.users:
            if user != sender:
                user.receive(message)

class User:
    def __init__(self, name, mediator):
        self.name = name
        self.mediator = mediator
        self.mediator.add_user(self)

    def send(self, message):
        self.mediator.send(message, self)
    
    def receive(self, message):
        print(f"{self.name} recibe: {message}")

# Creando el mediador y los usuarios
joke_room = JokeRoom()
user1 = User("Juan", joke_room)
user2 = User("Pedro", joke_room)

# Enviando chistes
user1.send("¿Por qué el libro de matemáticas se deprimió? Porque tenía demasiados problemas.")
user2.send("¿Por qué los pájaros no usan Facebook? Porque ya tienen Twitter.")
