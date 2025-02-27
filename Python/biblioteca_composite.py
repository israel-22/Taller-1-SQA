from abc import ABC, abstractmethod

class ComponenteBiblioteca(ABC):
    @abstractmethod
    def mostrar(self, nivel =0):
        pass
    
class Archivo(ComponenteBiblioteca):
    def __init__(self, nombre):
        self._nombre = nombre
        
    def mostrar(self, nivel =0):
        print(" " * nivel + f"- {self._nombre}")
        
class Carpeta(ComponenteBiblioteca):
    def __init__(self, nombre):
        self._nombre = nombre
        self._componentes = []
        
    def agregar(self, componente):
        self._componentes.append(componente)
        
    def mostrar(self, nivel =0):
        print(" " * nivel + f"+ [{self._nombre}]")
        for componente in self._componentes:
            componente.mostrar(nivel + 1)
            
archivo1= Archivo("documentos.txt")
archivo2= Archivo("imagenes.jpg")
archivo3= Archivo("video.mp4")
archivo4= Archivo("Libro.pdf")
archivo5= Archivo("Autor.txt")
archivo6= Archivo("Editorial.txt")
archivo7= Archivo("Año de publicacion.txt")

carpeta1= Carpeta("Mis Documentos")
carpeta1.agregar(archivo1)
carpeta1.agregar(archivo2)

carpeta2= Carpeta("Videos")
carpeta2.agregar(archivo3)

carpeta3= Carpeta ("Informacion")
carpeta3.agregar(archivo4)
carpeta3.agregar(archivo5)
carpeta3.agregar(archivo6)
carpeta3.agregar(archivo7)

carpeta_principal = Carpeta ("/")
carpeta_principal.agregar(carpeta1)
carpeta_principal.agregar(carpeta2)
carpeta_principal.agregar(carpeta3)

carpeta_principal.mostrar() 
        