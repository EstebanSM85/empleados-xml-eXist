package com.empleados.db;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class ValidadorXML {

    private static final String RUTA_XML = "src/main/resources/empleados.xml";
    private static final String RUTA_XSD = "src/main/resources/schemas/empleado.xsd";

    public boolean validar() {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(RUTA_XSD));
            Validator validator = schema.newValidator();

            validator.validate(new StreamSource(new File(RUTA_XML)));

            return true;

        } catch (Exception e) {
            System.out.println("Error de validación: " + e.getMessage());
            return false;
        }
    }
}