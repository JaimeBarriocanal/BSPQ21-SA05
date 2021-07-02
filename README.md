# BSPQ21-SA05

Crear una base de datos en XAMPP llamada deustovideoclub y dar permisos al usuario por defecto:

    CREATE DATABASE deustovideoclub;
    CREATE USER IF NOT EXISTS 'spq'@'localhost' IDENTIFIED BY 'spq';
    GRANT ALL ON deustovideoclub.* TO 'spq'@'localhost';
    
Abrir cmd en la dirección del proyecto y compilar

    mvn compile

Enhance:

    mvn datanucleus:enhance

Creamos las tablas:

    mvn datanucleus:schema-create

Añadimos los datos de prueba:

    mvn exec:java -Pdatos

Iniciamos el servidor:

    mvn exec:java
    
Lanzamos el cliente:

    mvn exec:java -Pcliente
    
Comprobar tests:

    mvn test


Para hacer Login en la parte de usuario utilizamos: "user0" y de contraseña "1234".

Para hacer Login en la parte de administrador: "admin" y "1234".


Desde usuario se pueden alquilar y devolver películas. Desde el admin se pueden crear y eliminar películas.
