package com.empleados.db;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.empleados.model.Empleado;

public class GestorXML {

    private static final String RUTA_XML = "src/main/resources/empleados.xml";

    public List<Empleado> leerEmpleados() {
        List<Empleado> lista = new ArrayList<>();

        try {
            File archivo = new File(RUTA_XML);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(archivo);

            NodeList nodos = doc.getElementsByTagName("empleado");

            for (int i = 0; i < nodos.getLength(); i++) {
                Element emp = (Element) nodos.item(i);

                int id = Integer.parseInt(emp.getAttribute("id"));
                String nombre = emp.getElementsByTagName("nombre").item(0).getTextContent();
                String cargo = emp.getElementsByTagName("cargo").item(0).getTextContent();
                int salario = Integer.parseInt(emp.getElementsByTagName("salario").item(0).getTextContent());

                lista.add(new Empleado(id, nombre, cargo, salario));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void agregarEmpleado(Empleado nuevo) {
        try {
            File archivo = new File(RUTA_XML);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(archivo);

            Element root = doc.getDocumentElement();

            Element emp = doc.createElement("empleado");
            emp.setAttribute("id", String.valueOf(nuevo.getId()));

            Element nombre = doc.createElement("nombre");
            nombre.setTextContent(nuevo.getNombre());
            emp.appendChild(nombre);

            Element cargo = doc.createElement("cargo");
            cargo.setTextContent(nuevo.getCargo());
            emp.appendChild(cargo);

            Element salario = doc.createElement("salario");
            salario.setTextContent(String.valueOf(nuevo.getSalario()));
            emp.appendChild(salario);

            root.appendChild(emp);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(archivo);
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}