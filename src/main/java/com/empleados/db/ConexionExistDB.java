package com.empleados.db;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

public class ConexionExistDB {

    private static final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private static final String COLECCION = "/db/empleados";
    private static final String USUARIO = "admin";
    private static final String PASSWORD = "admin";

    public Collection conectar() {
        try {
            Class<?> cl = Class.forName("org.exist.xmldb.DatabaseImpl");
            Database database = (Database) cl.getDeclaredConstructor().newInstance();
            DatabaseManager.registerDatabase(database);

            return DatabaseManager.getCollection(URI + COLECCION, USUARIO, PASSWORD);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void subirXML(String nombre, String rutaLocal) {
        try {
            Collection col = conectar();
            if (col == null) return;

            XMLResource res = (XMLResource) col.createResource(nombre, "XMLResource");
            res.setContent(new java.io.File(rutaLocal));
            col.storeResource(res);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ejecutarConsulta(String consulta) {
        try {
            Collection col = conectar();
            if (col == null) return;

            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet resultado = servicio.query(consulta);

            ResourceIterator it = resultado.getIterator();
            while (it.hasMoreResources()) {
                Resource r = it.nextResource();
                System.out.println(r.getContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}