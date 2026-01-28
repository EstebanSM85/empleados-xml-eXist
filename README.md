📦 Sistema de Gestión de Empleados con eXist-db
Sistema de gestión de empleados desarrollado en Java utilizando eXist-db como base de datos XML nativa.
Incluye validación XML, lectura/escritura de documentos, consultas XQuery e indexación para mejorar el rendimiento.

🚀 Características
✅ Gestión de Empleados: Lectura y escritura de empleados en XML
✅ Validación XML: Validación mediante esquema XSD antes de procesar los datos
✅ Conexión con eXist-db: Subida de documentos y consultas XQuery
✅ Índices optimizados: Búsquedas rápidas mediante collection.xconf
✅ Consultas XQuery: Filtrado eficiente por nombre, cargo o salario
✅ Arquitectura limpia: Separación entre modelo, lógica y acceso a datos

🛠️ Tecnologías Utilizadas
- Java 23
- Maven
- eXist-db 6.x
- XML DOM para lectura/escritura
- XQuery para consultas
- Eclipse IDE (opcional)

📋 Requisitos Previos
Antes de ejecutar el proyecto, asegúrate de tener instalado:
- JDK 23 o superior
- Maven 3.6+
- eXist-db 6.x (corriendo en http://localhost:8080/exist)
- Eclipse o cualquier IDE compatible con Maven
Configuración de eXist-db
- Descargar eXist-db desde https://exist-db.org
- Iniciar el servidor
- Acceder a: http://localhost:8080/exist
- Credenciales por defecto:
- Usuario: admin
- Contraseña: admin

📥 Instalación
1. Clonar el repositorio
git clone https://github.com/tu-usuario/empleados-xml-exist.git
cd empleados-xml-exist


2. Compilar el proyecto
mvn clean install


3. Importar en Eclipse (opcional)
- File → Import → Maven → Existing Maven Projects
- Seleccionar la carpeta del proyecto
- Finalizar

▶️ Ejecución
Desde Eclipse
- Abrir Main.java
- Click derecho → Run As → Java Application
Desde terminal
mvn exec:java -Dexec.mainClass="com.empleados.main.Main"



📖 Funcionamiento del Sistema
Al ejecutar la aplicación, se realizan automáticamente:
- Validación del XML
- Lectura de empleados desde el archivo local
- Subida del XML a eXist-db
- Consultas XQuery usando índices
Ejemplo de consultas:
- Empleados con salario > 30000
- Empleados cuyo nombre contiene “Ana”

🏗️ Estructura del Proyecto
empleados-xml-exist/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/empleados/
│   │   │       ├── model/
│   │   │       │   └── Empleado.java
│   │   │       ├── db/
│   │   │       │   ├── ConexionExistDB.java
│   │   │       │   ├── GestorXML.java
│   │   │       │   └── ValidadorXML.java
│   │   │       └── main/
│   │   │           └── Main.java
│   │   └── resources/
│   │       ├── empleados.xml
│   │       └── schemas/
│   │           └── empleado.xsd
│
├── pom.xml
└── README.md



📦 Estructura de Datos XML
Ejemplo de empleado:
<empleado id="1">
    <nombre>Ana López</nombre>
    <cargo>Desarrolladora</cargo>
    <salario>28000</salario>
</empleado>


Campos del Empleado
|  |  |  | 
|  |  |  | 
|  |  |  | 
|  |  |  | 
|  |  |  | 



🔧 Configuración
Cambiar URL de eXist-db
En ConexionExistDB.java:
private static final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";


Cambiar credenciales
private static final String USUARIO = "admin";
private static final String PASSWORD = "admin";


Cambiar colección base
private static final String COLECCION = "/db/empleados";



🧪 Pruebas
Probar conexión
curl http://localhost:8080/exist/


Verificar documentos
- Abrir http://localhost:8080/exist
- Ir a Browse Collections
- Navegar a /db/empleados

📚 Dependencias Principales
<dependency>
    <groupId>org.exist-db</groupId>
    <artifactId>exist-core</artifactId>
    <version>6.2.0</version>
</dependency>

<dependency>
    <groupId>org.apache.xmlgraphics</groupId>
    <artifactId>batik-dom</artifactId>
    <version>1.17</version>
</dependency>



🐛 Solución de Problemas
Error de conexión
- Verificar que eXist-db está corriendo
- Revisar puerto y URL
- Comprobar credenciales
Error de validación XML
- Revisar estructura del XML
- Comprobar que todos los campos existen
- Validar contra el XSD
Error al consultar
- Verificar que la colección /db/empleados existe
- Comprobar permisos

🤝 Contribuciones
- Haz un fork del proyecto
- Crea una rama:
git checkout -b feature/nueva-funcionalidad
- Realiza tus cambios
- Envía un Pull Request

📄 Licencia
Este proyecto está disponible bajo la Licencia MIT.

👨‍💻 Autor
Esteban – Proyecto de Gestión de Empleados con eXist-db
