-- Seleccionamos la base de datos del proyecto
USE sistema_taller;

-- Creamos la tabla servicio con sus 4 campos correspondientes
CREATE TABLE IF NOT EXISTS servicio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL,
    cliente VARCHAR(100) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL
);

-- Limpiamos la tabla para evitar datos duplicados al probar
TRUNCATE TABLE servicio;

-- Insertamos un par de servicios iniciales para hacer pruebas
INSERT INTO servicio (descripcion, cliente, precio)
VALUES 
('Mantenimiento preventivo de PC', 'Juan Pérez', 50.00),
('Modificación de consola Nintendo Switch', 'Ana Gómez', 150.00),
('Ensamblaje de PC Gamer a medida', 'Luis Torres', 120.00),
('Reparación de bisagras de laptop HP', 'Sofía Castro', 95.00),
('Diagnóstico de tarjeta de video sin imagen', 'Elena Rojas', 30.00),
('Mantenimiento correctivo de consola PlayStation 5', 'Diego Salazar', 180.00),
('Cambio de pantalla LCD para laptop Dell', 'Valeria Castillo', 110.00),
('Ampliación de memoria RAM a 32GB', 'Jorge Luis', 25.00);

-- Recuperamos los registros para verificar el resultado en pantalla
SELECT id, descripcion, cliente, precio
FROM servicio
ORDER BY id;