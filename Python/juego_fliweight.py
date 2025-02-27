#policias y ladrones
class TipoEnemigo:
    def __init__(self, nombre,region, textura, sonido):
        self._nombre = nombre
        self._region = region
        self._textura = textura
        self._sonido = sonido

    def mostrar(self, x, y):
        print(f"Mostrando enemigo {self._nombre} en ({x}, {y}) la region del individuo es {self._region} con textura {self._textura} y sonido {self._sonido}")

class TipoAmigos:
    def __init__(self, nombre,region, textura, sonido):
        self._nombre = nombre
        self._region = region
        self._textura = textura
        self._sonido = sonido

    def mostrar(self, x, y):
        print(f"Mostrando amigo {self._nombre} en ({x}, {y}) la region del individuo es {self._region} con textura {self._textura} y sonido {self._sonido}")

class FabricaEnemigos:
    _tipos = {}
    
class FabricaAmigos:
    _tipos = {}

    @staticmethod
    def obtener_tipo(nombre,region, textura, sonido):
        if nombre not in FabricaEnemigos._tipos:
            print(f"Creando nuevo tipo de enemigo: {nombre}")
            FabricaEnemigos._tipos[nombre] = TipoEnemigo(nombre,region, textura, sonido)
        return FabricaEnemigos._tipos[nombre]
    
    @staticmethod
    def obtener_tipo(nombre,region, textura, sonido):
        if nombre not in FabricaAmigos._tipos:
            print(f"Creando nuevo tipo de enemigo: {nombre}")
            FabricaAmigos._tipos[nombre] = TipoAmigos(nombre,region, textura, sonido)
        return FabricaAmigos._tipos[nombre]

class Enemigo:
    def __init__(self, tipo, x, y):
        self.tipo = tipo
        self.x = x
        self.y = y
class Amigo:
    def __init__(self, tipo, x, y):
        self.tipo = tipo
        self.x = x
        self.y = y

    def mostrar(self):
        self.tipo.mostrar(self.x, self.y)

# Creando los tipos de enemigos
tipo_choro = FabricaEnemigos.obtener_tipo("Choro","Guayakill" "Choro.png", "sonido_choro.mp3")
tipo_chulquero= FabricaEnemigos.obtener_tipo("Chulquero", "Ibarra0, Chulquero.jpg", "sonido_moto_chulquero.mp3")
tipo_narco= FabricaEnemigos.obtener_tipo("Narco", "Colombiano", "Narco.png", "sonido_narco.mp3")


# Creando enemigos con sus tipos y posiciones
enemigos = [
    Enemigo(tipo_choro, 10, 20),
    Enemigo(tipo_choro, 50, 20),
    Enemigo(tipo_chulquero, 30, 40),
    Enemigo(tipo_chulquero, 0, 0),
    Enemigo(tipo_narco, 0, 0),
]

# Mostrando los enemigos
for enemigo in enemigos:
    enemigo.mostrar()


# Creando los tipos de enemigos

tipo_chapa = FabricaAmigos.obtener_tipo("Chapa","Latacunga" "Chapa.png", "sonido_chapa.mp3")
tipo_milico = FabricaAmigos.obtener_tipo("Chapa","Latacunga" "Chapa.png", "sonido_chapa.mp3")
tipo_guardia = FabricaAmigos.obtener_tipo("Chapa","Latacunga" "Chapa.png", "sonido_chapa.mp3")

# Creando enemigos con sus tipos y posiciones
amigos = [
    Amigo(tipo_choro, 10, 20),
    Amigo(tipo_choro, 50, 20),
    Amigo(tipo_chapa, 30, 40),
    Amigo(tipo_chapa, 0, 0),
]

# Mostrando los enemigos
for amigo in amigos:
    amigo.mostrar()