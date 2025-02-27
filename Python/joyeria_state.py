from abc import ABC, abstractmethod

class State(ABC):
    @abstractmethod
    def select_item(self):
        pass

    @abstractmethod
    def add_to_cart(self):
        pass

    @abstractmethod
    def make_payment(self):
        pass

class SinSeleccionar(State):
    def select_item(self):
        print("Joya o reloj seleccionado")
        return Seleccionado()

    def add_to_cart(self):
        print("Selecciona un artículo antes de agregarlo al carrito")
        return self

    def make_payment(self):
        print("No puedes realizar el pago sin seleccionar un artículo")
        return self

class Seleccionado(State):
    def select_item(self):
        print("Ya has seleccionado un artículo")
        return self

    def add_to_cart(self):
        print("Artículo añadido al carrito")
        return CarritoConContenido()

    def make_payment(self):
        print("Debes añadir el artículo al carrito antes de pagar")
        return self

class CarritoConContenido(State):
    def select_item(self):
        print("Ya has agregado un artículo al carrito")
        return self

    def add_to_cart(self):
        print("Ya tienes artículos en el carrito")
        return self

    def make_payment(self):
        print("Pago realizado con éxito")
        return PagoRealizado()

class PagoRealizado(State):
    def select_item(self):
        print("El pago ya ha sido realizado, no puedes seleccionar más artículos")
        return self

    def add_to_cart(self):
        print("El pago ya ha sido realizado, no puedes agregar más artículos")
        return self

    def make_payment(self):
        print("Ya se ha realizado el pago")
        return self

class Store:
    def __init__(self):
        self.state = SinSeleccionar()  # El cliente empieza sin seleccionar nada

    def select_item(self):
        self.state = self.state.select_item()

    def add_to_cart(self):
        self.state = self.state.add_to_cart()

    def make_payment(self):
        self.state = self.state.make_payment()

# Creando una instancia de la tienda
store = Store()

# Simulando las acciones del cliente
store.select_item()  # Selecciona un artículo
store.add_to_cart()  # Añade al carrito
store.make_payment()  # Realiza el pago
store.select_item()  # Intentar seleccionar un artículo después de pagar
store.add_to_cart()  # Intentar agregar algo al carrito después de pagar
