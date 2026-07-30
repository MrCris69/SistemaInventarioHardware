package basedatosjava.dao;

import basedatosjava.conexion.ConexionBD;
import basedatosjava.entidad.Componente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComponenteDAO {

    public boolean registrar(Componente componente) {
        String sql = "INSERT INTO componente (nombre, precio, stock) VALUES (?, ?, ?)";
        
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            
            ps.setString(1, componente.getNombre());
            ps.setDouble(2, componente.getPrecio());
            ps.setInt(3, componente.getStock());
            
            int filas = ps.executeUpdate();
            return filas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al registrar: " + e.getMessage());
            return false;
        }
    }

    public List<Componente> listar() {
        List<Componente> componentes = new ArrayList<>();
        String sql = "SELECT id, nombre, precio, stock FROM componente ORDER BY id";
        
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            // El método rs.next() avanza al siguiente registro y devuelve true mientras exista una fila disponible[cite: 1]
            while (rs.next()) {
                Componente componente = new Componente();
                componente.setId(rs.getInt("id"));
                componente.setNombre(rs.getString("nombre"));
                componente.setPrecio(rs.getDouble("precio"));
                componente.setStock(rs.getInt("stock"));
                
                componentes.add(componente);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al consultar: " + e.getMessage());
        }
        
        return componentes;
    }
}
