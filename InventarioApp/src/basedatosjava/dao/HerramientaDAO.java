package basedatosjava.dao;

import basedatosjava.conexion.ConexionBD;
import basedatosjava.entidad.Herramienta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HerramientaDAO {

    public boolean registrar(Herramienta herramienta) {
        String sql = "INSERT INTO herramienta (nombre, tipo, cantidad) VALUES (?, ?, ?)";
        
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            
            ps.setString(1, herramienta.getNombre());
            ps.setString(2, herramienta.getTipo());
            ps.setInt(3, herramienta.getCantidad());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al registrar: " + e.getMessage());
            return false;
        }
    }

    public List<Herramienta> listar() {
        List<Herramienta> herramientas = new ArrayList<>();
        String sql = "SELECT id, nombre, tipo, cantidad FROM herramienta ORDER BY id";
        
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Herramienta herramienta = new Herramienta();
                herramienta.setId(rs.getInt("id"));
                herramienta.setNombre(rs.getString("nombre"));
                herramienta.setTipo(rs.getString("tipo"));
                herramienta.setCantidad(rs.getInt("cantidad"));
                herramientas.add(herramienta);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al consultar: " + e.getMessage());
        }
        
        return herramientas;
    }
}