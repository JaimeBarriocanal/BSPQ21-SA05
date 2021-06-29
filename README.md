# BSPQ21-SA05

<<<<<<< HEAD
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
=======
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
>>>>>>> branch 'main' of https://github.com/JaimeBarriocanal/BSPQ21-SA05
