package com.registrocivil_adapter;
import java.io.*;
import java.nio.file.*;
import org.json.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;



public class registrocivil_adapter {
    // Leer y escribir JSON
    public static JSONObject readJson(String path) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)), "UTF-8");
            return new JSONObject(content);
        } catch (IOException e) {
            System.out.println("Error: El archivo JSON no pudo ser leído.");
            return null;
        }
    }

    public static void writeJson(String path, JSONObject data) {
        try (FileWriter file = new FileWriter(path)) {
            file.write(data.toString(4)); // Indentación de 4 espacios para legibilidad
        } catch (IOException e) {
            System.out.println("Error: El archivo JSON no pudo ser escrito.");
        }
    }

    // Leer y escribir XML
    public static Document readXml(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                System.out.println("Error: El archivo " + path + " no existe.");
                return null;
            }
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(file);
        } catch (Exception e) {
            System.out.println("Error: El archivo XML no está bien formado.");
            return null;
        }
    }

    public static void writeXml(String path, Document document) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(path));
            transformer.transform(source, result);
        } catch (Exception e) {
            System.out.println("Error: El archivo XML no pudo ser escrito.");
        }
    }

    // Convertir Document XML a un objeto de tipo JSONObject (equivalente a la conversión XML a diccionario en Python)
    public static JSONObject xmlToJson(Document document) {
        JSONObject json = new JSONObject();
        Element root = document.getDocumentElement();
        json.put(root.getTagName(), getElementValue(root));
        return json;
    }

    private static JSONObject getElementValue(Element element) {
        JSONObject result = new JSONObject();
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                result.put(node.getNodeName(), node.getTextContent());
            }
        }
        return result;
    }

    // Clase principal para ejecutar el programa
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Ingrese los datos del ciudadano:");

            // Solicitar datos al usuario
            System.out.print("Nombre del ciudadano: ");
            String nombre = br.readLine().trim();

            System.out.print("Cédula de identidad: ");
            String cedula = br.readLine().trim();
            while (!cedula.matches("\\d+")) {
                System.out.println("Error: La cédula debe contener solo números.");
                cedula = br.readLine().trim();
            }

            System.out.print("Edad: ");
            String edad = br.readLine().trim();
            while (!edad.matches("\\d+")) {
                System.out.println("Error: La edad debe ser un número válido.");
                edad = br.readLine().trim();
            }

            System.out.print("Ciudad: ");
            String ciudad = br.readLine().trim();

            // Crear JSONObject con los datos ingresados
            JSONObject data = new JSONObject();
            data.put("nombre", nombre);
            data.put("cedula", cedula);
            data.put("edad", Integer.parseInt(edad));
            data.put("ciudad", ciudad);

            System.out.println("\n---------- Información de los ciudadanos ---------------\n");

            // Guardar y leer datos en JSON
            writeJson("data.json", data);
            System.out.println("Datos en JSON guardados correctamente.");
            System.out.println("JSON leído: " + readJson("data.json").toString(4));

            // Guardar y leer datos en XML
            Document xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = xmlDocument.createElement("ciudadano");
            xmlDocument.appendChild(root);
            root.appendChild(xmlDocument.createElement("nombre")).appendChild(xmlDocument.createTextNode(nombre));
            root.appendChild(xmlDocument.createElement("cedula")).appendChild(xmlDocument.createTextNode(cedula));
            root.appendChild(xmlDocument.createElement("edad")).appendChild(xmlDocument.createTextNode(edad));
            root.appendChild(xmlDocument.createElement("ciudad")).appendChild(xmlDocument.createTextNode(ciudad));

            writeXml("data.xml", xmlDocument);
            System.out.println("\nDatos en XML guardados correctamente.");
            Document xmlData = readXml("data.xml");

            if (xmlData != null) {
                JSONObject xmlJson = xmlToJson(xmlData);
                System.out.println("XML leído: " + xmlJson.toString(4));
            } else {
                System.out.println("No se pudo leer el XML debido a un error.");
            }

            System.out.println("\n----------------- Fin del programa --------------");

        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println("Error: Ocurrió un problema al leer o escribir archivos.");
        }
    }
}

