package com.empleados.main;

import java.util.List;

import com.empleados.db.GestorXML;
import com.empleados.db.ValidadorXML;
import com.empleados.db.ConexionExistDB;
import com.empleados.model.Empleado;

public class Main {

    public static void main(String[] args) {

        System.out.println("Validando XML...");
        ValidadorXML validador = new ValidadorXML();

        if (validador.validar()) {
            System.out.println("XML válido.");
        } else {
            System.out.println("XML inválido. Revisa el archivo.");
            return;
        }

        System.out.println("\nLeyendo empleados del XML...");
        GestorXML gestor = new GestorXML();
        List<Empleado> empleados = gestor.leerEmpleados();

        for (Empleado e : empleados) {
            System.out.println(e);
        }

        System.out.println("\nSubiendo XML a eXist-db...");
        ConexionExistDB conexion = new ConexionExistDB();
        conexion.subirXML("empleados.xml", "src/main/resources/empleados.xml");

        System.out.println("\nConsulta XQuery: empleados con salario > 30000");
        String consulta = "for $e in /empleados/empleado[salario > 30000] return $e";
        conexion.ejecutarConsulta(consulta);
        System.out.println("\nConsulta: empleados cuyo nombre contiene 'Ana'");
        conexion.ejecutarConsulta(
            "for $e in /empleados/empleado[contains(nombre, 'Ana')] return $e"
        );

        System.out.println("\nConsulta: empleados con salario > 30000");
        conexion.ejecutarConsulta(
            "for $e in /empleados/empleado[salario > 30000] return $e"
        );

    }
}
