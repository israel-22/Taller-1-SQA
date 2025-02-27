from collections.abc import Iterator, Iterable

class Pelicula:
    def __init__(self, titulo, anio):
        self.titulo = titulo
        self.anio = anio

    def __str__(self):
        return f"{self.titulo} ({self.anio})"
    

class PeliculaIterator(Iterator):  
    def __init__(self, peliculas):
        self.peliculas = peliculas
        self.index = 0

    def __next__(self):
        if self.index >= len(self.peliculas):
            raise StopIteration()
        pelicula = self.peliculas[self.index]
        self.index += 1
        return pelicula
   

class Cine(Iterable):
    def __init__(self):
        self.peliculas = []

    def agregar_pelicula(self, pelicula):
        self.peliculas.append(pelicula)
    
    def __iter__(self):
        return PeliculaIterator(self.peliculas)


# Creación de la colección de películas del UCM
cine = Cine()
cine.agregar_pelicula(Pelicula("Iron Man", 2008))
cine.agregar_pelicula(Pelicula("Capitán América: El primer vengador", 2011))
cine.agregar_pelicula(Pelicula("Los Vengadores", 2012))
cine.agregar_pelicula(Pelicula("Guardians of the Galaxy", 2014))
cine.agregar_pelicula(Pelicula("Avengers: Endgame", 2019))

# Iterando sobre las películas y mostrando la lista
for pelicula in cine:
    print(pelicula)
