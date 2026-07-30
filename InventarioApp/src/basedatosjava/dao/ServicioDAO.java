package basedatosjava.dao;
import basedatosjava.conexion.ConexionBD;
import basedatosjava.entidad.Servicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioDAO {
    public boolean registrar(Servicio servicio) {
        String sql = "INSERT INTO servicio (descripcion, cliente, precio) VALUES (?, ?, ?)";
        
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            
            ps.setString(1, servicio.getDescripcion());
            ps.setString(2, servicio.getCliente());
            ps.setDouble(3, servicio.getPrecio());
            
            int filas = ps.executeUpdate();
            return filas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al registrar: " + e.getMessage());
            return false;
        }
    }

    public List<Servicio> listar() {
        List<Servicio> servicios = new ArrayList<>();
        String sql = "SELECT id, descripcion, cliente, precio FROM servicio ORDER BY id";
        
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            // Recorremos el ResultSet fila por fila[cite: 1]
            while (rs.next()) {
                Servicio servicio = new Servicio();
                servicio.setId(rs.getInt("id"));
                servicio.setDescripcion(rs.getString("descripcion"));
                servicio.setCliente(rs.getString("cliente"));
                servicio.setPrecio(rs.getDouble("precio"));
                
                servicios.add(servicio);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al consultar: " + e.getMessage());
        }
        
        return servicios;
    }
}
