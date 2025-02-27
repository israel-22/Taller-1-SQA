import json
import xml.etree.ElementTree as ET
import os  # Importamos para verificar la existencia del archivo

class JSONHandler:
    def read_json(self, path):
        with open(path, "r", encoding="utf-8") as f:
            data = json.load(f)
        return data
    
    def write_json(self, path, data):
        with open(path, "w", encoding="utf-8") as f:
            json.dump(data, f, indent=4, ensure_ascii=False)  # Se asegura de manejar caracteres especiales

class XMLHandler:
    def _xml_to_dict(self, root):
        """ Convierte un XML a diccionario manejando múltiples hijos con el mismo nombre """
        result = {root.tag: {}}
        for child in root:
            if child.tag in result[root.tag]:  
                if isinstance(result[root.tag][child.tag], list):
                    result[root.tag][child.tag].append(child.text)
                else:
                    result[root.tag][child.tag] = [result[root.tag][child.tag], child.text]
            else:
                result[root.tag][child.tag] = child.text
        return result
    
    def _dict_to_xml(self, tag, data):
        """ Convierte un diccionario a XML, asegurando que los valores sean cadenas de texto """
        root = ET.Element(tag)
        for key, value in data.items():
            child = ET.SubElement(root, key)
            child.text = str(value) if value is not None else ""
        return root

    def read_xml(self, path):
        """ Verifica si el archivo existe antes de intentar leerlo """
        if not os.path.exists(path):
            print(f"Error: El archivo {path} no existe.")
            return None
        try:
            tree = ET.parse(path)
            root = tree.getroot()
            return self._xml_to_dict(root)
        except ET.ParseError:
            print(f"Error: El archivo {path} no está bien formado.")
            return None
    
    def write_xml(self, path, data):
        """ Escribe datos en XML asegurando que esté bien formado """
        root = self._dict_to_xml("ciudadano", data)
        tree = ET.ElementTree(root)
        tree.write(path, encoding="utf-8", xml_declaration=True)

class FileAdapter:
    def __init__(self, handler, type):
        self.handler = handler
        self.type = type

    def read(self, path):
        if self.type == "json":
            return self.handler.read_json(path)
        elif self.type == "xml":
            return self.handler.read_xml(path)
        else:
            raise ValueError("Formato no soportado")
        
    def write(self, path, data):
        if self.type == "json":
            self.handler.write_json(path, data)
        elif self.type == "xml":
            self.handler.write_xml(path, data)
        else:
            raise ValueError("Formato no soportado")

# Solicitar datos al usuario desde la consola
print("Ingrese los datos del ciudadano:")

nombre = input("Nombre del ciudadano: ").strip()
cedula = input("Cédula de identidad: ").strip()
while not cedula.isdigit():
    print("Error: La cédula debe contener solo números.")
    cedula = input("Cédula de identidad: ").strip()

edad = input("Edad: ").strip()
while not edad.isdigit():
    print("Error: La edad debe ser un número válido.")
    edad = input("Edad: ").strip()

ciudad = input("Ciudad: ").strip()

# Crear diccionario con los datos ingresados
data = {
    "nombre": nombre,
    "cedula": cedula,
    "edad": int(edad),
    "ciudad": ciudad
}

print("\n---------- Información de los ciudadanos ---------------\n")

# Guardar y leer datos en JSON
json_adapter = FileAdapter(JSONHandler(), "json")
json_adapter.write("data.json", data)
print("Datos en JSON guardados correctamente.")
print("JSON leído:", json_adapter.read("data.json"))

# Guardar y leer datos en XML
xml_adapter = FileAdapter(XMLHandler(), "xml")
xml_adapter.write("data.xml", data)
print("\nDatos en XML guardados correctamente.")
xml_data = xml_adapter.read("data.xml")

if xml_data:
    print("XML leído:", xml_data)
else:
    print("No se pudo leer el XML debido a un error.")

print("\n----------------- Fin del programa --------------")
