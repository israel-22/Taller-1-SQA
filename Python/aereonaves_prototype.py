import copy
from abc import ABC, abstractmethod

# Clase base abstracta para aeronaves militares lo adapto dee los ejemplos del profe
class Aereonave(ABC):
    def __init__(self, nacion, tipo):
        self.nacion = nacion  #  rusao o gringo
        self.tipo = tipo  # modelo de nave avion o elicoptero

    @abstractmethod
    def clone(self):
        pass

    def __str__(self):
        return f"Nación: {self.nacion}, Tipo: {self.tipo}"


# Clase concreta para modelos de aeronaves
class Modelo(Aereonave):
    def __init__(self, nacion, tipo, modelo, funcion):
        super().__init__(nacion, tipo)
        self.modelo = modelo  # Nombre del modelo de la nave
        self.funcion = funcion  # Función principal del avion

    def clone(self):
        return copy.deepcopy(self)

    def __str__(self):
        return super().__str__() + f", Modelo: {self.modelo}, Función: {self.funcion}"


# Clase concreta para especificaciones técnicas
class Especificaciones(Aereonave):
    def __init__(self, nacion, tipo, velocidad, armamento, capacidad):
        super().__init__(nacion, tipo)
        self.velocidad = velocidad  # Velocidad máxima 
        self.armamento = armamento  # Tipo de armas 
        self.capacidad = capacidad  # Capacidad de carga de lo que sea

    def clone(self):
        return copy.deepcopy(self)

    def __str__(self):
        return super().__str__() + f", Velocidad: {self.velocidad} km/h, Armamento: {self.armamento}, Capacidad: {self.capacidad} personas"


# Prototipos base de aeronaves
avion_prototipo = Modelo("EE.UU.", "Avión de combate", "F-22 Raptor", "Superioridad aérea")
helicoptero_prototipo = Modelo("Rusia", "Helicóptero de combate", "Ka-52 Alligator", "Ataque y reconocimiento")

# Clonando aeronaves
avion1 = avion_prototipo.clone()
avion1.modelo = "F-35 Lightning II"
avion1.funcion = "Ataque multirrol"

helicoptero1 = helicoptero_prototipo.clone()
helicoptero1.modelo = "Mi-28 Havoc"
helicoptero1.funcion = "Apoyo terrestre"

# Prototipos de especificaciones técnicas
especificaciones_avion = Especificaciones("EE.UU.", "Avión de combate", 2410, "Misiles AIM-120", 1)
especificaciones_helicoptero = Especificaciones("Rusia", "Helicóptero de combate", 320, "Cañón 30mm, cohetes", 2)

# Clonando especificaciones
especificaciones_avion1 = especificaciones_avion.clone()
especificaciones_avion1.velocidad = 1900
especificaciones_avion1.armamento = "Misiles AIM-9, bombas guiadas"

especificaciones_helicoptero1 = especificaciones_helicoptero.clone()
especificaciones_helicoptero1.capacidad = 3

# Mostrando resultados creo que iva asi,
print("------------aereonaves----------------")
print("") 
print(avion1)
print(helicoptero1)
print("")
print ("-------------caracteristicas---------------")
print("")
print(especificaciones_avion1)
print(especificaciones_helicoptero1)
print("")
print("--------------------fin del preograma----------------")
print("")
