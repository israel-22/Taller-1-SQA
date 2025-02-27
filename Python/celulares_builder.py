class Celular:
    def __init__(self):
        self.items = []
        
    def add(self, item):
        self.items.append(item)
    
    def get_total_cost(self):
        return sum(item.get_cost() for item in self.items)
    
    def __str__(self):
        text = "Celular:\n" 
        text += "\n".join(item.name for item in self.items)
        text += f"\nTotal cost: {self.get_total_cost():.2f}"
        return text       
        
class Item:
    def __init__(self, name, cost):
        self.name = name
        self.cost = cost
                
    def get_cost(self):
        return self.cost  
        
    def __str__(self):
        return f"{self.name}: {self.cost}"

class CelularBuilder:
    def __init__(self):
        self.meal = Celular()
    
    def add_main_item(self):
        pass
    
    def add_gama_item(self):
        pass
    
    def add_modelo_item(self):
        pass
    
    def add_color_item(self):
        pass
    
    def reset(self):
        self.meal = Celular()
    
    def get_meal(self):
        return self.meal

class StandarCelularBuilder(CelularBuilder):
    def add_main_item(self):
        self.meal.add(Item("Marca Iphone", 600))
        
    def add_gama_item(self):
        self.meal.add(Item(" Gama Alta", 300))
        
    def add_modelo_item(self):        
        self.meal.add(Item("generacion 14", 200))

    def add_color_item(self):
        self.meal.add(Item("Color blanco", 300))

class CalidadPrecioBuilder(CelularBuilder):
    def add_main_item(self):
        self.meal.add(Item("Marca Alcatel", 68.95))
        
    def add_gama_item(self):
        self.meal.add(Item(" Gama Media", 25.76))
        
    def add_modelo_item(self):        
        self.meal.add(Item("Modelo Pixy 4", 19.99))
    
    def add_color_item(self):
        self.meal.add(Item("Color Rojo Nacarado", 2.99))

class CelularDirector:
    def __init__(self, builder):
        self.builder = builder
        
    def construct_celular(self):
        self.builder.reset()
        self.builder.add_main_item()
        self.builder.add_gama_item()
        self.builder.add_modelo_item()
        self.builder.add_color_item()

# **Listas para almacenar los celulares construidos**
gama_alta_celulares = []
calidad_precio_celulares = []

# **Construcción de celulares de gama alta**
standard_builder = StandarCelularBuilder()
standard_director = CelularDirector(standard_builder)

for i in range(3):  # Crear 3 celulares de gama alta
    standard_director.construct_celular()
    gama_alta_celulares.append(standard_builder.get_meal())  # Cambiado a get_meal()

# **Construcción de celulares de calidad precio**
calidad_precio_builder = CalidadPrecioBuilder()
calidad_precio_director = CelularDirector(calidad_precio_builder)

for i in range(3):  # Crear 3 celulares de calidad precio
    calidad_precio_director.construct_celular()
    calidad_precio_celulares.append(calidad_precio_builder.get_meal())  # Cambiado a get_meal()

# **Imprimir la lista de celulares de gama alta**
print("\n=== Celulares de Gama Alta ===")
for celular in gama_alta_celulares:
    print(celular)
    print("-" * 30)

# **Imprimir la lista de celulares de calidad precio**
print("\n=== Celulares de Calidad Precio ===")
for celular in calidad_precio_celulares:
    print(celular)
    print("-" * 30)
