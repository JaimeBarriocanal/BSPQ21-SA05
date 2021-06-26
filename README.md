# BSPQ21-SA05

Crear una base de datos en XAMPP:

    CREATE DATABASE deustovideoclub;
    CREATE USER IF NOT EXISTS 'spq'@'localhost' IDENTIFIED BY 'spq';
    GRANT ALL ON deustovideoclub.* TO 'spq'@'localhost';
    
Después compilamos el proyecto:

    mvn compile

Creamos las tablas:

    mvn datanucleus:schema-create

Insertamos los datos:

    mvn exec:java -Pdatos

Ejecutamos el servidor:

    mvn exec:java
