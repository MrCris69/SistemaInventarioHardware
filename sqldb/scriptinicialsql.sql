CREATE DATABASE IF NOT EXISTS sistema_taller
CHARACTER SET utf8mb4;

USE sistema_taller;

CREATE TABLE IF NOT EXISTS componente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL
);
TRUNCATE TABLE componente;
-- Componentes
INSERT INTO componente (nombre, precio, stock)
VALUES 
('Procesador AMD Ryzen 7 7800X3D', 1579.00, 7),
('Tarjeta de Video NVIDIA RTX 5070 12GB', 3599.00, 5),
('Memoria RAM Corsair Vengeance DDR5 16GB 6000MHz', 929.00, 3),
('SSD NVMe M.2 Samsung 990 PRO 1TB', 629.50, 10),
('Fuente de Poder Corsair RM750x 80 Plus Gold Modular', 439.99, 12);
SELECT id, nombre, precio, stock
FROM componente
ORDER BY id;