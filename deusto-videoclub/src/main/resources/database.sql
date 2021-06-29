DROP SCHEMA deustovideoclub;
CREATE DATABASE deustovideoclub;
CREATE USER IF NOT EXISTS 'spq'@'localhost' IDENTIFIED BY 'spq';
GRANT ALL ON deustovideoclub.* TO 'spq'@'localhost';