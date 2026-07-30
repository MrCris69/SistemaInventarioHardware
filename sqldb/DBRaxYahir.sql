USE sistema_taller;

CREATE TABLE IF NOT EXISTS herramienta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tipo VARCHAR(100) NOT NULL,
    cantidad INT NOT NULL
);

TRUNCATE TABLE herramienta;

INSERT INTO herramienta (nombre, tipo, cantidad)
VALUES 
('Kit de microsoldadura', 'Precisión', 2),
('Estación de calor', 'Eléctrica', 1);