package basedatosjava.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    
    // URL apuntando a la nueva base de datos sistema_taller
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_taller?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String CLAVE = "dbalcocer2026"; 
    
    private ConexionBD() {
    }
    
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}
