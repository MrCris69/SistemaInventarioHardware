package basedatosjava.vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VentanaDashboard extends JFrame {

    public VentanaDashboard() {
        setTitle("Sistema de Inventario - Taller de Hardware");
        setSize(450, 350);
        // Esta es la única ventana que debe cerrar el programa completo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null);
        
        configurarMenu();
    }

    private void configurarMenu() {
        setLayout(new BorderLayout());
        
        JLabel lblTitulo = new JLabel("Menú Principal del Sistema", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 15, 15));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 40, 40, 40));
        
        JButton btnComponentes = new JButton("1. Gestión de Componentes");
        JButton btnHerramientas = new JButton("2. Gestión de Herramientas");
        JButton btnServicios = new JButton("3. Registro de Servicios");
        
        // Eventos para abrir las ventanas de cada integrante
        btnComponentes.addActionListener(e -> new VentanaComponente().setVisible(true));
        btnHerramientas.addActionListener(e -> new VentanaHerramienta().setVisible(true));
        btnServicios.addActionListener(e -> new VentanaServicio().setVisible(true));
        
        panelBotones.add(btnComponentes);
        panelBotones.add(btnHerramientas);
        panelBotones.add(btnServicios);
        
        add(lblTitulo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
    }
}
