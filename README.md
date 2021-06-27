# BSPQ21-SA05

Crear una base de datos llamada deustovideoclub y dar permisos al usuario por defecto (programa XAMPP)

    CREATE DATABASE deustovideoclub;
    CREATE USER IF NOT EXISTS 'spq'@'localhost' IDENTIFIED BY 'spq';
    GRANT ALL ON deustovideoclub.* TO 'spq'@'localhost';

Abrir cmd en la dirección del proyecto y compilar

    mvn compile
    
Enhance:

    mvn datanucleus:enhance
    
Creamos las tablas:

    mvn datanucleus:schema-create
    
Añadimos datos de prueba:

    mvn exec:java -Pdatos
    
Lanzamos el servidor:

    mvn exec:java
    
Lanzamos el cliente:

    mvn exec:java -Pcliente
