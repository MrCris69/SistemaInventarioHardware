package basedatosjava.vista;

import basedatosjava.dao.ComponenteDAO;
import basedatosjava.entidad.Componente;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class VentanaComponente extends JFrame {
    private final JTextField txtNombre;
    private final JTextField txtPrecio;
    private final JTextField txtStock;
    private final JButton btnGuardar;
    private final JButton btnListar;
    private final JTable tablaComponentes;
    private final DefaultTableModel modelo;
    private final ComponenteDAO componenteDAO;

    public VentanaComponente() {
        componenteDAO = new ComponenteDAO();
        
        txtNombre = new JTextField(20);
        txtPrecio = new JTextField(20);
        txtStock = new JTextField(20);
        btnGuardar = new JButton("Guardar");
        btnListar = new JButton("Listar");
        
        modelo = new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Precio", "Stock"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tablaComponentes = new JTable(modelo);
        
        configurarVentana();
        organizarComponentes();
        registrarEventos();
        listarComponentes();
    }

    private void configurarVentana() {
        setTitle("Registro de Componentes");
        setSize(1280, 720);
        // Cuando hagamos el Dashboard esto cambiará, por ahora cerrará la aplicación
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void organizarComponentes() {
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 8, 8));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(12, 12, 8, 12));
        
        panelFormulario.add(new JLabel("Nombre del Componente:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Precio:"));
        panelFormulario.add(txtPrecio);
        panelFormulario.add(new JLabel("Stock:"));
        panelFormulario.add(txtStock);
        
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnGuardar);
        panelBotones.add(btnListar);
        
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);
        
        JScrollPane desplazamiento = new JScrollPane(tablaComponentes);
        
        setLayout(new BorderLayout());
        add(panelSuperior, BorderLayout.NORTH);
        add(desplazamiento, BorderLayout.CENTER);
    }

    private void registrarEventos() {
        btnGuardar.addActionListener(e -> guardarComponente());
        btnListar.addActionListener(e -> listarComponentes());
    }

    private void guardarComponente() {
        // Obtenemos los valores y eliminamos espacios innecesarios con trim()
        String nombre = txtNombre.getText().trim();
        String precioTexto = txtPrecio.getText().trim();
        String stockTexto = txtStock.getText().trim();
        
        if (nombre.isEmpty() || precioTexto.isEmpty() || stockTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            double precio = Double.parseDouble(precioTexto);
            int stock = Integer.parseInt(stockTexto);
            
            // Creamos el objeto y lo entregamos al método registrar del DAO
            Componente componente = new Componente(nombre, precio, stock);
            boolean registrado = componenteDAO.registrar(componente);
            
            if (registrado) {
                JOptionPane.showMessageDialog(this, "Componente registrado.");
                txtNombre.setText("");
                txtPrecio.setText("");
                txtStock.setText("");
                txtNombre.requestFocus();
                listarComponentes(); // Actualizamos la tabla
            } else {
                JOptionPane.showMessageDialog(this, "No fue posible registrar el componente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio y el stock deben ser valores numéricos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarComponentes() {
        // Se ejecuta setRowCount(0) para eliminar las filas mostradas y evitar registros repetidos al presionar Listar[cite: 1]
        modelo.setRowCount(0);
        List<Componente> componentes = componenteDAO.listar();
        
        for (Componente componente : componentes) {
            modelo.addRow(new Object[]{
                componente.getId(),
                componente.getNombre(),
                componente.getPrecio(),
                componente.getStock()
            });
        }
    }
}