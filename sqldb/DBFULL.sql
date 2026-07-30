-- ====================================================
-- 1. CREACIÓN DE LA BASE DE DATOS PRINCIPAL
-- ====================================================
CREATE DATABASE IF NOT EXISTS sistema_taller CHARACTER SET utf8mb4;
USE sistema_taller;

-- ====================================================
-- 2. TABLA INTEGRANTE 1: COMPONENTES
-- ====================================================
CREATE TABLE IF NOT EXISTS componente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL
);

-- ====================================================
-- 3. TABLA INTEGRANTE 2: HERRAMIENTAS
-- ====================================================
CREATE TABLE IF NOT EXISTS herramienta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tipo VARCHAR(100) NOT NULL,
    cantidad INT NOT NULL
);

-- ====================================================
-- 4. TABLA INTEGRANTE 3: SERVICIOS
-- ====================================================
CREATE TABLE IF NOT EXISTS servicio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL,
    cliente VARCHAR(100) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL
);

-- ====================================================
-- 5. LIMPIEZA DE TABLAS (Para evitar duplicados en pruebas)
-- ====================================================
TRUNCATE TABLE componente;
TRUNCATE TABLE herramienta;
TRUNCATE TABLE servicio;

-- ====================================================
-- 6. INSERCIÓN DE DATOS DE PRUEBA INICIALES
-- ====================================================
INSERT INTO componente (nombre, precio, stock) VALUES 
('Jeringa de pasta térmica Arctic MX-4', 35.50, 12),
('Chip de modificación Mariko', 120.00, 5);

INSERT INTO herramienta (nombre, tipo, cantidad) VALUES 
('Kit de microsoldadura', 'Precisión', 2),
('Estación de calor', 'Eléctrica', 1);

INSERT INTO servicio (descripcion, cliente, precio) VALUES 
('Mantenimiento preventivo de PC', 'Juan Pérez', 50.00),
('Modificación de consola Nintendo Switch', 'Ana Gómez', 150.00);